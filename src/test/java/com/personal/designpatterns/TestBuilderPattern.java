package com.personal.designpatterns;

import com.personal.designpatterns.builder.Builder;
import com.personal.designpatterns.builder.CarBuilder;
import com.personal.designpatterns.builder.PersonalCar;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by piyushr on 9/15/17.
 */
public class TestBuilderPattern {

    @Test
    public void testCarBuilder() throws Exception {
        Builder builder = new CarBuilder<PersonalCar>();
        builder.build();
        PersonalCar personalCar = builder.getResult();

        Assert.assertTrue("Body is not created", personalCar.getBody() != null);
        Assert.assertTrue("Engine is not created", personalCar.getEngine() != null);
        Assert.assertTrue("None of wheels created", personalCar.getWheels() != null);
        Assert.assertTrue("All 4 wheels are not created", personalCar.getWheels().length == 4);

    }
}
