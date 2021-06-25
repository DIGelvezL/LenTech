package com.lentech.daniel.api.web.validator;

import com.lentech.daniel.api.configuration.properties.LenTechApiKeysProperties;
import com.lentech.daniel.api.exceptions.InvalidKeyException;
import com.lentech.daniel.api.exceptions.PropertiesException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class KeyValidator implements IKeyValidator {

    private LenTechApiKeysProperties properties;

    private static final String LEN_TECH_KEY = "len.tech.key";

    public KeyValidator() throws PropertiesException {
        properties = LenTechApiKeysProperties.getInstance();
    }

    public void validate(String key) throws InvalidKeyException {
        String keyProperties = properties.getString(LEN_TECH_KEY);

        if (Objects.isNull(keyProperties) || keyProperties.isEmpty()) {
            throw new InvalidKeyException("There are not a key setted");
        }

        if (!key.equals(keyProperties)) {
            throw new InvalidKeyException("Provided key is not valid");
        }
    }
}
