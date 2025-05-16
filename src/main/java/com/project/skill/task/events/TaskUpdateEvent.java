package com.project.skill.task.events;

import com.project.skill.task.dto.TaskDto;
import io.opentelemetry.context.Context;

public record TaskUpdateEvent(TaskDto taskDto, Context propagatedContext) {
}
