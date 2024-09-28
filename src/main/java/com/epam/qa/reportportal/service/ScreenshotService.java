package com.epam.qa.reportportal.service;

import com.epam.qa.reportportal.utils.LoggerUtils;
import com.epam.qa.reportportal.utils.TestParameters;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ScreenshotService {

    private static final Logger LOGGER = LoggerUtils.getLogger();

    /**
     * Cleans screenshot directory.
     */
    public void cleanScreenshots() {
        File screenshotFolder = new File(TestParameters.SCREENSHOT_PATH);
        if (screenshotFolder.exists()) {
            try {
                FileUtils.cleanDirectory(screenshotFolder);
                LOGGER.info("Screenshots folder [{}] has been cleaned", screenshotFolder.getAbsolutePath());
            } catch (IOException e) {
                LOGGER.info("Error on cleaning screenshots folder", e);
            }
        }
    }
}
