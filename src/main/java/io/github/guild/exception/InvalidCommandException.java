package io.github.guild.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Set;

@Getter
public class InvalidCommandException extends ConstraintViolationException {
    public InvalidCommandException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }

    public InvalidCommandException(String message) {
        super(message, Collections.emptySet());
    }

    public InvalidCommandException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
