package ohtu.miniprojekti5000.logic;

import java.util.List;
import ohtu.miniprojekti5000.domain.ReferenceInterface;
import ohtu.miniprojekti5000.ui.ConsoleIO;

/**
 *
 * @author hexvaara
 */
public class InputValidator
{
    private final ConsoleIO io;
    
    public InputValidator()
    {
        io = new ConsoleIO();
    }
    
    /**
     * checks if user input is integer [0,1]
     * @param input user input
     * @return Input class, with integer value and valid or invalid boolean
     */
    public Input validateNumeralCommand(String input)
    {
        boolean valid = false;
        int value = Integer.MIN_VALUE;
        
        try 
        {
            value = Integer.parseInt(input);
            valid = true;
        }
        catch (Exception e) {}
        
        if (valid && value >= 0 && value < 5) // valid commands are 0,1  <--------------------- add new commands here and update javadoc plz.
        {
            return new Input(value, true);
        } else return new Input(value, false);
    }
    
    /**
     * checks if user input is a valid year from 10 000 bc to 3 000 ad
     * @param input user input
     * @return Input class, with integer value and valid or invalid boolean
     */
    public Input validateYearInput(String input)
    {
        boolean valid = false;
        int value = Integer.MIN_VALUE;
        
        try 
        {
            value = Integer.parseInt(input);
            valid = true;
        }
        catch (Exception e) {}
        
        if (valid && value >= -10000 && value <= 3000)
        {
            return new Input(value, true);
        } else return new Input(value, false);
        
    }
    /**
     * checks if user input is not empty
     * @param input user input
     * @return Input class, with String value and valid or invalid boolean
     */
    public Input validateStringInput(String input)
    {
        if (!input.isEmpty()) return new Input(input, true);
        else return new Input(input, false);
    }
    /**
     * checks if input is a valid unique header
     * @param references
     * @param input
     * @return Input class, with String value and valid or invalid boolean
     */
    public Input validateHeaderInput(List<ReferenceInterface> references, String input)
    {
        if (!input.isEmpty() || !references.contains(input)) return new Input(input, true);
        else return new Input("", false);
    }
    
    /**
     * checks if input is a .bib ending filename
     * @param input
     * @return Input class, with String value and valid or invalid boolean
     */
    public Input validateFileName(String input)
    {
        if (input.endsWith(".bib")) return new Input(input, true);
        else return new Input(input, false);
    }
    
    
}
