package com.lentech.daniel.api.repository.employee.sql.impl;

import com.lentech.daniel.api.exceptions.EmployeeException;
import com.lentech.daniel.api.repository.employee.sql.EmployeeRepository;
import com.lentech.daniel.api.repository.employee.sql.orm.EmployeeOrm;
import com.lentech.daniel.api.service.employee.model.Employee;
import com.lentech.daniel.api.service.employee.port.CreateEmployeeExternalPort;
import com.lentech.daniel.api.service.employee.port.DeleteEmployeeExternalPort;
import com.lentech.daniel.api.service.employee.port.SearchEmployeeExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Qualifier("sqlEmployeeRepository")
public class EmployeeSqlRepositoryImpl implements CreateEmployeeExternalPort, SearchEmployeeExternalPort, DeleteEmployeeExternalPort {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employee) {
        EmployeeOrm employeeOrm = new EmployeeOrm(employee);
        employeeOrm.addPersonOrm(employee.getPerson());
        employeeOrm.addPositionOrm(employee.getPosition());

        employeeRepository.save(employeeOrm);
    }

    @Override
    public Integer searchEmployeeIdById(Integer employeeId) throws EmployeeException {

        Integer idEmployee = employeeRepository.searchEmployeeIdById(employeeId);

        if (Objects.nonNull(idEmployee)) {
            return idEmployee;
        }

        throw new EmployeeException("Employee not found");
    }

    @Override
    public void deleteEmployee(Employee employee) {

        EmployeeOrm employeeOrm = new EmployeeOrm(employee);
        employeeRepository.delete(employeeOrm);
    }

    @Override
    public List<Employee> findAll() {

        return ((List<EmployeeOrm>) employeeRepository.findAll()).stream().map(EmployeeOrm::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Employee> searchEmployeeByPosition(Integer positionId) {

        return employeeRepository.searchEmployeeByPosition(positionId).stream().map(EmployeeOrm::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Employee> searchEmployeeByPersonName(String personName) {

        return employeeRepository.searchEmployeeByPersonName(personName).stream().map(EmployeeOrm::toModel).collect(Collectors.toList());
    }
}
