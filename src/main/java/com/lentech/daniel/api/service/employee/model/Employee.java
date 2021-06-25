package com.lentech.daniel.api.service.employee.model;

import com.lentech.daniel.api.service.person.model.Person;
import com.lentech.daniel.api.service.position.model.Position;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Employee {

    @ApiModelProperty(position = 0)
    private Integer id;

    @ApiModelProperty(position = 1)
    private double salary;

    @ApiModelProperty(position = 2)
    private Person person;

    @ApiModelProperty(position = 3)
    private Position position;

    @ApiModelProperty(position = 4)
    private String origin;
}
