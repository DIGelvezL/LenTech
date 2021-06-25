package com.lentech.daniel.api.web.dto.response;

import com.lentech.daniel.api.service.position.model.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PositionResponseDto extends ResponseDto {

    private Position position;

    public PositionResponseDto(Integer code, String message, String errorMessage, Position position) {
        super(code, message, errorMessage);
        this.position = position;
    }
}
