package com.epam.qa.reportportal.webdriver;

import com.codeborne.selenide.WebDriverProvider;
import com.epam.qa.reportportal.utils.LoggerUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.Map;

public class ChromeDriverProvider implements WebDriverProvider {

    private static final Logger LOGGER = LoggerUtils.getLogger();

    public static final String DOWNLOAD_PATH = "";


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        WebDriverManager.chromedriver().clearDriverCache().cachePath("./build/cache");
        WebDriver webDriver = null;
        int retryCount = 0;
        while (null == webDriver) {
            try {
                LOGGER.info("Attempting to create Chrome WebDriver, attempt {}", retryCount + 1);
                webDriver = new ChromeDriver(getChromeOptions());
                webDriver.manage().window().maximize();
                LOGGER.info("Chrome WebDriver created successfully on attempt {}", retryCount + 1);
            } catch (Exception e) {
                 if (retryCount > 2) {
                     LOGGER.error("Failed to create Chrome WebDriver after {} attempts", retryCount + 1, e);
                     throw e;
                }
                LOGGER.warn("Unable to create Chrome web driver.", e);
                retryCount++;
            }
        }
        webDriver.manage().window().maximize();
        LOGGER.info("Chrome WebDriver successfully created and maximized.");
        return webDriver;
    }

    /**
     * Provides chrome options.
     *
     * @return chrome options
     */
    protected ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of("profile.default_content_settings.popups", 0,
                "download.default_directory", DOWNLOAD_PATH));
        options.addArguments("--test-type");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-print-preview");
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.setAcceptInsecureCerts(true);
        options.setExperimentalOption("w3c", true);
        return options;
    }
}
