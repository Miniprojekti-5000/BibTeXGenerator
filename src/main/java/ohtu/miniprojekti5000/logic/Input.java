/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.miniprojekti5000.logic;

/**
 *
 * @author hexvaara
 */
public class Input
{
    private final int integerValue;
    private final String stringValue;
    private final boolean valid;
    
    public Input(int value, boolean valid)
    {
        this.integerValue = value;
        this.stringValue = "";
        this.valid = valid;
    }
    public Input(String value, boolean valid)
    {
        this.integerValue = Integer.MIN_VALUE;
        this.stringValue = value;
        this.valid = valid;
    }
    
    public int getIntegerValue()
    {
        return integerValue;
    }
    
    public String getStringValue()
    {
        return stringValue;
    }
    
    public boolean isValid()
    {
        return valid;
    }
    
}
