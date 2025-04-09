package com.project.skill.task;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ComparableObject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private FieldName fieldName;
    private String previousValue;
    private String newValue;

    @Enumerated(EnumType.STRING)
    private Classification classification;

    /**
     * Represents the similarity between the old and new values as a percentage.
     * Expected format: Double value in the range 0.0 - 100.0,
     * where 85.5 means 85.5% similarity.
     */
    private Double similarityPercentage;
}
