package com.lentech.daniel.api.repository.position.sql.impl;

import com.lentech.daniel.api.repository.position.sql.PositionRepository;
import com.lentech.daniel.api.repository.position.sql.orm.PositionOrm;
import com.lentech.daniel.api.service.position.model.Position;
import com.lentech.daniel.api.service.position.port.SearchPositionExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Qualifier("sqlPositionRepository")
public class PositionSqlRepositoryImpl implements SearchPositionExternalPort {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Position findById(Integer id) {

        return positionRepository.findById(id).map(PositionOrm::toModel).orElse(null);
    }

    @Override
    public List<Position> findAll() {

        return positionRepository.searchPositions().stream().distinct().map(PositionOrm::toModel).collect(Collectors.toList());
    }
}
