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

    @BeforeAll
    static void setUp(){
        log.info("\tBEFORE ALL TESTS =>");
    }

    @AfterAll
    static void tearDown(){
        log.info("\tAFTER ALL TESTS =>");
    }

    @BeforeEach
    void setUpTest(){
        log.info("\t\tBEFORE ONE TEST");
    }

    @AfterEach
    void tearDownTest(){
        log.info("\t\tAFTER ONE TEST");
    }

    @Disabled
    @Test
    void testAdd(){
        log.info("\t\t\tSTART WORK TEST");

        int a = 2;
        int b = 3;

        Assertions.assertEquals(5, MyCalc.add(a, b));

        log.info("\t\t\tEND WORK TEST");
    }

    @Disabled
    @ParameterizedTest(name="#{index}- Test with Argument={arguments}")
    @ValueSource(doubles = {1, 2, 3, 4, 5})
    void testPow(double a){
        log.info("\t\t\tSTART WORK TEST");

        double act_result = MyCalc.pow(a);
        double exp_result = Math.pow(2, a);

        log.info(String.valueOf(act_result));
        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND WORK TEST");
    }


    @Disabled
    @ParameterizedTest(name="#{index}- Test with Argument={arguments}")
    @CsvSource({
            "0, 1",
            "2, 3",
            "4, 7"
    })
    void testMult(int a, int b){
        log.info("\t\t\tSTART WORK TEST");

        double act_result = MyCalc.mult(a, b);
        double exp_result = Math.multiplyFull(a, b);

        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND WORK TEST");
    }



    @ParameterizedTest(name="#{index}- Test with Argument={arguments}")
    @MethodSource("intProvider")
    void testSub(int[] vars){
        log.info("\t\t\tSTART WORK TEST");

        int a = vars[0];
        int b = vars[1];

        double act_result = MyCalc.sub(a, b);
        double exp_result = Math.subtractExact(a, b);

        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND WORK TEST");
    }

    static ArrayList<int[]> intProvider() {
        log.info("\t\t\tSTART WORK intProvider");

        ArrayList<int[]> result = new ArrayList<>();

        result.add(new int[]{1, 3});
        result.add(new int[]{2, 5});
        result.add(new int[]{3, 7});

        log.info("\t\t\tEND WORK intProvider");

        return result;
    }

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
