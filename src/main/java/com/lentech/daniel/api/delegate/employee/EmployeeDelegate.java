package com.lentech.daniel.api.delegate.employee;

import com.lentech.daniel.api.exceptions.EmployeeException;
import com.lentech.daniel.api.exceptions.InvalidKeyException;
import com.lentech.daniel.api.exceptions.PersonException;
import com.lentech.daniel.api.exceptions.PositionException;
import com.lentech.daniel.api.service.employee.api.ICreateEmployee;
import com.lentech.daniel.api.service.employee.api.IDeleteEmployee;
import com.lentech.daniel.api.service.employee.api.search.ISearchEmployee;
import com.lentech.daniel.api.service.employee.api.search.ISearchEmployeeByPersonName;
import com.lentech.daniel.api.service.employee.api.search.ISearchEmployeeByPosition;
import com.lentech.daniel.api.service.employee.api.search.ISearchEmployeeIdById;
import com.lentech.daniel.api.service.employee.model.Employee;
import com.lentech.daniel.api.service.person.api.ISearchPerson;
import com.lentech.daniel.api.service.person.model.Person;
import com.lentech.daniel.api.service.position.api.ISearchPosition;
import com.lentech.daniel.api.service.position.model.Position;
import com.lentech.daniel.api.web.dto.request.EmployeeDto;
import com.lentech.daniel.api.web.dto.response.EmployeeResponseDto;
import com.lentech.daniel.api.web.dto.response.ILenTechResponse;
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
public class EmployeeDelegate {

    @Autowired
    private IKeyValidator keyValidator;
    @Autowired
    private ICreateEmployee createEmployee;
    @Autowired
    private ISearchPerson searchPerson;
    @Autowired
    private ISearchPosition searchPosition;
    @Autowired
    private ISearchEmployee searchEmployee;
    @Autowired
    private IDeleteEmployee deleteEmployee;
    @Autowired
    private ISearchEmployeeIdById searchEmployeeIdById;
    @Autowired
    private ISearchEmployeeByPosition searchEmployeeByPosition;
    @Autowired
    private ISearchEmployeeByPersonName searchEmployeeByPersonName;

    private static final Logger logger = LogManager.getLogger(EmployeeDelegate.class);

    public ResponseEntity<ILenTechResponse> createEmployee(EmployeeDto employeeDto, String key) {

        try {
            keyValidator.validate(key);

            if (Objects.nonNull(employeeDto)) {
                Person person = searchPerson.findById(employeeDto.getPersonId());

                if (Objects.isNull(person)) {
                    throw new PersonException("Person not found");
                }

                Position position = searchPosition.findById(employeeDto.getPositionId());

                if (Objects.isNull(position)) {
                    throw new PositionException("Position not found");
                }

                createEmployee.createEmployee(
                        Employee.builder()
                                .person(person)
                                .position(position)
                                .salary(employeeDto.getSalary()
                                ).build());

                return new ResponseEntity<>(ResponseDto.builder().code(200).message("Ok").build(), HttpStatus.OK);
            } else {
                throw new PersonException("Error saving employee");
            }
        } catch (InvalidKeyException e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder()
                    .errorMessage(e.getMessage())
                    .build(), HttpStatus.FORBIDDEN);
        } catch (PersonException | PositionException e) {
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

    public ResponseEntity<ILenTechResponse> updateEmployee(EmployeeDto employeeDto, String key) {

        try {
            keyValidator.validate(key);

            if (Objects.nonNull(employeeDto)) {
                Integer employeeId = searchEmployeeIdById.searchEmployee(employeeDto.getId());

                Person person = searchPerson.findById(employeeDto.getPersonId());

                if (Objects.isNull(person)) {
                    throw new PersonException("Person not found");
                }

                Position position = searchPosition.findById(employeeDto.getPositionId());

                if (Objects.isNull(position)) {
                    throw new PositionException("Position not found");
                }

                createEmployee.createEmployee(
                        Employee.builder()
                                .id(employeeId)
                                .person(person)
                                .position(position)
                                .salary(employeeDto.getSalary()
                                ).build());

                return new ResponseEntity<>(ResponseDto.builder().code(200).message("Ok").build(), HttpStatus.OK);
            } else {
                throw new EmployeeException("Error updating employee");
            }
        } catch (InvalidKeyException e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder()
                    .errorMessage(e.getMessage())
                    .build(), HttpStatus.FORBIDDEN);
        } catch (EmployeeException | PersonException | PositionException e) {
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

    public ResponseEntity<ILenTechResponse> deleteEmployee(EmployeeDto employeeDto, String key) {

        try {
            keyValidator.validate(key);

            if (Objects.nonNull(employeeDto)) {
                Integer employeeId = searchEmployeeIdById.searchEmployee(employeeDto.getId());

                deleteEmployee.deleteEmployee(
                        Employee.builder()
                                .id(employeeId)
                                .build());

                return new ResponseEntity<>(ResponseDto.builder().code(200).message("Ok").build(), HttpStatus.OK);
            } else {
                throw new EmployeeException("Error deleting employee");
            }
        } catch (InvalidKeyException e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder()
                    .errorMessage(e.getMessage())
                    .build(), HttpStatus.FORBIDDEN);
        } catch (EmployeeException e) {
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

    public ResponseEntity<ILenTechResponse> findAll(Integer positionId, String personName, String key) {

        try {
            keyValidator.validate(key);

            return new ResponseEntity<>(EmployeeResponseDto.builder().employees(
                    Objects.nonNull(positionId) ? searchEmployeeByPosition.searchEmployee(positionId)
                            : Objects.nonNull(personName) ? searchEmployeeByPersonName.searchEmployee(personName)
                            : searchEmployee.searchEmployee()).build(), HttpStatus.OK);

        } catch (InvalidKeyException e) {
            addLog(e);
            return new ResponseEntity<>(ResponseDto.builder()
                    .errorMessage(e.getMessage())
                    .build(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            addLog(e);
            return new ResponseEntity<>(EmployeeResponseDto.builder().errorMessage(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addLog(Exception e) {
        logger.info(e.getMessage());
        logger.error(e.getMessage(), e);
    }
}
