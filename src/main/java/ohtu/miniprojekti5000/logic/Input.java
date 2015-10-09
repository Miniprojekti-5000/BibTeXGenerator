package ohtu.miniprojekti5000.logic;

/**
 * Tuppeli jolle voi antaa string, boolean tai int, boolean arvot
 * @author miniprojekti5000
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
