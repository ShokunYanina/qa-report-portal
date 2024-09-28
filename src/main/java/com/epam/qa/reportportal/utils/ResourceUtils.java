package com.epam.qa.reportportal.utils;

import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.net.URL;

public class ResourceUtils {

    private static final Logger LOGGER = LoggerUtils.getLogger();

    /**
     * Retrieves the base URL of the resources directory, specifically for the "stories" folder.
     * <p>
     * If the resources are packed inside a JAR file, it adjusts the URL accordingly
     * to point to the JAR file location. If the resources are located in the file system,
     * it returns the appropriate file URL.
     * </p>
     *
     * @return The base URL of the resources' directory.
     * @throws RuntimeException if the resources could not be located.
     */
    public static URL getResourcesURL() {
        LOGGER.info("Attempting to retrieve the base URL of the 'stories' folder");
        try {
            String url = new ClassPathResource("stories").getURL().toString();
            LOGGER.debug("Initial URL retrieved: {}", url);
            url = url.substring(0, url.length() - "/stories".length());
            LOGGER.debug("URL after removing '/stories': {}", url);
            if (url.startsWith("jar:")) {
                LOGGER.debug("Detected URL inside a JAR file, adjusting the URL");
                url = url.substring("jar:".length());
                LOGGER.debug("URL after removing 'jar:' prefix: {}", url);
                if (url.endsWith(".jar!")) {
                    url = url.substring(0, url.length() - 1);
                    LOGGER.debug("URL after removing trailing '!': {}", url);
                }
            }
            URL finalURL = new URL(url);
            LOGGER.info("Successfully retrieved the base URL: {}", finalURL);
            return finalURL;
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve the resources URL", e);
            throw new RuntimeException("Resources not found", e);
        }
    }
}
