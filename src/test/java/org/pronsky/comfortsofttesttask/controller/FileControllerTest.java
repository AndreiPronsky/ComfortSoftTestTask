package org.pronsky.comfortsofttesttask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pronsky.comfortsofttesttask.controller.handler.ExceptionHandlerController;
import org.pronsky.comfortsofttesttask.service.FileService;
import org.pronsky.comfortsofttesttask.service.dto.request.FindMaxDto;
import org.pronsky.comfortsofttesttask.service.dto.response.MaxValueResponseDto;
import org.pronsky.comfortsofttesttask.service.exceptions.FailedToReadFileException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class FileControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private FileService fileService;

    @InjectMocks
    private FileController fileController;

    private MaxValueResponseDto responseDto;
    private String expectedContent;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        MockitoAnnotations.openMocks(this);

        Long expectedMaxNumber = 243L;

        mockMvc = MockMvcBuilders
                .standaloneSetup(fileController)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();

        responseDto = MaxValueResponseDto.builder()
                .maxValue(expectedMaxNumber)
                .build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        expectedContent = objectMapper.writeValueAsString(responseDto);
    }

    @Test
    @SneakyThrows
    void getMaxNumberFromFile_whenNumberFound_shouldReturnMaxValueResponseDto() {

        when(fileService.getMaxNumber(any(FindMaxDto.class))).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/files/findMax")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("numberOfElements", String.valueOf(10))
                        .header("localPathToFile", "Test.xlsx"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedContent))
                .andDo(print());

        verify(fileService, times(1)).getMaxNumber(any(FindMaxDto.class));
    }

    @Test
    @SneakyThrows
    void getMaxNumberFromFile_whenFileNotFound_shouldReturnBadRequest() {
        when(fileService.getMaxNumber(any(FindMaxDto.class))).thenThrow(FailedToReadFileException.class);

        mockMvc.perform(get("/api/v1/files/findMax")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("numberOfElements", String.valueOf(10))
                        .header("localPathToFile", "Test.xlsx"))
                .andExpect(status().isBadRequest())
                .andDo(print());

        verify(fileService, times(1)).getMaxNumber(any(FindMaxDto.class));
    }
}
