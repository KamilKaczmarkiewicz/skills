package com.project.skill.person.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreatePersonRequest(
        @NotNull(message = "name cannot be null") @Schema(example = "Jon") String name,
        @NotNull(message = "surname cannot be null") @Schema(example = "Wood") String surname,
        @NotNull(message = "company cannot be null") @Schema(example = "Beans") String company,
        @NotNull(message = "birthDate cannot be null") @Schema(example = "1992-01-21") LocalDate birthDate
) {
}
