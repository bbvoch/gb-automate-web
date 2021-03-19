package ru.geekbrains.webui;

import org.junit.jupiter.api.*;
//важный импорт, указать, что в импорте должен быть именно он
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 1. Показываем, как написать первый unit-тест на функцию
public class CommonTest {
    Functions functions = new Functions();
    private static Logger logger = LoggerFactory.getLogger(CommonTest.class);

    @BeforeAll
    static void beforeAllTests() {
        logger.info("Before all tests");
    }


    //два теста - на четную и нечетную цифру в методе isNumberEven(Integer number)
    @Test
    void testGivenEvenNumber__whenCheckingIsNumberEven__thenTrue() {
        boolean result = functions.isNumberEven(8);
        Assertions.assertTrue(result);
    }

    @Test
    void givenOddNumber__whenCheckingIsNumberEven__thenFalseTest() {
        boolean result = functions.isNumberEven(3);
        Assertions.assertFalse(result);
    }

    //показать ассерт с поясняющим сообщением на другом примере метода
    @Test
    void givenNegativeNumber__whenCheckingIsNumberPositive__thenFalseTest() {
        boolean result = functions.isPositive(-5);
        Assertions.assertFalse(result, "Try a negative number");
    }

    //тест, который должен упасть - показываем, где появляется сообщение
    @Test
    void FailedTestGivenNegativeNumber__whenCheckingIsNumberPositive__thenTrueTest() {
        boolean result = functions.isPositive(-5);
        Assertions.assertTrue(result, "Try a negative number");
    }
//показываем аннотацию DisplayName
    @Test
    @DisplayName("Позитивный тест на функцию isPrime() с простым числом")
    public void isPrimePositiveTest() {
        Assertions.assertTrue(functions.isPrime(3));
    }

    //показываем, как скипать тесты с помощью аннотации Disabled
    @Test
    @Disabled("для демонстрации")
    public void isPrimeNegativeTest() {
        Assertions.assertFalse(functions.isPrime(6));
    }

    //показываем параметризованные тесты - аналог DataProvider в TestNG
    @DisplayName("Слово является палиндромом")
    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    public void isPalindromeTest(String word){
        Assertions.assertTrue(functions.isPalindrome(word));
    }

    @AfterAll
    static void afterAllTests() {
        logger.info("After all tests");
    }
}
