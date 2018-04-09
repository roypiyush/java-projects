package com.personal.log4j;

import com.personal.model.MySimpleJavaBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by piyushr on 12/19/17.
 */
public class AppenderTest {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(AppenderTest.class);
        MySimpleJavaBean bean = new MySimpleJavaBean();
        bean.setName("Some Name");
        bean.setValue("Some Value");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++)
            builder.append("1234567890");
        for (int i = 0; i < 20; i++)
            builder.append(builder.toString()); // 10 ^ 20 = 1000000000


        logger.error("Hello there, Some Random int {} {}", bean, new Random().nextInt());
        logger.info(builder.toString());
    }
}
