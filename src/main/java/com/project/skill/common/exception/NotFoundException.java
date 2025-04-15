package com.project.skill.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class NotFoundException extends RuntimeException {

    public NotFoundException(ObjectType type, String id) {
        super("%s with id: %s not found.".formatted(type.value, id));
    }

    @Getter
    @RequiredArgsConstructor
    public enum ObjectType {
        TASK("Task"),
        PERSON("Person");
        private final String value;
    }
}
