package com.project.skill.task;

import com.project.skill.person.dto.PersonDto;
import com.project.skill.task.dto.TaskDto;

public interface TaskFacade {

    TaskDto createTaskForNewPerson(PersonDto personDto);

    TaskDto createTaskForUpdatedPerson(PersonDto newPerson, PersonDto oldPerson);

    TaskDto createTaskForDeletedPerson(PersonDto personDto);

}
