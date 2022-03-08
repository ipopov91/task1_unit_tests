import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;

import static org.testng.Assert.*;

public class TestPen {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Test(dataProvider = "negativeValidationData")
    public void emptyStringReturnTest (int inkContainerIsNegative) {
        Assert.assertEquals(new Pen(inkContainerIsNegative).write("inheritance"),"");
    }

    @Test(dataProvider = "textMoreInkContainer")
    public void testIfTextSizeMoreInkContainer(int inkContainerValue, double sizeLetter, String text, String expectedResult) {
        Assert.assertEquals(new Pen(inkContainerValue, sizeLetter).write(text), expectedResult);
    }

    @Test(dataProvider = "textLessInkContainer")
    public void testIfTextSizeLessInkContainer(int inkContainerValue, double sizeLetter, String text) {
        Assert.assertEquals(new Pen(inkContainerValue, sizeLetter).write(text), text);
    }

    @Test(dataProvider = "positiveValidationData")
    public void testIsWorkForTrue(int inkContainerValue) {
        Pen pen = new Pen(inkContainerValue);
        Boolean actualResult = pen.isWork();
        Assert.assertTrue(actualResult);
    }

    @Test(dataProvider = "negativeValidationData")
    public void testIsWorkForFalse(int inkContainerValue) {
        Pen pen = new Pen(inkContainerValue);
        Boolean actualResult = pen.isWork();
        Assert.assertFalse(actualResult);
    }

    @Test(dataProvider = "colorsData")
    public void testGetColorMethod(String color) {
        Pen pen = new Pen (1,1.0, color);
        String actualResult = pen.getColor();
        Assert.assertEquals(actualResult, color);
    }

    @Test(dataProvider = "sizeLetterParamZero")
    public void testSizeLetterParamZero(int inkContainerValue, double sizeLetter) {
        Pen pen = new Pen(inkContainerValue, sizeLetter);
        String actualResult = pen.write("inheritance");
        Assert.assertEquals(actualResult, "");
    }

    @Test(dataProvider = "positiveValidationData")
    public void testWordParamEmptyString(int inkContainerValue) {
        Pen pen = new Pen(inkContainerValue);
        String actualResult = pen.write("");
        assertEquals(actualResult, "");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testParamNull() {
        Pen pen = new Pen(1);
        pen.write(null);
    }

    @Test(dataProvider = "colorsData")
    public void testDoSthElse(String color) {
        Pen pen = new Pen(1, 1, color);
        pen.doSomethingElse();
        Assert.assertEquals(output.toString(), color);
    }

    @DataProvider
    public static Object[][] positiveValidationData() {
        return new Object[][] {
                {1},
                {10},
                {Integer.MAX_VALUE},
                {Integer.MAX_VALUE - 1}
        };
    }

    @DataProvider
    public static Object[][] negativeValidationData() {
        return new Object[][] {
                {-1},
                {-10},
                {Integer.MIN_VALUE},
                {Integer.MIN_VALUE + 1}
        };
    }


    @DataProvider
    public static Object[][] textMoreInkContainer() {
        return new Object[][] {
                {1, 1, "inheritance", "i"},
                {5, 1, "inheritance", "inher"},
                {10, 1, "in heritance", "in heritanc"},
                {11, 1, "Inheritance", "Inheritanc"}
        };
    }

    @DataProvider
    public static Object[][] textLessInkContainer() {
        return new Object[][] {
                {2, 1, "i"},
                {23, 2, "inheritance"},
                {Integer.MAX_VALUE, 1000, "in heritance"}
        };
    }

    @DataProvider
    public static Object[][] sizeLetterParamZero() {
        return new Object[][] {
                {1, 0}
        };
    }

    @DataProvider
    public Object[][] colorsData() {
        return new Object[][]{
                {"BLUE"},
                {"Green"},
                {"white"},
                {""}
        };
    }
}
