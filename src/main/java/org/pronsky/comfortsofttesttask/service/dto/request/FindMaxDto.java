package org.pronsky.comfortsofttesttask.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Request Dto co find maximum number in a file")
public class FindMaxDto {

    @Schema(description = "Path to file", example = "Test.xlsx")
    private String localPathToFile;

    @Schema(description = "Number of first numbers in the file, among which maximum should be found", example = "10")
    private Integer numberOfElements;
}
