package com.lentech.daniel.api.delegate.position;

import com.lentech.daniel.api.exceptions.InvalidKeyException;
import com.lentech.daniel.api.exceptions.PositionException;
import com.lentech.daniel.api.service.position.api.ISearchPosition;
import com.lentech.daniel.api.service.position.model.Position;
import com.lentech.daniel.api.web.dto.response.ILenTechResponse;
import com.lentech.daniel.api.web.dto.response.PistionResponseDto;
import com.lentech.daniel.api.web.dto.response.PositionResponseDto;
import com.lentech.daniel.api.web.dto.response.ResponseDto;
import com.lentech.daniel.api.web.validator.IKeyValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PositionDelegate {

    @Autowired
    private IKeyValidator keyValidator;
    @Autowired
    private ISearchPosition searchPosition;

    private static final Logger logger = LogManager.getLogger(PositionDelegate.class);

    public ResponseEntity<ILenTechResponse> findById(Integer id, String key) {

        try {
            keyValidator.validate(key);

            if (Objects.nonNull(id)) {
                Position position = searchPosition.findById(id);

                return new ResponseEntity<>(new PositionResponseDto(200, "Ok", Strings.EMPTY, position), HttpStatus.OK);

            } else {
                throw new PositionException("Position not found");
            }
        } catch (InvalidKeyException e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder()
                    .errorMessage(e.getMessage())
                    .build(), HttpStatus.FORBIDDEN);
        } catch (PositionException e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder()
                    .errorMessage(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder().errorMessage(e.toString()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ILenTechResponse> findAll(String key) {

        try {
            keyValidator.validate(key);

            return new ResponseEntity<>(PistionResponseDto.builder().positions(searchPosition.findAll()).build(), HttpStatus.OK);

        } catch (InvalidKeyException e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder()
                    .errorMessage(e.getMessage())
                    .build(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            addLog(e);
            return new ResponseEntity<>(PistionResponseDto.builder().errorMessage(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addLog(Exception e) {
        logger.info(e.getMessage());
        logger.error(e.getMessage(), e);
    }
}
