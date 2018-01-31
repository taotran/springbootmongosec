package com.pycogroup.taotran.rest.error;

import com.pycogroup.taotran.rest.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception e, WebRequest request) {
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "Server error");
    }
}
