package hw1;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    private MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {

        int expectedNumber = 14;
        int actualNumber = MainClass.getLocalNumber();

        String textError = "Метод getLocalNumber должен возвращать -- " + expectedNumber;
        Assert.assertEquals(textError, actualNumber, expectedNumber);

    }

    @Test
    public void testGetClassNumber() {

        int numberToCompare = 45;
        int actualNumber = mainClass.getClassNumber();

        String textError = "Метод getClassNumber должен возвращать значение больше -- " + numberToCompare;
        Assert.assertTrue(textError, actualNumber > numberToCompare);

    }

}
