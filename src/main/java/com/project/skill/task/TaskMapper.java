package com.project.skill.task;

import com.project.skill.person.dto.PersonDto;
import com.project.skill.task.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface TaskMapper {

    TaskDto toDto(Task task);

    default Task toNewPersonTask(PersonDto newPerson) {
        if (newPerson == null) {
            return null;
        }
        return Task.builder()
                .personId(newPerson.id())
                .comparableObjects(Set.of(
                        newComparableObject(FieldName.NAME, newPerson.name()),
                        newComparableObject(FieldName.SURNAME, newPerson.surname()),
                        newComparableObject(FieldName.COMPANY, newPerson.company()),
                        newComparableObject(FieldName.BIRTH_DATE, newPerson.birthDate().toString())
                ))
                .progressStatusPercentage(100.0)
                .build();
    }

    private ComparableObject newComparableObject(FieldName fieldName, String newValue){
        return ComparableObject.builder()
                .fieldName(fieldName)
                .newValue(newValue)
                .classification(Classification.ADDED)
                .build();
    }

    default Task toDeletedPersonTask(PersonDto person) {
        if (person == null) {
            return null;
        }
        return Task.builder()
                .personId(person.id())
                .comparableObjects(Set.of(
                        newDeletedComparableObject(FieldName.NAME, person.name()),
                        newDeletedComparableObject(FieldName.SURNAME, person.surname()),
                        newDeletedComparableObject(FieldName.COMPANY, person.company()),
                        newDeletedComparableObject(FieldName.BIRTH_DATE, person.birthDate().toString())
                ))
                .progressStatusPercentage(100.0)
                .build();
    }

    private ComparableObject newDeletedComparableObject(FieldName fieldName, String previousValue){
        return ComparableObject.builder()
                .fieldName(fieldName)
                .previousValue(previousValue)
                .classification(Classification.DELETED)
                .build();
    }

    default Task toTask(PersonDto newPerson, PersonDto oldPerson) {
        if (newPerson == null || oldPerson == null) {
            return null;
        }
        return Task.builder()
                .personId(newPerson.id())
                .comparableObjects(Set.of(
                        newComparableObject(FieldName.NAME, oldPerson.name(), newPerson.name()),
                        newComparableObject(FieldName.SURNAME, oldPerson.surname(), newPerson.surname()),
                        newComparableObject(FieldName.COMPANY, oldPerson.company(), newPerson.company()),
                        newComparableObject(FieldName.BIRTH_DATE, oldPerson.birthDate().toString(), newPerson.birthDate().toString())
                ))
                .progressStatusPercentage(0.0)
                .build();
    }

    private ComparableObject newComparableObject(FieldName fieldName, String previousValue, String newValue){
        return ComparableObject.builder()
                .fieldName(fieldName)
                .previousValue(previousValue)
                .newValue(newValue)
                .classification(Classification.IN_PROGRESS)
                .build();
    }

}
