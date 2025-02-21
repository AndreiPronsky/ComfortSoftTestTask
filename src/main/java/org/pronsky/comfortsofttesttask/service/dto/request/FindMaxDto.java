package org.pronsky.comfortsofttesttask.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Request Dto co find maximum number in a file")
public class FindMaxDto {

    @Schema(description = "Path to file")
    @NotNull(message = "Path to a file should not be null or blank")
    private String localPathToFile;

    @Schema(description = "Number of first numbers in the file, among which maximum should be found")
    @Positive(message = "Number must be greater than 0")
    private Integer numberOfElements;
}
