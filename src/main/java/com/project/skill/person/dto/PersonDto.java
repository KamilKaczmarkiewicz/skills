package com.project.skill.person.dto;

import java.time.LocalDate;

public record PersonDto(
        String id,
        String name,
        String surname,
        String company,
        LocalDate birthDate
) {
}
