package com.project.skill.task.dto;

import com.project.skill.task.Classification;
import com.project.skill.task.FieldName;

import java.util.UUID;

public record ComparableObjectDto(
        UUID id,
        FieldName fieldName,
        String previousValue,
        String newValue,
        Classification classification,
        Double similarityPercentage
) {
}
