package com.pycogroup.taotran.rest.error;

import com.pycogroup.taotran.rest.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

//@RestControllerAdvice
public class GlobalResourceExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalResourceExceptionHandler.class);

//    @ExceptionHandler(value = {Exception.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception e, WebRequest request) {
        LOGGER.info(e.getMessage());
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, e.getMessage());
    }
}
