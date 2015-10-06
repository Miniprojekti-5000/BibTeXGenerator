package ohtu.miniprojekti5000;

import ohtu.miniprojekti5000.logic.Controller;
import ohtu.miniprojekti5000.ui.ConsoleIO;

public class App {

    public static void main(String[] args) {
        ConsoleIO io = new ConsoleIO();
        Controller co = new Controller(io);
        
        
        
        co.start();
    }
}
