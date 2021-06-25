package com.lentech.daniel.api.service.employee.impl.search;

import com.lentech.daniel.api.exceptions.EmployeeException;
import com.lentech.daniel.api.service.employee.api.search.ISearchEmployeeIdById;
import com.lentech.daniel.api.service.employee.port.SearchEmployeeExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SearchEmployeeIdByIdImpl implements ISearchEmployeeIdById {

    @Autowired
    @Qualifier("sqlEmployeeRepository")
    private SearchEmployeeExternalPort searchEmployeeExternalPort;

    @Override
    public Integer searchEmployee(Integer employeeid) throws EmployeeException {

        return searchEmployeeExternalPort.searchEmployeeIdById(employeeid);
    }
}
