package com.lentech.daniel.api.service.employee.impl.search;

import com.lentech.daniel.api.service.employee.api.search.ISearchEmployeeByPosition;
import com.lentech.daniel.api.service.employee.model.Employee;
import com.lentech.daniel.api.service.employee.port.SearchEmployeeExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchEmployeeByPositionImpl implements ISearchEmployeeByPosition {

    @Autowired
    @Qualifier("sqlEmployeeRepository")
    private SearchEmployeeExternalPort searchEmployeeExternalPort;

    @Override
    public List<Employee> searchEmployee(Integer positionId) {

        return searchEmployeeExternalPort.searchEmployeeByPosition(positionId);
    }
}
