package com.lentech.daniel.api.service.person.port;

import com.lentech.daniel.api.exceptions.PersonException;
import com.lentech.daniel.api.service.person.model.Person;

public interface SearchPersonExternalPort {

    Person findByName(String name) throws PersonException;

    Person findById(Integer id);
}
