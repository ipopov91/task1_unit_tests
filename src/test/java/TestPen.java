import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

public class TestPen {

    @Test(dataProvider = "validationData")
    public void check (int inkContainerValue, double sizeLetter) {
        assertEquals(new Pen(inkContainerValue, sizeLetter).write("world is beautiful"),"world is beautiful");
    }

    @Test(dataProvider = "positiveValidationDataForWorkChecker")
    public void workingChecker(int inkContainerValue) {
        assertTrue(new Pen(inkContainerValue).isWork(), "true");
    }

    @Test(dataProvider = "negativeValidationDataForWorkChecker")
    public void unworkingChecker(int inkContainerValue) {
        assertFalse(new Pen(inkContainerValue).isWork(), "false");
    }

    @Test(dataProvider = "dataProviderForColorChecker")
    public void colorChecker(int inkContainerValue, double sizeLetter, String color) {
        assertEquals(new Pen(inkContainerValue, sizeLetter, color).getColor(), color);
    }

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Test
    public void testDoSomethingElse() {
        PrintStream old = System.out;
        Pen pen = new Pen(100, 1.0, "GREY");
        System.setOut(new PrintStream(output));
        pen.doSomethingElse();
        assertEquals(output.toString().replaceAll("\\s", ""), "GREY", "Successfully brings text");
        System.setOut(old);
    }

    @DataProvider
    public static Object[][] dataProviderForColorChecker() {
        return new Object[][] {
                {1000, 1.0, "BLUE"},
                {1000, 1.0, "RED"},
                {1000, 1.0, "BLACK"},
                {1000, 1.0, "GREEN"},
        };
    }

    @DataProvider
    public static Object[][] validationData() {
        return new Object[][] {
                {100, 1.0},
                {100, 2.0},
                {16, 1.0},
                {16, 2.0},
                {0, 1.0}
        };
    }

    @DataProvider
    public static Object[][] positiveValidationDataForWorkChecker() {
        return new Object[][] {
                {Integer.MAX_VALUE},
                {100},
                {0}
        };
    }

    @DataProvider
    public static Object[][] negativeValidationDataForWorkChecker() {
        return new Object[][] {
                {-Integer.MAX_VALUE},
                {-100},
        };
    }
}
