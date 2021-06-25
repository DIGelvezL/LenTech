package com.lentech.daniel.api.service.employee.impl;

import com.lentech.daniel.api.service.employee.api.ICreateEmployee;
import com.lentech.daniel.api.service.employee.model.Employee;
import com.lentech.daniel.api.service.employee.port.CreateEmployeeExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeImpl implements ICreateEmployee {

    @Autowired
    @Qualifier("sqlEmployeeRepository")
    private CreateEmployeeExternalPort createEmployeeExternalPort;

    @Override
    public void createEmployee(Employee employee) {

        createEmployeeExternalPort.createEmployee(employee);
    }
}
