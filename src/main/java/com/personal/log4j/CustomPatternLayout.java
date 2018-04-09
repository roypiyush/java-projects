package com.personal.log4j;

import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.spi.LoggingEvent;

import java.util.regex.Pattern;

/**
 * Created by piyushr on 12/20/17.
 */
public class CustomPatternLayout extends EnhancedPatternLayout {

    private int maxLength = Integer.MAX_VALUE;

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    private static final Pattern FILTER_PATTERN = Pattern.compile("([^A-Za-z0-9.:\\-_,\\.\\[\\]()\\n\\s])");
    @Override
    public String format(LoggingEvent event) {
        String format = super.format(event);
        if (format.length() > maxLength) {
            format = format.substring(0, maxLength) + "\n";
        }
        return sanitize(format);
    }

    public static String sanitize(String value) {
        if (value == null || value.trim().isEmpty()) {
            return value;
        }
        // return value;
        return FILTER_PATTERN.matcher(value).replaceAll("_");
    }
}
