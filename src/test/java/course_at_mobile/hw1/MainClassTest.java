package course_at_mobile.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class MainClassTest {

    private MainClass mainClass = new MainClass();

    @Test
    void testGetLocalNumber() {

        int expectedNumber = 14;
        int actualNumber = MainClass.getLocalNumber();

        String textError = "Метод getLocalNumber должен возвращать -- " + expectedNumber;
        Assertions.assertEquals(actualNumber, expectedNumber, textError);

    }

    @Test
    void testGetClassNumber() {

        int numberToCompare = 45;
        int actualNumber = mainClass.getClassNumber();

        String textError = "Метод getClassNumber должен возвращать значение больше -- " + numberToCompare;
        Assertions.assertTrue(actualNumber > numberToCompare, textError);

    }

    @Test
    void testGetClassString() {

        String actualString = mainClass.getClassString();
        List<String> listExpectedWorlds = Arrays.asList("Hello", "hello");

        boolean result = listExpectedWorlds.stream().anyMatch(actualString::contains);
        Assertions.assertTrue(result, "В возврощаемой строке должна быть подстрока 'hello'или 'Hello'");

    }

}
