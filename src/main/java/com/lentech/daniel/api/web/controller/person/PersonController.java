package com.lentech.daniel.api.web.controller.person;

import com.lentech.daniel.api.delegate.person.PersonDelegate;
import com.lentech.daniel.api.service.person.model.Person;
import com.lentech.daniel.api.web.dto.response.ILenTechResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/person")
@Api(value = "Person microservice", description = "This is Person's API")
@Validated
public class PersonController {

    private static final String API_KEY = "key";

    @Autowired
    private PersonDelegate personDelegate;

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json", headers = API_KEY)
    @ApiOperation(value = "Create", notes = "Create Persons")
    public @ResponseBody
    ResponseEntity<ILenTechResponse> create(@RequestBody Person person, WebRequest webRequest) {

        return personDelegate.createPerson(person, webRequest.getHeader(API_KEY));
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json", headers = API_KEY)
    @ApiOperation(value = "FindByName", notes = "Find Persons by name")
    public @ResponseBody
    ResponseEntity<ILenTechResponse> findByName(@RequestBody Person person, WebRequest webRequest) {

        return personDelegate.findByName(person.getName(), webRequest.getHeader(API_KEY));
    }
}
