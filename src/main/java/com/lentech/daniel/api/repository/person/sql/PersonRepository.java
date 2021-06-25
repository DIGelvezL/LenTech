package com.lentech.daniel.api.repository.person.sql;

import com.lentech.daniel.api.repository.person.sql.orm.PersonOrm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonOrm, Integer> {

    PersonOrm findByName(String name);
}
