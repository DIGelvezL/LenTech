package com.lentech.daniel.api.delegate.person;

import com.lentech.daniel.api.exceptions.InvalidKeyException;
import com.lentech.daniel.api.exceptions.PersonException;
import com.lentech.daniel.api.service.person.api.ICreatePerson;
import com.lentech.daniel.api.service.person.api.ISearchPerson;
import com.lentech.daniel.api.service.person.model.Person;
import com.lentech.daniel.api.web.dto.response.ILenTechResponse;
import com.lentech.daniel.api.web.dto.response.PersonResponseDto;
import com.lentech.daniel.api.web.dto.response.ResponseDto;
import com.lentech.daniel.api.web.validator.IKeyValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PersonDelegate {

    @Autowired
    private IKeyValidator keyValidator;

    @Autowired
    private ICreatePerson createPerson;

    @Autowired
    private ISearchPerson searchPerson;

    private static final Logger logger = LogManager.getLogger(PersonDelegate.class);

    public ResponseEntity<ILenTechResponse> createPerson(Person person, String key) {

        try {
            keyValidator.validate(key);

            if (Objects.nonNull(person)) {
                createPerson.createPerson(person);

                return new ResponseEntity<>(ResponseDto.builder().code(200).message("Ok").build(), HttpStatus.OK);
            } else {
                throw new PersonException("Error saving person");
            }
        } catch (InvalidKeyException e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder()
                    .errorMessage(e.getMessage())
                    .build(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder().errorMessage(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ILenTechResponse> findByName(String name, String key) {

        try {
            keyValidator.validate(key);

            if (Objects.nonNull(name) && !name.isEmpty()) {
                Person person = searchPerson.findByName(name);

                return new ResponseEntity<>(new PersonResponseDto(200, "Ok", "", person), HttpStatus.OK);
            } else {
                throw new PersonException("Person not found");
            }
        } catch (InvalidKeyException e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder()
                    .errorMessage(e.getMessage())
                    .build(), HttpStatus.FORBIDDEN);
        } catch (PersonException e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder()
                    .errorMessage(e.getMessage())
                    .build(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder().errorMessage(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addLog(Exception e) {
        logger.info(e.getMessage());
        logger.error(e.getMessage(), e);
    }
}
