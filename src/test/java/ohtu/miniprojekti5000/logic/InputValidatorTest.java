/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.miniprojekti5000.logic;

import java.util.ArrayList;
import java.util.List;
import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hexvaara
 */
public class InputValidatorTest
{
    
    public InputValidatorTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void validateNumeralCommand_correct()
    {
        InputValidator iv = new InputValidator();
        Input temp = iv.validateNumeralCommand("1");
        assertTrue(temp.isValid());
        assertEquals(1, temp.getIntegerValue());
        assertEquals("", temp.getStringValue());
    }
    @Test
    public void validateNumeralCommand_incorrect()
    {
        InputValidator iv = new InputValidator();
        Input temp = iv.validateNumeralCommand("a");
        assertFalse(temp.isValid());
    }
    @Test
    public void validateYearInput_correct()
    {
        InputValidator iv = new InputValidator();
        Input temp = iv.validateYearInput("2000");
        assertTrue(temp.isValid());
        assertEquals(2000, temp.getIntegerValue());
    }
    @Test
    public void validateYearInput_incorrect()
    {
        InputValidator iv = new InputValidator();
        Input temp = iv.validateYearInput("a");
        assertFalse(temp.isValid());
    }
    @Test
    public void validateStringInput_correct()
    {
        InputValidator iv = new InputValidator();
        Input temp = iv.validateStringInput("a");
        assertTrue(temp.isValid());
        assertEquals("a", temp.getStringValue());
    }
    @Test
    public void validateStringInput_incorrect()
    {
        InputValidator iv = new InputValidator();
        Input temp = iv.validateStringInput("");
        assertFalse(temp.isValid());
    }
    @Test
    public void validateHeaderInput_incorrect()
    {
        BookReference br1 = new BookReference();
        br1.setHeading("asd");
        
        List<ReferenceInterface> references = new ArrayList<>();
        references.add(br1);
        
        InputValidator iv = new InputValidator();
        Input temp = iv.validateHeaderInput(references, "asd");
        
        assertFalse(temp.isValid());
    }
    @Test
    public void validateHeaderInput_correct()
    {
        BookReference br1 = new BookReference();
        br1.setHeading("asd");
        
        List<ReferenceInterface> references = new ArrayList<>();
        references.add(br1);
        
        InputValidator iv = new InputValidator();
        Input temp = iv.validateHeaderInput(references, "lol");
        
        assertTrue(temp.isValid());
        assertEquals("lol", temp.getStringValue());
    }
    @Test
    public void validateFileName_correct()
    {
        InputValidator iv = new InputValidator();
        Input temp = iv.validateFileName("test.bib");
        assertTrue(temp.isValid());
        assertEquals("test.bib",temp.getStringValue());
    }
    @Test
    public void validateFileName_incorrect()
    {
        InputValidator iv = new InputValidator();
        Input temp = iv.validateFileName("test.txt");
        assertFalse(temp.isValid());
    }
}
