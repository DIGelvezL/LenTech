package com.lentech.daniel.api.service.position.api;

import com.lentech.daniel.api.service.position.model.Position;

import java.util.List;

public interface ISearchPosition {

    Position findById(Integer id);

    List<Position> findAll();
}
