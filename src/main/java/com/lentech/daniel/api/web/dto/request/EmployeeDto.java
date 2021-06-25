package com.lentech.daniel.api.web.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class EmployeeDto {

    @ApiModelProperty(position = 0)
    private Integer id;

    @ApiModelProperty(position = 1)
    private double salary;

    @ApiModelProperty(position = 2)
    private Integer personId;

    @ApiModelProperty(position = 3)
    private Integer positionId;
}
