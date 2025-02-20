package org.pronsky.comfortsofttesttask.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Data
@Schema(description = "Error response")
public class ErrorResponseDto {

    @Schema(description = "Http status code", example = "500")
    private int status;
    @Schema(description = "Error message", example = "Something went wrong")
    private String error;
    @Schema(description = "Request URI")
    private String path;

    public ErrorResponseDto(String message, HttpStatus status, WebRequest request) {

        this.status = status.value();
        this.error = message;
        this.path = ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}
