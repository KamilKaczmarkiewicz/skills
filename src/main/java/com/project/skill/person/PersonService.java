package com.project.skill.person;

import com.project.skill.common.exception.NotFoundException;
import com.project.skill.person.dto.CreatePersonRequest;
import com.project.skill.person.dto.DeletePersonResponse;
import com.project.skill.person.dto.PersonDto;
import com.project.skill.person.dto.PersonWithTaskResponse;
import com.project.skill.task.TaskFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.project.skill.common.exception.NotFoundException.ObjectType.PERSON;

@Service
@RequiredArgsConstructor
@Slf4j
class PersonService {

    private final PersonRepository repository;
    private final PersonMapper personMapper;
    private final TaskFacade taskFacade;

    @Transactional
    PersonWithTaskResponse createPerson(CreatePersonRequest request) {
        log.debug("Creating new a person: {}", request);
        var person = personMapper.toPerson(request);
        person = repository.save(person);
        var personDto = personMapper.toDto(person);
        var task = taskFacade.createTaskForNewPerson(personDto);
        return new PersonWithTaskResponse(personDto, task.id());
    }

    Page<PersonDto> getAllPersons(Pageable pageable) {
        return repository.findAll(pageable)
                .map(personMapper::toDto);
    }

    PersonDto getPersonById(UUID id) {
        return repository.findById(id)
                .map(personMapper::toDto)
                .orElseThrow(() -> createNotFoundException(id));
    }

    @Transactional
    PersonWithTaskResponse updatePerson(UUID id, CreatePersonRequest request) {
        log.debug("Updating person with id: {}", id);
        var existingPerson = repository.findByIdWithLock(id)
                .orElseThrow(() -> createNotFoundException(id));
        var oldPersonDto = personMapper.toDto(existingPerson);
        personMapper.updatePersonFromDto(request, existingPerson);
        var updatedPerson = repository.save(existingPerson);
        var newPersonDto = personMapper.toDto(updatedPerson);
        var task = taskFacade.createTaskForUpdatedPerson(newPersonDto, oldPersonDto);
        return new PersonWithTaskResponse(newPersonDto, task.id());
    }

    private NotFoundException createNotFoundException(UUID id) {
        return new NotFoundException(PERSON, id);
    }

    @Transactional
    DeletePersonResponse deletePerson(UUID id) {
        var person = repository.findByIdWithLock(id)
                .orElseThrow(() -> createNotFoundException(id));
        var task = taskFacade.createTaskForDeletedPerson(personMapper.toDto(person));
        repository.delete(person);
        return new DeletePersonResponse(task.id());
    }
}
