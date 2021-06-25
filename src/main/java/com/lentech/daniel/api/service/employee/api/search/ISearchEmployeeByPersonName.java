package com.lentech.daniel.api.service.employee.api.search;

import com.lentech.daniel.api.service.employee.model.Employee;

import java.util.List;

public interface ISearchEmployeeByPersonName {

    List<Employee> searchEmployee(String personName);
}
