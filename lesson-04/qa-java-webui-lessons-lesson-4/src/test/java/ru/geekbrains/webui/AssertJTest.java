package ru.geekbrains.webui;

import org.junit.jupiter.api.Assertions;
//важный импорт, указать, что в импорте должен быть именно он
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

//Тесты с AssertJ

public class AssertJTest {
    Functions functions = new Functions();
    //Показываем, что мы уже умеем

    @Test
    void testEvenNumberForIsEvenFunc() {
        boolean result = functions.isNumberEven(8);
        assertThat(result).as("Checking that 8 is even...").isTrue();
    }
//as будет показываться перед сообщением об ошибке
    @Test
    void testEvenNumberForIsEvenFuncFailed() {
        boolean result = functions.isNumberEven(8);
        assertThat(result).as("Checking that 8 is not even...").isFalse();
    }
}
