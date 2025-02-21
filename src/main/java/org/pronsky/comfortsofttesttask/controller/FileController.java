package org.pronsky.comfortsofttesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.pronsky.comfortsofttesttask.service.FileService;
import org.pronsky.comfortsofttesttask.service.dto.request.FindMaxDto;
import org.pronsky.comfortsofttesttask.service.dto.response.ErrorResponseDto;
import org.pronsky.comfortsofttesttask.service.dto.response.ResponseDto;
import org.pronsky.comfortsofttesttask.service.dto.response.SimpleErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@Tag(name = "File API")
public class FileController {

    private final FileService fileService;

    @Operation(
            summary = "Find max number in a file",
            description = """
                    Invokes service method to find maximum number
                    in a range of values limited by a value passed in a request,
                    within the file allocating in a provided path.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Number found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FindMaxDto.class))}),
            @ApiResponse(responseCode = "500", description = "Something went wrong",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    @GetMapping("/findMax")
    public ResponseEntity<ResponseDto> getMaxNumberFromFile(
            @RequestHeader(defaultValue = "Test.xlsx") String localPathToFile,
            @RequestParam(defaultValue = "10") Integer numberOfElements) {
        if (numberOfElements == null || numberOfElements < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SimpleErrorResponseDto.builder()
                    .message("Number of elements must be greater than 0")
                    .build());
        }
        return ResponseEntity.ok(fileService.getMaxNumber(FindMaxDto.builder()
                .localPathToFile(localPathToFile)
                .numberOfElements(numberOfElements)
                .build()));
    }
}
