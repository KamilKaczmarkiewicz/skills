package com.project.skill.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return handleException(HttpStatus.BAD_REQUEST, ex, errors);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, ex);
    }

    ResponseEntity<ErrorResponse> handleException(HttpStatus httpStatus, Exception ex) {
        return handleException(httpStatus, ex, ex.getMessage());
    }

    ResponseEntity<ErrorResponse> handleException(HttpStatus httpStatus, Exception ex, String errorMessage) {
        var debugId = UUID.randomUUID();
        log.warn("DebugId: {} - {}", debugId, ex.getMessage());
        return ResponseEntity.status(httpStatus).body(new ErrorResponse(errorMessage, debugId));
    }


    private record ErrorResponse(Instant timestamp, String message, UUID debugId) {
        ErrorResponse(String message, UUID debugId) {
            this(Instant.now(), message, debugId);
        }
    }

}
