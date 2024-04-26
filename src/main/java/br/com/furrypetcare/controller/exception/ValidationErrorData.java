package br.com.furrypetcare.controller.exception;

import org.springframework.validation.FieldError;

record ValidationErrorData(
        String field,
        String message
) {
    public ValidationErrorData(FieldError error) {
        this(
                error.getField(),
                error.getDefaultMessage()
        );
    }
}
