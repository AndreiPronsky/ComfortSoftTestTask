package org.pronsky.comfortsofttesttask.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimpleErrorResponseDto implements ResponseDto {

    private String message;
}
