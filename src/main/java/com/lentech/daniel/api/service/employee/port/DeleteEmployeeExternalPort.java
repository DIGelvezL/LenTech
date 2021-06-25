package com.lentech.daniel.api.service.employee.port;

import com.lentech.daniel.api.service.employee.model.Employee;

public interface DeleteEmployeeExternalPort {

    void deleteEmployee(Employee employee);
}
