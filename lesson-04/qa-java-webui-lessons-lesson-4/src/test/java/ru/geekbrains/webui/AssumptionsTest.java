package ru.geekbrains.webui;


import org.apache.maven.surefire.booter.SystemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

public class AssumptionsTest {
    Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Jane", "Doe");
    }

    //Assumption - условие для выполнения теста
    //Если предположение не выполняется, создается исключение TestAbortedException, и тест просто пропускается.
    //Предположения также понимают лямбда-выражения.
    @Test
    void trueAssumption() {
        assumeTrue(5 > 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    void falseAssumption() {
        assumeFalse(5 < 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    void assumptionThat() {
        String someString = "Just a string";
        assumingThat(
                someString.equals("Just a string"),
                () -> assertEquals(2 + 2, 4)
        );
    }

    //практический пример, лямбды поддерживаются в языке с 8 версии:
    @Test
    void jvmVersionTest(){
        assumeTrue(SystemUtils.isBuiltInJava9AtLeast());
        assertAll("person",
                () -> assertEquals("Jane", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    //пример с обработкой упавшего условия, показываем throwTestAbortedException
    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

}
