package com.lentech.daniel.api.configuration.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LenTechApiKeysProperties {

    private final Logger logger = LoggerFactory.getLogger(LenTechApiKeysProperties.class);
    private static final String API_KEYS_PROPERTIES = "apikeys.properties";

    private static LenTechApiKeysProperties instance;

    private Properties properties;

    private LenTechApiKeysProperties() {
        try {
            properties = new Properties();
            InputStream inputStream = LenTechApiKeysProperties.class.getClassLoader().getResourceAsStream(API_KEYS_PROPERTIES);
            properties.load(inputStream);
        } catch (IOException e) {
            logger.info("Unable to read pom file for AIMate properties, will use system and environment properties.");
            properties = new Properties();
        }
    }

    public static synchronized LenTechApiKeysProperties getInstance() {
        if(instance == null) {
            instance = new LenTechApiKeysProperties();
        }
        return instance;
    }

    public String getString(String propertyName, String defaultValue) {
        String value = System.getenv(propertyName);
        if (null != value) {
            return value;
        }
        value = System.getProperty(propertyName);
        if (null != value) {
            return value;
        }
        value = properties.getProperty(propertyName);
        if (null != value) {
            return value;
        }
        return defaultValue;
    }

    public String getString(String propertyName) {
        return getString(propertyName, null);
    }

    public int getInteger(String propertyName, int defaultValue) {
        String value = getString(propertyName);
        if (value != null) {
            return Integer.parseInt(value);
        } else {
            return defaultValue;
        }
    }

    public int getInteger(String propertyName) {
        return Integer.parseInt(getString(propertyName));
    }

    public double getDouble(String propertyName, double defaultValue) {
        String value = getString(propertyName);
        if (value != null) {
            return Double.parseDouble(value);
        } else {
            return defaultValue;
        }
    }

    public double getDouble(String propertyName) {
        return Double.parseDouble(getString(propertyName));
    }

    public boolean getBoolean(String propertyName, boolean defaultValue) {
        String value = getString(propertyName);
        if (value != null) {
            return Boolean.parseBoolean(value);
        } else {
            return defaultValue;
        }
    }

    public boolean getBoolean(String propertyName) {
        return Boolean.parseBoolean(getString(propertyName));
    }

    public void setProperty(String propertyName, String value) {
        properties.setProperty(propertyName, value);
    }
}
