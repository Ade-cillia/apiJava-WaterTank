package com.example.api.exceptions;

import com.example.api.models.ErrorValidation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class RestApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // Create our list of error message from ErrorValidation objects
        List<ErrorValidation> errors = new ArrayList<>();

        // Get BindingResult object
        BindingResult bindingResult = ex.getBindingResult();

        // Get List of FieldErrors
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        // Loop on list errors
        for (FieldError fieldError : fieldErrors ) {
            errors.add(new ErrorValidation(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return this.handleExceptionInternal(ex, errors, headers, status, request);
    }
}
