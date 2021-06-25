package com.lentech.daniel.api.repository.position.sql;

import com.lentech.daniel.api.repository.position.sql.orm.PositionOrm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends CrudRepository<PositionOrm, Integer> {

    @Query("SELECT p FROM PositionOrm p Join p.employees e")
    List<PositionOrm> searchPositions();
}
