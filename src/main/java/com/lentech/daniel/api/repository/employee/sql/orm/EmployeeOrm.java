package com.lentech.daniel.api.repository.employee.sql.orm;

import com.lentech.daniel.api.repository.person.sql.orm.PersonOrm;
import com.lentech.daniel.api.repository.position.sql.orm.PositionOrm;
import com.lentech.daniel.api.service.employee.model.Employee;
import com.lentech.daniel.api.service.person.model.Person;
import com.lentech.daniel.api.service.position.model.Position;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@NamedQuery(name = "EmployeeOrm.findAll", query = "SELECT e FROM EmployeeOrm e")
public class EmployeeOrm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double salary;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private PersonOrm personOrm;

    @ManyToOne
    @JoinColumn(name = "id_position")
    private PositionOrm positionOrm;

    public EmployeeOrm() {
    }

    public EmployeeOrm(Employee employee) {
        this.id = employee.getId();
        this.salary = employee.getSalary();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonOrm getPersonOrm() {
        return personOrm;
    }

    public void setPersonOrm(PersonOrm personOrm) {
        this.personOrm = personOrm;
    }

    public PositionOrm getPositionOrm() {
        return positionOrm;
    }

    public void setPositionOrm(PositionOrm positionOrm) {
        this.positionOrm = positionOrm;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void addPersonOrm(Person Person) {
        this.personOrm = new PersonOrm(Person);
    }

    public void addPositionOrm(Position position) {
        this.positionOrm = new PositionOrm(position);
    }

    public Employee toModel() {
        return Employee.builder()
                .id(this.id)
                .person(this.personOrm.toModel())
                .position(this.positionOrm.toModelWhitoutEmployee())
                .salary(this.salary)
                .origin("sql")
                .build();
    }

    public Employee toModelWithoutPosition() {
        return Employee.builder()
                .id(this.id)
                .salary(this.salary)
                .person(this.personOrm.toModel())
                .origin("sql")
                .build();
    }
}
