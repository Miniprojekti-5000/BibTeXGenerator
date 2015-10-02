package ohtu.miniprojekti5000.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpecialCharConverterTest {
    private SpecialCharConverter specialCharConverter;

    @Before
    public void setUp() {
        specialCharConverter = new SpecialCharConverter();
    }

    @Test
    public void singleSpecialCharIsConvertedToBibTeXFormat() {
        specialCharConverter.addReplace("ä", "{\\\"a}");

        String test_str = "abcdefghijklmnopqÄrstuvwxyzåäö";

        assertEquals("abcdefghijklmnopq{\\\"A}rstuvwxyzå{\\\"a}ö", specialCharConverter.convertSpecialChars(test_str));
    }

    @Test
    public void multipleSpecialCharsIsConvertedToBibTeXFormat() {
        specialCharConverter.addReplace("ä", "{\\\"a}");
        specialCharConverter.addReplace("ö", "{\\\"o}");
        specialCharConverter.addReplace("å", "{\\a}");

        String test_str = "abcdefghijklÖmnÄopqÅrstuvwxyzåäö";

        assertEquals("abcdefghijkl{\\\"O}mn{\\\"A}opq{\\A}rstuvwxyz{\\a}{\\\"a}{\\\"o}", specialCharConverter.convertSpecialChars(test_str));
    }
}
