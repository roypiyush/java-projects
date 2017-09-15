package com.personal.eulerrule;

import org.junit.Assert;
import org.junit.Test;


public class TestEuler {

    @Test
    public void testHCF1() throws Exception {

        EulerDemo eulerDemo = new EulerDemo();
        Assert.assertTrue("HCF Calculation failed", eulerDemo.findHCF(1, 2) == 1);

    }

    @Test
    public void testLCM1() throws Exception {

        EulerDemo eulerDemo = new EulerDemo();
        Assert.assertTrue("LCM Calculation failed", eulerDemo.findLCM(2, 1) == 2);

    }

    @Test
    public void completeTest() throws Exception {
        EulerDemo eulerDemo = new EulerDemo();
        int numbers[] = {14,56,63,36};

        int HCF = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            // Finding HCF of multiple numbers
            HCF = eulerDemo.findHCF(HCF, numbers[i]);
        }
        Assert.assertTrue("HCF of numbers in wrong", HCF == 1);


        int LCM = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            // Finding LCM of multiple numbers
            LCM = eulerDemo.findLCM(LCM, numbers[i]);
        }
        Assert.assertTrue("LCM of numbers in wrong", LCM == 504);
    }
}