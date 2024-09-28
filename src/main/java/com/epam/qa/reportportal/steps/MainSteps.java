package com.epam.qa.reportportal.steps;

import com.epam.qa.reportportal.service.ScreenshotService;
import com.epam.qa.reportportal.utils.LoggerUtils;
import org.jbehave.core.annotations.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainSteps {

    private static final Logger LOGGER = LoggerUtils.getLogger();

    @Autowired
    private ScreenshotService screenshotService;

    /**
     * Cleans the screenshots directory before executing stories.
     */
    @BeforeStories
    public void beforeStories() {
        LOGGER.info("Cleaning screenshots directory...");
        screenshotService.cleanScreenshots();
        LOGGER.info("Screenshots directory cleaned successfully.");
    }
}
