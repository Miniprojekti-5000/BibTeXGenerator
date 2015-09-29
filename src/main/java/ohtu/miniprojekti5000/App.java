package ohtu.miniprojekti5000;

import ohtu.miniprojekti5000.logic.References;
import ohtu.miniprojekti5000.ui.UI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        References references = new References();

        UI ui = new UI(references);

        ui.run2((byte)0);
    }
}
