package com.project.skill.task;

import com.project.skill.common.TimeHelper;
import com.project.skill.common.exception.NotFoundException;
import com.project.skill.person.dto.PersonDto;
import com.project.skill.task.dto.TaskDto;
import com.project.skill.task.events.TaskUpdateEvent;
import io.opentelemetry.context.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.project.skill.common.exception.NotFoundException.ObjectType.TASK;

@Service
@RequiredArgsConstructor
class TaskService implements TaskFacade {

    private final TaskRepository repository;
    private final TaskMapper taskMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final TimeHelper timeHelper;


    @Override
    public TaskDto createTaskForNewPerson(PersonDto personDto) {
        var task = repository.save(taskMapper.toNewPersonTask(personDto));
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto createTaskForUpdatedPerson(PersonDto newPerson, PersonDto oldPerson) {
        var task = repository.save(taskMapper.toTask(newPerson, oldPerson));
        var taskDto = taskMapper.toDto(task);
        var propagatedContext = Context.current();
        eventPublisher.publishEvent(new TaskUpdateEvent(taskDto, propagatedContext));
        return taskDto;
    }

    @Override
    public TaskDto createTaskForDeletedPerson(PersonDto personDto) {
        var task = repository.save(taskMapper.toDeletedPersonTask(personDto));
        return taskMapper.toDto(task);
    }

    Page<TaskDto> getAllTasks(Pageable pageable, UUID personId) {
        if (personId != null) {
            return repository.findByPersonIdWithComparableObjects(personId, pageable)
                    .map(taskMapper::toDto);
        }
        return repository.findAllWithComparableObjects(pageable)
                .map(taskMapper::toDto);
    }

    @Cacheable(value = "tasks", key = "#id")
    TaskDto getTaskById(UUID id) {
        timeHelper.sleep(500);
        return repository.findByIdWithComparableObjects(id)
                .map(taskMapper::toDto)
                .orElseThrow(() -> new NotFoundException(TASK, id));
    }
}
