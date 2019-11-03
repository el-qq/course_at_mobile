package hw1;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {

        int expectedNumber = 14;
        int actualNumber = MainClass.getLocalNumber();

        String textError = "Метод getLocalNumber должен возвращать -- " + expectedNumber;
        Assert.assertEquals(textError, actualNumber, expectedNumber);

    }
}
