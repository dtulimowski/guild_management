package io.github.guild.application.controller;

import io.github.guild.exception.InvalidCommandException;
import io.github.guild.application.controller.error.ApiErrorResponse;
import io.github.guild.exception.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {InvalidCommandException.class})
    protected ResponseEntity<Object> handleInvalidCommandException(InvalidCommandException ex) {
        log.info("Invalid command cannot be handled", ex);
        return buildViolationResponse(ex);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        return buildViolationResponse(ex);
    }

    private ResponseEntity<Object> buildViolationResponse(ConstraintViolationException ex) {
        var builder = ApiErrorResponse.builder()
                .message("Invalid request message sent")
                .errorDetail(ex.getMessage())
                .timestamp(LocalDateTime.now());
        for (ConstraintViolation<?> error : ex.getConstraintViolations()) {
            builder.error(error.getPropertyPath().toString(), error.getMessage());
        }

        return new ResponseEntity<>(builder.build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        log.info("Item not found", ex);
        var builder = ApiErrorResponse.builder()
                .message("Item not found")
                .errorDetail(ex.getMessage())
                .timestamp(LocalDateTime.now());

        return new ResponseEntity<>(builder.build(), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var builder = ApiErrorResponse.builder()
                .message("Invalid request message sent")
                .timestamp(LocalDateTime.now());
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            builder.error(error.getField(), error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            builder.error(error.getObjectName(), error.getDefaultMessage());
        }

        return handleExceptionInternal(ex, builder.build(), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var builder = ApiErrorResponse.builder()
                .message("Invalid request message sent. JSON deserialization failed or invalid enum value specified")
                .timestamp(LocalDateTime.now());

        return handleExceptionInternal(ex, builder.build(), headers, HttpStatus.BAD_REQUEST, request);
    }
}
