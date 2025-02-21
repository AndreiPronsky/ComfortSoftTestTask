package org.pronsky.comfortsofttesttask.service;

import org.pronsky.comfortsofttesttask.service.dto.request.FindMaxDto;
import org.pronsky.comfortsofttesttask.service.dto.response.MaxValueResponseDto;

public interface FileService {

    MaxValueResponseDto getMaxNumber(FindMaxDto findMaxDto);

}
