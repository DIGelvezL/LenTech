package com.lentech.daniel.api.web.validator;

import com.lentech.daniel.api.exceptions.InvalidKeyException;

public interface IKeyValidator {

    void validate(String key) throws InvalidKeyException;
}
