package com.lentech.daniel.api.service.position.port;

import com.lentech.daniel.api.service.position.model.Position;

import java.util.List;

public interface SearchPositionExternalPort {

    Position findById(Integer id);

    List<Position> findAll();
}
