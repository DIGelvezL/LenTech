package com.lentech.daniel.api.service.employee.impl.search;

import com.lentech.daniel.api.service.employee.api.search.ISearchEmployeeByPersonName;
import com.lentech.daniel.api.service.employee.model.Employee;
import com.lentech.daniel.api.service.employee.port.SearchEmployeeExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchEmployeeByPersonNameImpl implements ISearchEmployeeByPersonName {

    @Autowired
    @Qualifier("sqlEmployeeRepository")
    private SearchEmployeeExternalPort searchEmployeeExternalPort;

    @Override
    public List<Employee> searchEmployee(String personName) {

        return searchEmployeeExternalPort.searchEmployeeByPersonName(personName);
    }
}
