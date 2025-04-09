package com.project.skill.task.dto;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record TaskDto(
        UUID id,
        UUID personId,
        Set<ComparableObjectDto> comparableObjects,
        Double progressStatusPercentage,
        Instant createdAt
) {
}
