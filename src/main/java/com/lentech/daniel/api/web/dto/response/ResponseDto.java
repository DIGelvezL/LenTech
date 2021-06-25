package com.lentech.daniel.api.web.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ResponseDto implements ILenTechResponse {
    protected Integer code;
    protected String message;
    protected String errorMessage;

    public ResponseDto(Integer code, String message, String errorMessage) {
        this.code = code;
        this.message = message;
        this.errorMessage = errorMessage;
    }
}
