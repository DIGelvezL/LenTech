package com.lentech.daniel.api.web.dto.response;

import com.lentech.daniel.api.service.employee.model.Employee;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class EmployeeResponseDto implements ILenTechResponse {

    @ApiModelProperty(position = 0)
    private List<Employee> employees;

    @ApiModelProperty(position = 1)
    private String errorMessage;
}
