package com.lentech.daniel.api.repository.position.sql.orm;

import com.lentech.daniel.api.repository.employee.sql.orm.EmployeeOrm;
import com.lentech.daniel.api.service.position.model.Position;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "positionTable")
@NamedQuery(name = "PositionOrm.findAll", query = "SELECT p FROM PositionOrm p")
public class PositionOrm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "positionOrm")
    private List<EmployeeOrm> employees;

    public PositionOrm() {
    }

    public PositionOrm(Position position) {
        this.id = position.getId();
        this.name = position.getName();
        this.employees = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeOrm> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeOrm> employees) {
        this.employees = employees;
    }

    public EmployeeOrm addEmployeeOrm(EmployeeOrm employeeOrm) {
        getEmployees().add(employeeOrm);
        employeeOrm.setPositionOrm(this);

        return employeeOrm;
    }

    public EmployeeOrm removeEmployeeOrm(EmployeeOrm employeeOrm) {
        getEmployees().remove(employeeOrm);
        employeeOrm.setPositionOrm(null);

        return employeeOrm;
    }

    public Position toModel() {
        return Position.builder()
                .id(this.id)
                .name(this.name)
                .employees(employees.stream()
                        .map(EmployeeOrm::toModelWithoutPosition)
                        .sorted((e1, e2) -> (int) (e2.getSalary() - e1.getSalary()))
                        .collect(Collectors.toList())
                ).origin("sql")
                .build();
    }

    public Position toModelWhitoutEmployee() {
        return Position.builder()
                .id(this.id)
                .name(this.name)
                .origin("sql")
                .build();
    }
}
