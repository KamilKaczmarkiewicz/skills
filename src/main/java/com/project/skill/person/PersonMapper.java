package com.project.skill.person;

import com.project.skill.person.dto.CreatePersonRequest;
import com.project.skill.person.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PersonMapper {

    PersonDto toDto(Person person);

    Person toPerson(CreatePersonRequest createPersonRequest);

    void updatePersonFromDto(CreatePersonRequest updateRequest, @MappingTarget Person person);

}
