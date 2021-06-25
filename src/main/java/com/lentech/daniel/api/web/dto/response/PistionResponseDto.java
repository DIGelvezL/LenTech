package com.lentech.daniel.api.web.dto.response;

import com.lentech.daniel.api.service.position.model.Position;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class PistionResponseDto implements ILenTechResponse {

    private List<Position> positions;
    private String errorMessage;
}
