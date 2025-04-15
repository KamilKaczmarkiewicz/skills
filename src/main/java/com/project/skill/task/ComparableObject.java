package com.project.skill.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ComparableObject {
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private FieldName fieldName;
    private String previousValue;
    private String newValue;
    private Classification classification;

    /**
     * Represents the similarity between the old and new values as a percentage.
     * Expected format: Double value in the range 0.0 - 100.0,
     * where 85.5 means 85.5% similarity.
     */
    private Double similarityPercentage;
}
