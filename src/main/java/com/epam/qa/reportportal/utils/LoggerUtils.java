package com.epam.qa.reportportal.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

    /**
     * Returns a logger instance for the calling class.
     *
     * @return Logger instance for the calling class.
     */
    public static Logger getLogger() {
        final Throwable t = new Throwable();
        t.fillInStackTrace();
        final String clazz = t.getStackTrace()[1].getClassName();
        return LoggerFactory.getLogger(clazz);
    }
}
