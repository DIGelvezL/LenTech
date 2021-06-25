package com.lentech.daniel.api.service.employee.api.search;

import com.lentech.daniel.api.exceptions.EmployeeException;

public interface ISearchEmployeeIdById {

    Integer searchEmployee(Integer employeeid) throws EmployeeException;
}
