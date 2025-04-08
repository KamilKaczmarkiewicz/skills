package com.project.skill.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class NotFoundException extends RuntimeException{

    @Getter
    @RequiredArgsConstructor
    public enum ObjectType {
        PERSON("Person");
        private final String value;
    }

    public NotFoundException(ObjectType type, String id) {
        super("%s with id: %s not found.".formatted(type.value, id));
    }
}
