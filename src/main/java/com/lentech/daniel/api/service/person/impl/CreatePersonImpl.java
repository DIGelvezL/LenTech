package com.lentech.daniel.api.service.person.impl;

import com.lentech.daniel.api.service.person.api.ICreatePerson;
import com.lentech.daniel.api.service.person.model.Person;
import com.lentech.daniel.api.service.person.port.CreatePersonExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonImpl implements ICreatePerson {

    @Autowired
    @Qualifier("sqlPersonRepository")
    private CreatePersonExternalPort createPersonExternalPort;

    @Override
    public void createPerson(Person person) {

        createPersonExternalPort.createPerson(person);
    }
}
