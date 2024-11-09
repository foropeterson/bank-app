package com.example.banking_system.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class LoggerUtil {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    // Define your custom log format
    public  void info(String process, int responseCode, String responseMessage, String errorMessage, long timeTaken) {
        // Get current datetime in the desired format

        // Format time taken in milliseconds
        String timeTakenFormatted = String.format("%dms", timeTaken / 1_000_000);  // Convert to milliseconds

        // Build the log message in the desired format
        String logMessage = String.format("%s process=%s, responseCode=%d, responseMessage=%s, errorMessage=%s, timeTaken=%s",
                datetime, process, responseCode, responseMessage, errorMessage != null ? errorMessage : "null", timeTakenFormatted);

        // Log the message (you can choose between info, warn, error depending on your use case)
        logger.info(logMessage);
    }
    public  void error(String process, int responseCode, String responseMessage, String errorMessage, long timeTaken) {
        // Get current datetime in the desired format
        String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

        // Format time taken in milliseconds
        String timeTakenFormatted = String.format("%dms", timeTaken / 1_000_000);  // Convert to milliseconds

        // Build the log message in the desired format
        String logMessage = String.format("%s process=%s, responseCode=%d, responseMessage=%s, errorMessage=%s, timeTaken=%s",
                datetime, process, responseCode, responseMessage, errorMessage != null ? errorMessage : "null", timeTakenFormatted);

        // Log the message (you can choose between info, warn, error depending on your use case)
        logger.error(logMessage);
    }
    public  void warn(String process, int responseCode, String responseMessage, String errorMessage, long timeTaken) {

        String timeTakenFormatted = String.format("%dms", timeTaken / 1_000_000);

        String logMessage = String.format("%s process=%s, responseCode=%d, responseMessage=%s, errorMessage=%s, timeTaken=%s",
                datetime, process, responseCode, responseMessage, errorMessage != null ? errorMessage : "null", timeTakenFormatted);
        logger.warn(logMessage);
    }

}
