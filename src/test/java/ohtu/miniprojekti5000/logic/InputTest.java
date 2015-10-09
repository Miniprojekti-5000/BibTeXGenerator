/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.miniprojekti5000.logic;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hexvaara
 */
public class InputTest
{
    
    public InputTest()
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
    public void testIntegerTypeInput()
    {
        Input i = new Input(1, true);
        assertEquals(i.getStringValue(), "");
        assertEquals(i.getIntegerValue(), 1);
        assertTrue(i.isValid());
    }

    @Test
    public void testStringTypeInput()
    {
        Input i = new Input("asd", true);
        assertEquals(i.getStringValue(), "asd");
        assertEquals(i.getIntegerValue(), Integer.MIN_VALUE);
        assertTrue(i.isValid());
    }
}
