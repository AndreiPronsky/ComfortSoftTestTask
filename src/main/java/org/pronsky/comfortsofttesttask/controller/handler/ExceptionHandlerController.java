package org.pronsky.comfortsofttesttask.controller.handler;

import org.pronsky.comfortsofttesttask.service.dto.response.ErrorResponseDto;
import org.pronsky.comfortsofttesttask.service.exceptions.FailedToReadFileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";
    public static final String BAD_REQUEST_MESSAGE = "Bad request";

    @ExceptionHandler({
            FailedToReadFileException.class,
            NullPointerException.class
    })
    public ResponseEntity<ErrorResponseDto> handleBadRequest(FailedToReadFileException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildErrorResponseBody(BAD_REQUEST_MESSAGE, exception));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildErrorResponseBody(INTERNAL_SERVER_ERROR_MESSAGE, exception));
    }

    private ErrorResponseDto buildErrorResponseBody(String message, RuntimeException exception) {
        return ErrorResponseDto.builder()
                .error(message)
                .className(exception.getClass().getSimpleName())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
