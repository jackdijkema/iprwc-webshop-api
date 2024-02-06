package dev.jacksd.iprwc.api.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleRequestExceptionBadRequest(@NotNull ApiRequestException e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException= new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleRequestExceptionUnauthorized(@NotNull ApiRequestException e) {

        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        ApiException apiException= new ApiException(e.getMessage(), unauthorized, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, unauthorized);
    }

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleRequestExceptionForbidden(@NotNull ApiRequestException e) {

        HttpStatus forbidden = HttpStatus.FORBIDDEN;
        ApiException apiException= new ApiException(e.getMessage(), forbidden, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, forbidden);
    }
}