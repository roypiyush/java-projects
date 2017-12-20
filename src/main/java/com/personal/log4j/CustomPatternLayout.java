package com.personal.log4j;

import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.spi.LoggingEvent;

import java.util.regex.Pattern;

/**
 * Created by piyushr on 12/20/17.
 */
public class CustomPatternLayout extends EnhancedPatternLayout {

    private int bytes = -1;

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }
    private static final Pattern DISALLOWED_CHARACTERS_PATTERN_FOR_LOG_FORGING = Pattern.compile("([^A-Za-z0-9.:\\-_,\\.\\[\\]()\\n\\s])");
    @Override
    public String format(LoggingEvent event) {
        String format = super.format(event);
        if (bytes != -1 && format.length() > bytes) {
            format = format.substring(0, bytes);
        }
        return sanitizeForging(format);
    }

    public static String sanitizeForging(String value) {
        if (value == null || value.trim().isEmpty()) {
            return value;
        }
        // return value;
        return DISALLOWED_CHARACTERS_PATTERN_FOR_LOG_FORGING.matcher(value).replaceAll("_");
    }
}
