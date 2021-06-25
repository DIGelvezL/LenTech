package com.lentech.daniel.api.service.person.api;

import com.lentech.daniel.api.exceptions.PersonException;
import com.lentech.daniel.api.service.person.model.Person;

public interface ISearchPerson {

    Person findByName(String name) throws PersonException;

    Person findById(Integer id);
}
