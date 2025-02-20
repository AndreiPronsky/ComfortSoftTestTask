package org.pronsky.comfortsofttesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.pronsky.comfortsofttesttask.service.FileService;
import org.pronsky.comfortsofttesttask.service.dto.ErrorResponseDto;
import org.pronsky.comfortsofttesttask.service.dto.FindMaxDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Tag(name = "File API")
public class FileControllerImpl {

    private final FileService fileService;

    @Operation(summary = "Find max number in a file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Number found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FindMaxDto.class))}),
            @ApiResponse(responseCode = "500", description = "Something went wrong",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    @GetMapping("/findMax")
    public ResponseEntity<Long> getMaxFromFile(@RequestBody FindMaxDto findMaxDto) {
        return ResponseEntity.ok(fileService.getMax(findMaxDto));
    }
}
