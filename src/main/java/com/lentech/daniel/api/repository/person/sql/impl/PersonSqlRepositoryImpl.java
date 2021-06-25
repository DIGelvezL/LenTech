package com.lentech.daniel.api.repository.person.sql.impl;

import com.lentech.daniel.api.exceptions.PersonException;
import com.lentech.daniel.api.repository.person.sql.PersonRepository;
import com.lentech.daniel.api.repository.person.sql.orm.PersonOrm;
import com.lentech.daniel.api.service.person.model.Person;
import com.lentech.daniel.api.service.person.port.CreatePersonExternalPort;
import com.lentech.daniel.api.service.person.port.SearchPersonExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@Qualifier("sqlPersonRepository")
public class PersonSqlRepositoryImpl implements CreatePersonExternalPort, SearchPersonExternalPort {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void createPerson(Person person) {

        personRepository.save(new PersonOrm(person));
    }

    @Override
    public Person findByName(String name) throws PersonException {

        PersonOrm personOrm = personRepository.findByName(name);

        if (Objects.nonNull(personOrm)) {
            return personOrm.toModel();
        }

        throw new PersonException("Person not found");
    }

    @Override
    public Person findById(Integer id) {

        return personRepository.findById(id).map(PersonOrm::toModel).orElse(null);
    }
}
