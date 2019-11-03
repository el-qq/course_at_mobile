package hw1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


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

    @Test
    public void testGetClassString() {

        String actualString = mainClass.getClassString();
        List<String> listExpectedWorlds = Arrays.asList("Hello", "hello");

        boolean result = listExpectedWorlds.stream().anyMatch(actualString::contains);

        Assert.assertTrue("В возврощаемой строке должна быть подстрока 'hello'или 'Hello'", result);

    }

}
