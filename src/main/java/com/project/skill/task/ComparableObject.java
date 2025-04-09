package com.project.skill.task;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
class ComparableObject {
    private String name;
    private String surname;
    private String company;
    private LocalDate birthDate;
}
