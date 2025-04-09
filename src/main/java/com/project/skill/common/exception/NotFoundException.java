package com.project.skill.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

public class NotFoundException extends RuntimeException{

    @Getter
    @RequiredArgsConstructor
    public enum ObjectType {
        TASK("Task"),
        PERSON("Person");
        private final String value;
    }

    public NotFoundException(ObjectType type, UUID id) {
        super("%s with id: %s not found.".formatted(type.value, id));
    }
}
