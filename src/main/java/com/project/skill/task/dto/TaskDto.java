package com.project.skill.task.dto;

import com.project.skill.task.Classification;

import java.time.Instant;
import java.util.UUID;

public record TaskDto(
        UUID id,
        UUID personId,
        Classification classification,
        ComparableObjectDto oldValues,
        ComparableObjectDto newValues,
        Double similarityPercentage,
        Double progressStatusPercentage,
        Instant createdAt
) {
}
