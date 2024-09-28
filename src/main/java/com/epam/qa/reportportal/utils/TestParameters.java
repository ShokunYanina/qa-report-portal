package com.epam.qa.reportportal.utils;

public final class TestParameters {

    /**
     * The constant STORY_TO_RUN.
     */
    public static final String STORY_TO_RUN = System.getProperty("qaa.story", "**/*");

    /**
     * The constant META_FILTERS.
     */
    public static final String META_FILTERS =
            System.getProperty("qaa.metaFilters", "-make-screenshot -ignore -ignoreUsualRun -skip");

    /**
     * Path to the directory which should contain screenshots.
     */
    public static final String SCREENSHOT_PATH = System.getProperty("qaa.screenshot.path", "build/reports/tests");

}
