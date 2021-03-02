package ru.geekbrains.webui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.webui.utils.TimingExtension;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(TimingExtension.class)
public class LoggingTests {
    private static Logger logger = LoggerFactory.getLogger(CommonTest.class);


    Functions functions = new Functions();

    @BeforeAll
    static void beforeAllTests() {
        logger.info("Before all tests");
    }

    @BeforeEach
    public void setUp(){
        logger.info("Test begins!");
    }

    @Test
    void testNegative() {
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
    void givenNegativeNumber__whenCheckingIsNumberPositive__thenFalseTest(){
        boolean result = functions.isPositive(-5);
        Assertions.assertFalse(result, "Try a negative number");
    }
    //тест, который должен упасть - показываем, где появляется сообщение
    @Test
    void failedTestGivenNegativeNumber__whenCheckingIsNumberPositive__thenTrueTest(){
        boolean result = functions.isPositive(-5);
        Assertions.assertTrue(result, "Try a negative number");
    }

    @Test
    public void testPrimeNumbers(){
        Assertions.assertTrue(functions.isPrime(23));
    }


    @AfterEach
    public void tearDown(){
        logger.info("Test completed!");
    }

    @AfterAll
    static void afterAllTests() {
        logger.info("After all tests");
    }
}
