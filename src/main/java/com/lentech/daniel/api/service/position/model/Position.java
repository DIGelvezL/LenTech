package com.lentech.daniel.api.service.position.model;

import com.lentech.daniel.api.service.employee.model.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class Position {

    private Integer id;

    private String name;

    private List<Employee> employees;

    private String origin;
}
