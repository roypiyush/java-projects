package com.personal.log4j;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by piyushr on 12/20/17.
 */
public class CustomPatternLayout extends PatternLayout {

    @Override
    public String format(LoggingEvent event) {
        return super.format(event);
    }

}
