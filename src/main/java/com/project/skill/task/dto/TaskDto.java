package com.project.skill.task.dto;

import java.time.Instant;
import java.util.List;

public record TaskDto(
        String id,
        String personId,
        List<ComparableObjectDto> comparableObjects,
        Double progressStatusPercentage,
        Instant createdAt
) {
}
