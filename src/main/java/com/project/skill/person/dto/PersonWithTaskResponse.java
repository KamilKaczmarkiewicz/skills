package com.project.skill.person.dto;

import java.util.UUID;

public record PersonWithTaskResponse(PersonDto person, UUID taskId) {
}
