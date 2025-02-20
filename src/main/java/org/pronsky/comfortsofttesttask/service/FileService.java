package org.pronsky.comfortsofttesttask.service;

import org.pronsky.comfortsofttesttask.service.dto.FindMaxDto;

public interface FileService {

    Long getMax(FindMaxDto findMaxDto);

}
