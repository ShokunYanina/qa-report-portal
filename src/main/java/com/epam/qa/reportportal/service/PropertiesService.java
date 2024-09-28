package com.epam.qa.reportportal.service;

import com.epam.qa.reportportal.utils.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class PropertiesService {

    private static final Logger LOGGER = LoggerUtils.getLogger();

    private Properties properties;

    /**
     * Constructs a new {@code PropertiesService} and loads the properties from
     * the configuration file.
     */
    public PropertiesService() {
        properties = new Properties();
        loadProperties();
    }

    /**
     * Loads properties from the {@code config.properties} file located in the classpath.
     * If the properties file cannot be found, a message is printed to the console.
     * Any {@link IOException} encountered while loading the properties will be printed
     * to the stack trace.
     */
    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                LOGGER.debug("Unable to find config.properties");
                return;
            }
            properties.load(input);
            LOGGER.info("Properties loaded successfully from config.properties");
        } catch (IOException ex) {
            LOGGER.error("Error loading properties file", ex);
        }
    }

    /**
     * Resolves the property value associated with the given key.
     *
     * @param key the property key to resolve.
     * @return the property value associated with the specified key, or {@code null}
     *         if the key is {@code null} or if the property cannot be found.
     */
    public String resolveProperty(String key) {
        if (key == null) {
            return null;
        }
        String result = properties.getProperty(key);
        if (result == null) {
            result = System.getProperty(key);
        }
        if (result == null) {
            result = System.getenv(key);
        }
        LOGGER.debug("Resolved property '{}' to '{}'", key, result);
        return result;

    }
}
