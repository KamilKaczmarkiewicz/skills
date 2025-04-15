package com.project.skill.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {

    @Id
    private String id;

    private String name;

    private String surname;

    private String company;

    private LocalDate birthDate;
}
