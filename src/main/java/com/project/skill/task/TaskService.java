package com.project.skill.task;

import com.project.skill.common.exception.NotFoundException;
import com.project.skill.person.dto.PersonDto;
import com.project.skill.task.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.project.skill.common.exception.NotFoundException.ObjectType.TASK;

@Service
@RequiredArgsConstructor
class TaskService implements TaskFacade{

    private final TaskRepository repository;
    private final TaskMapper taskMapper;


    @Override
    public TaskDto createTaskForNewPerson(PersonDto personDto) {
        var task = repository.save(taskMapper.toNewPersonTask(personDto));
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto createTaskForUpdatedPerson(PersonDto newPerson, PersonDto oldPerson) {
        var task = repository.save(taskMapper.toTask(newPerson, oldPerson));
        //todo calculate Task async
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto createTaskForDeletedPerson(PersonDto personDto) {
        var task = repository.save(taskMapper.toDeletedPersonTask(personDto));
        return taskMapper.toDto(task);
    }

    Page<TaskDto> getAllTasks(Pageable pageable, UUID personId) {
        if (personId != null) {
            return repository.findByPersonId(personId, pageable)
                    .map(taskMapper::toDto);
        }
        return repository.findAll(pageable)
                .map(taskMapper::toDto);
    }

    TaskDto getTaskById(UUID id) {
        return repository.findById(id)
                .map(taskMapper::toDto)
                .orElseThrow(() -> new NotFoundException(TASK, id));
    }
}
