package com.lentech.daniel.api.service.person.impl;

import com.lentech.daniel.api.exceptions.PersonException;
import com.lentech.daniel.api.service.person.api.ISearchPerson;
import com.lentech.daniel.api.service.person.model.Person;
import com.lentech.daniel.api.service.person.port.SearchPersonExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SearchPersonImpl implements ISearchPerson {

    @Autowired
    @Qualifier("sqlPersonRepository")
    private SearchPersonExternalPort searchPersonExternalPort;

    @Override
    public Person findByName(String name) throws PersonException {
        return searchPersonExternalPort.findByName(name);
    }

    @Override
    public Person findById(Integer id) {
        return searchPersonExternalPort.findById(id);
    }
}
