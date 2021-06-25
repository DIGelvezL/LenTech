package com.lentech.daniel.api.test;

import com.lentech.daniel.api.delegate.employee.EmployeeDelegate;
import com.lentech.daniel.api.exceptions.PropertiesException;
import com.lentech.daniel.api.service.employee.api.ICreateEmployee;
import com.lentech.daniel.api.service.employee.impl.CreateEmployeeImpl;
import com.lentech.daniel.api.service.employee.port.CreateEmployeeExternalPort;
import com.lentech.daniel.api.service.person.api.ISearchPerson;
import com.lentech.daniel.api.service.person.impl.SearchPersonImpl;
import com.lentech.daniel.api.service.person.port.SearchPersonExternalPort;
import com.lentech.daniel.api.web.dto.request.EmployeeDto;
import com.lentech.daniel.api.web.dto.response.ILenTechResponse;
import com.lentech.daniel.api.web.dto.response.ResponseDto;
import com.lentech.daniel.api.web.validator.IKeyValidator;
import com.lentech.daniel.api.web.validator.KeyValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    EmployeeDelegate employeeDelegate = new EmployeeDelegate();

    @InjectMocks
    ICreateEmployee createEmployee = new CreateEmployeeImpl();
    @InjectMocks
    IKeyValidator keyValidator = new KeyValidator();
    @InjectMocks
    ISearchPerson searchPerson = new SearchPersonImpl();

    @Mock
    CreateEmployeeExternalPort createEmployeeExternalPort;
    @Mock
    SearchPersonExternalPort searchPersonExternalPort;

    private String key = "7bb9f19204d26ec2";

    public EmployeeControllerTest() throws PropertiesException {
    }

    @Before
    public void main() {
        ReflectionTestUtils.setField(employeeDelegate, "createEmployee", createEmployee);
        ReflectionTestUtils.setField(employeeDelegate, "keyValidator", keyValidator);
        ReflectionTestUtils.setField(employeeDelegate, "searchPerson", searchPerson);
        ReflectionTestUtils.setField(searchPerson, "searchPersonExternalPort", searchPersonExternalPort);
        ReflectionTestUtils.setField(createEmployee, "createEmployeeExternalPort", createEmployeeExternalPort);
    }

    @Test
    public void createEmployeeInvalidKey() {

        ResponseEntity<ILenTechResponse> lenTechResponse = employeeDelegate.createEmployee(EmployeeDto.builder().build(), "");
        assertEquals(HttpStatus.FORBIDDEN, lenTechResponse.getStatusCode());
        assertEquals("Provided key is not valid", ((ResponseDto) lenTechResponse.getBody()).getErrorMessage());
    }

    @Test
    public void createEmployeeRequestNull() {

        ResponseEntity<ILenTechResponse> lenTechResponse = employeeDelegate.createEmployee(null, key);
        assertEquals(HttpStatus.NOT_FOUND, lenTechResponse.getStatusCode());
        assertEquals("Error saving employee", ((ResponseDto) lenTechResponse.getBody()).getErrorMessage());
    }

    @Test
    public void createEmployeePersonNonFound() {

        Mockito.when(searchPersonExternalPort.findById(Mockito.anyInt())).thenReturn(null);

        ResponseEntity<ILenTechResponse> lenTechResponse = employeeDelegate.createEmployee(
                EmployeeDto.builder()
                        .salary(8400)
                        .personId(1)
                        .positionId(1)
                        .build(),
                key
        );
        assertEquals(HttpStatus.NOT_FOUND, lenTechResponse.getStatusCode());
        assertEquals("Person not found", ((ResponseDto) lenTechResponse.getBody()).getErrorMessage());
    }
}
