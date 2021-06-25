package com.lentech.daniel.api.service.employee.port;

import com.lentech.daniel.api.exceptions.EmployeeException;
import com.lentech.daniel.api.service.employee.model.Employee;

import java.util.List;

public interface SearchEmployeeExternalPort {

    Integer searchEmployeeIdById(Integer employeeId) throws EmployeeException;

    List<Employee> findAll();

    List<Employee> searchEmployeeByPosition(Integer id);

    List<Employee> searchEmployeeByPersonName(String personName);
}
