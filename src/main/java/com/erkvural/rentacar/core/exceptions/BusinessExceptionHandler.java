package com.erkvural.rentacar.core.exceptions;

import com.erkvural.rentacar.core.utilities.results.ErrorDataResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleBusinessExceptions(BusinessException businessException) {
        String businessError = businessException.getMessage();

        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(businessError, "Business.Error");

        return errorDataResult;
    }
}
