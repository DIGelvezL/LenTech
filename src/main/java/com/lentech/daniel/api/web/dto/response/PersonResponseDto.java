package com.lentech.daniel.api.web.dto.response;

import com.lentech.daniel.api.service.person.model.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonResponseDto extends ResponseDto {

    protected Person person;

    public PersonResponseDto(Integer code, String message, String errorMessage, Person person) {
        super(code, message, errorMessage);
        this.person = person;
    }
}
