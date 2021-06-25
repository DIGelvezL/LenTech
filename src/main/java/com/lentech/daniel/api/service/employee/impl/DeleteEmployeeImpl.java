package com.lentech.daniel.api.service.employee.impl;

import com.lentech.daniel.api.service.employee.api.IDeleteEmployee;
import com.lentech.daniel.api.service.employee.model.Employee;
import com.lentech.daniel.api.service.employee.port.DeleteEmployeeExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DeleteEmployeeImpl implements IDeleteEmployee {

    @Autowired
    @Qualifier("sqlEmployeeRepository")
    private DeleteEmployeeExternalPort deleteEmployeeExternalPort;

    @Override
    public void deleteEmployee(Employee employee) {

        deleteEmployeeExternalPort.deleteEmployee(employee);
    }
}
