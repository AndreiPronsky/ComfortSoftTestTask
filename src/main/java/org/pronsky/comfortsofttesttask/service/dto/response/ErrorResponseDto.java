package org.pronsky.comfortsofttesttask.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "Error response")
public class ErrorResponseDto {

    @Schema(description = "Error message", example = "Something went wrong")
    private String error;
    @Schema(description = "Error timestamp")
    private LocalDateTime timestamp;
    @Schema(description = "Exception class")
    private String className;
}
