package com.project.skill.person.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PersonDto(
        UUID id,
        String name,
        String surname,
        String company,
        LocalDate birthDate
) {
}
