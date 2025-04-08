package com.project.skill.person;

import com.project.skill.common.exception.NotFoundException;
import com.project.skill.person.dto.CreatePersonRequest;
import com.project.skill.person.dto.PersonDto;
import com.project.skill.person.dto.UpdatePersonRequest;
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

    PersonDto createPerson(CreatePersonRequest request) {
        log.debug("Creating new a person: {}", request);
        var person = personMapper.toPerson(request);
        person = repository.save(person);
        return personMapper.toDto(person);
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
    PersonDto updatePerson(UUID id, UpdatePersonRequest request) {
        var existingPerson = repository.findByIdForUpdateById(id)
                .orElseThrow(() -> createNotFoundException(id));
        personMapper.updatePersonFromDto(request, existingPerson);
        var updatedPerson = repository.save(existingPerson);
        return personMapper.toDto(updatedPerson);
    }

    private NotFoundException createNotFoundException(UUID id) {
        return new NotFoundException(PERSON, id.toString());
    }

    void deletePerson(UUID id) {
        repository.deleteById(id);
    }
}
