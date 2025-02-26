package org.pronsky.comfortsofttesttask.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "Error response")
public class ErrorResponseDto implements ResponseDto {

    @Schema(description = "Error message", example = "Something went wrong")
    private String error;
    @Schema(description = "Error timestamp", example = "2025-02-21T13:14:58.413044066")
    private LocalDateTime timestamp;
    @Schema(description = "Exception class", example = "IllegalArgumentException")
    private String className;
}
