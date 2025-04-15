package com.project.skill.person;

import com.project.skill.person.dto.CreatePersonRequest;
import com.project.skill.person.dto.DeletePersonResponse;
import com.project.skill.person.dto.PersonDto;
import com.project.skill.person.dto.PersonWithTaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
class PersonController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PersonWithTaskResponse createPerson(@Valid @RequestBody CreatePersonRequest request){
        return personService.createPerson(request);
    }

    @GetMapping
    Page<PersonDto> getAllPersons(
            @ParameterObject @PageableDefault(sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable
    ){
        return personService.getAllPersons(pageable);
    }

    @GetMapping("/{id}")
    PersonDto getPersonById(@PathVariable String id){
        return personService.getPersonById(id);
    }

    @PutMapping("/{id}")
    PersonWithTaskResponse updatePerson(@PathVariable String id, @Valid @RequestBody CreatePersonRequest request){
        return personService.updatePerson(id, request);
    }

    @DeleteMapping("/{id}")
    DeletePersonResponse deletePerson(@PathVariable String id){
        return personService.deletePerson(id);
    }
}
