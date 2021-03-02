import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.geekbrains.webui.MyCalc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class MyCalcTests {

    static final Logger log = LoggerFactory.getLogger(MyCalcTests.class);

    // метод выполняется прежде ВСЕХ тестов
    @BeforeAll
    static void setUp(){
        log.info("\tBEFORE ALL TESTS =>");
    }

    // метод выполняется после ВСЕХ тестов
    @AfterAll
    static void tearDown(){
        log.info("\tAFTER ALL TESTS =>");
    }

    // метод выполняется перед каждым тестом
    @BeforeEach
    void setUpTest(){
        log.info("\t\tBEFORE ONE TEST");
    }

    // метод выполняется после каждого теста
    @AfterEach
    void tearDownTest(){
        log.info("\t\tAFTER ONE TEST");
    }

    //простейший тест, который проверяет работу функции сложения
    @Test
    void testAdd(){
        log.info("\t\t\tSTART WORK TEST");

        int act_result = MyCalc.add(2, 3);
        int exp_result = 5;

        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND WORK TEST");
    }

    // параметризированный тест
    // проверяем метод возведения в степень
    // параметры получаем из ValueSource
    @ParameterizedTest(name="#{index}- Test with Argument={arguments}")
    @ValueSource(doubles = {1, 2, 3, 4, 5})
    void testPow(double a){
        log.info("\t\t\tSTART WORK TEST");

        double act_result = MyCalc.pow(a);
        double exp_result = Math.pow(2, a);

        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND WORK TEST");
    }

    // параметризированный тест
    // проверяем метод возведения в степень
    // параметры получаем из CsvSource
    @ParameterizedTest(name="#{index}- Test with Argument={arguments}")
    @CsvSource({
            "0, 1",
            "2, 3",
            "4, 7"
    })
    void testMult(int a, int b){
        log.info("\t\t\tSTART WORK TEST");

        long act_result = MyCalc.mult(a, b);
        long exp_result = Math.multiplyFull(a, b);

        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND WORK TEST");
    }

    // параметризированный тест
    // проверяем метод разницы
    // параметры получаем из MethodSource - в нем указан отдельный метод intProvider
    @ParameterizedTest(name="#{index}- Test with Argument={arguments}")
    @MethodSource("intProvider")
    void testSub(int[] vars){
        log.info("\t\t\tSTART WORK TEST");

        int a = vars[0];
        int b = vars[1];

        long act_result = MyCalc.sub(a, b);
        long exp_result = Math.subtractExact(a, b);

        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND WORK TEST");
    }

    //метод в котором формитруются тестовые данные для теста testSub
    static ArrayList<int[]> intProvider() {
        log.info("\t\t\tSTART WORK intProvider");

        ArrayList<int[]> result = new ArrayList<>();

        result.add(new int[]{1, 3});
        result.add(new int[]{2, 5});
        result.add(new int[]{3, 7});

        log.info("\t\t\tEND WORK intProvider");

        return result;
    }

    // параметризированный тест
    // проверяем метод деления
    // параметры получаем из CsvFileSource - в нем указан путь к отдельному файлу,
    // который находится в папке test\resources
    @ParameterizedTest(name="#{index}- Test with Argument={arguments}")
    @CsvFileSource(resources = "/nums-data.csv", numLinesToSkip = 1)
    void testDiv(int a, int b){
        log.info("\t\t\tSTART WORK TEST");

        double act_result = MyCalc.div(a, b);
        double exp_result = Math.floorDiv(a, b);

        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND WORK TEST");
    }
}
