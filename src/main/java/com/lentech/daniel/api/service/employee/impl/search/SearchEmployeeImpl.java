package com.lentech.daniel.api.service.employee.impl.search;

import com.lentech.daniel.api.service.employee.api.search.ISearchEmployee;
import com.lentech.daniel.api.service.employee.model.Employee;
import com.lentech.daniel.api.service.employee.port.SearchEmployeeExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchEmployeeImpl implements ISearchEmployee {

    @Autowired
    @Qualifier("sqlEmployeeRepository")
    private SearchEmployeeExternalPort searchEmployeeExternalPort;

    @Override
    public List<Employee> searchEmployee() {

        return searchEmployeeExternalPort.findAll();
    }
}
