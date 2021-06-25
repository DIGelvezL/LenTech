package com.lentech.daniel.api.repository.person.sql.orm;

import com.lentech.daniel.api.repository.employee.sql.orm.EmployeeOrm;
import com.lentech.daniel.api.service.person.model.Person;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
@NamedQuery(name = "PersonOrm.findAll", query = "SELECT p FROM PersonOrm p")
public class PersonOrm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String lastName;

    private String address;

    private String cellphone;

    private String cityName;

    @OneToMany(mappedBy = "positionOrm")
    private List<EmployeeOrm> employees;

    public PersonOrm() {
    }

    public PersonOrm(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.lastName = person.getLastName();
        this.address = person.getAddress();
        this.cellphone = person.getCellphone();
        this.cityName = person.getCityName();
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<EmployeeOrm> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeOrm> employees) {
        this.employees = employees;
    }

    public Person toModel() {
        return Person.builder()
                .id(this.id)
                .name(this.name)
                .lastName(this.lastName)
                .address(this.address)
                .cellphone(this.cellphone)
                .cityName(this.cityName)
                .origin("sql")
                .build();
    }
}
