package com.lentech.daniel.api.repository.employee.sql;

import com.lentech.daniel.api.exceptions.EmployeeException;
import com.lentech.daniel.api.repository.employee.sql.orm.EmployeeOrm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeOrm, Integer> {

    @Query("select e.id from EmployeeOrm e where e.id = :employeeId")
    Integer searchEmployeeIdById(Integer employeeId) throws EmployeeException;

    @Query("select e from EmployeeOrm e where e.positionOrm.id = :positionId")
    List<EmployeeOrm> searchEmployeeByPosition(Integer positionId);

    @Query("select e from EmployeeOrm e where e.personOrm.name = :personName")
    List<EmployeeOrm> searchEmployeeByPersonName(String personName);
}
