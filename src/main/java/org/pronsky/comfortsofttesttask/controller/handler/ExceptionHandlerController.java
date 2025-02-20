package org.pronsky.comfortsofttesttask.controller.handler;

import org.pronsky.comfortsofttesttask.service.dto.ErrorResponseDto;
import org.pronsky.comfortsofttesttask.service.exceptions.FailedToReadFileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Внутренняя ошибка сервера";

    @ExceptionHandler(FailedToReadFileException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequest(FailedToReadFileException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        request
                ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto(
                        INTERNAL_SERVER_ERROR_MESSAGE,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        request
                ));
    }
}
