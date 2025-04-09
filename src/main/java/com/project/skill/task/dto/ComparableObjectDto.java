package com.project.skill.task.dto;

import java.time.LocalDate;

public record ComparableObjectDto(
        String name,
        String surname,
        String company,
        LocalDate birthDate
) {
}
