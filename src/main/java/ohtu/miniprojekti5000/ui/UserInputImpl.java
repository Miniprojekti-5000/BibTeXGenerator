package ohtu.miniprojekti5000.ui;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author hexvaara
 */
public class UserInputImpl implements UserInput
{   
    private String[] user_input;
    
    public UserInputImpl()
    {
        user_input = null;
    }
    @Override
    public String[] getUserInput()
    {
        return user_input;
    }
    
    @Override
    public void flush(int size)
    {
        user_input = new String[size];
    }
    
    @Override
    public boolean ask_input(int lines) // returns false if some line is empty or -1
    {
        flush(lines);
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < lines; i++)
        {
            String input = sc.nextLine();
            if (input.isEmpty() || input.matches("-1")) return false;
            else user_input[i] = input;
        }
        return true;
    }
}
