package com.lentech.daniel.api.service.position.impl;

import com.lentech.daniel.api.service.position.api.ISearchPosition;
import com.lentech.daniel.api.service.position.model.Position;
import com.lentech.daniel.api.service.position.port.SearchPositionExternalPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchPositionImpl implements ISearchPosition {

    @Autowired
    @Qualifier("sqlPositionRepository")
    private SearchPositionExternalPort searchPositionExternalPort;

    @Override
    public Position findById(Integer id) {
        return searchPositionExternalPort.findById(id);
    }

    @Override
    public List<Position> findAll() {

        return searchPositionExternalPort.findAll();
    }
}
