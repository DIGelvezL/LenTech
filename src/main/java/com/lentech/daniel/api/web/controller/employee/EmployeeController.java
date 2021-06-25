package com.lentech.daniel.api.web.controller.employee;

import com.lentech.daniel.api.delegate.employee.EmployeeDelegate;
import com.lentech.daniel.api.web.dto.request.EmployeeDto;
import com.lentech.daniel.api.web.dto.response.ILenTechResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/employee")
@Api(value = "Employee microservice", description = "This is Employee's API")
@Validated
public class EmployeeController {

    private static final String API_KEY = "key";

    @Autowired
    private EmployeeDelegate employeeDelegate;

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json", headers = API_KEY)
    @ApiOperation(value = "Create", notes = "Create Employees")
    public @ResponseBody
    ResponseEntity<ILenTechResponse> create(
            @RequestBody EmployeeDto employeeDto,

            WebRequest webRequest
    ) {

        return employeeDelegate.createEmployee(employeeDto, webRequest.getHeader(API_KEY));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            consumes = "application/json", produces = "application/json", headers = API_KEY)
    @ApiOperation(value = "Update", notes = "Update Employees")
    public @ResponseBody
    ResponseEntity<ILenTechResponse> update(
            @RequestBody EmployeeDto employeeDto,

            WebRequest webRequest
    ) {

        return employeeDelegate.updateEmployee(employeeDto, webRequest.getHeader(API_KEY));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,
            consumes = "application/json", produces = "application/json", headers = API_KEY)
    @ApiOperation(value = "Delete", notes = "Delete Employees")
    public @ResponseBody
    ResponseEntity<ILenTechResponse> deleteEmployee(
            @RequestBody EmployeeDto employeeDto,

            WebRequest webRequest
    ) {

        return employeeDelegate.deleteEmployee(employeeDto, webRequest.getHeader(API_KEY));
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = "application/json", headers = API_KEY)
    @ApiOperation(value = "Find", notes = "Find Employees")
    public @ResponseBody
    ResponseEntity<ILenTechResponse> find(
            @ApiParam(value = "Find employees by position Id")
            @RequestParam(name = "positionId", required = false) Integer positionId,

            @ApiParam(value = "Find employees by position person Name")
            @RequestParam(name = "personName", required = false) String personName,

            WebRequest webRequest
    ) {

        return employeeDelegate.findAll(positionId, personName, webRequest.getHeader(API_KEY));
    }
}
