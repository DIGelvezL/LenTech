package com.lentech.daniel.api.service.person.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@ToString
public class Person {

    @ApiModelProperty(position = 0)
    @NotNull(message = "username.empty.message")
    @Size(min = 1, message = "username.empty.message")
    private Integer id;

    @ApiModelProperty(position = 0)
    private String name;

    @ApiModelProperty(position = 0)
    private String lastName;

    @ApiModelProperty(position = 0)
    private String address;

    @ApiModelProperty(position = 0)
    private String cellphone;

    @ApiModelProperty(position = 0)
    private String cityName;

    @ApiModelProperty(position = 0)
    private String origin;
}
