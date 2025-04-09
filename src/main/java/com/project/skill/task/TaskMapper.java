package com.project.skill.task;

import com.project.skill.person.dto.PersonDto;
import com.project.skill.task.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface TaskMapper {

    TaskDto toDto(Task task);

    default Task toNewPersonTask(PersonDto newPerson) {
        if (newPerson == null) {
            return null;
        }
        return Task.builder()
                .personId(newPerson.id())
                .classification(Classification.ADDED)
                .newValues(new ComparableObject(
                        newPerson.name(),
                        newPerson.surname(),
                        newPerson.company(),
                        newPerson.birthDate()
                ))
                .progressStatusPercentage(100.0)
                .build();
    }

    default Task toDeletedPersonTask(PersonDto person) {
        if (person == null) {
            return null;
        }
        return Task.builder()
                .personId(person.id())
                .classification(Classification.DELETED)
                .oldValues(new ComparableObject(
                        person.name(),
                        person.surname(),
                        person.company(),
                        person.birthDate()
                ))
                .progressStatusPercentage(100.0)
                .build();
    }

    default Task toTask(PersonDto newPerson, PersonDto oldPerson) {
        if (newPerson == null || oldPerson == null) {
            return null;
        }
        return Task.builder()
                .personId(newPerson.id())
                .classification(Classification.IN_PROGRESS)
                .oldValues(new ComparableObject(
                        oldPerson.name(),
                        oldPerson.surname(),
                        oldPerson.company(),
                        oldPerson.birthDate()
                ))
                .newValues(new ComparableObject(
                        newPerson.name(),
                        newPerson.surname(),
                        newPerson.company(),
                        newPerson.birthDate()
                ))
                .progressStatusPercentage(0.0)
                .build();
    }

}
