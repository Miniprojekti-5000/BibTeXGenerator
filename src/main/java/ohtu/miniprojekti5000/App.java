package ohtu.miniprojekti5000;

import ohtu.miniprojekti5000.logic.Controller;
import ohtu.miniprojekti5000.ui.IO;

public class App {

    public static void main(String[] args) {
        IO io = new IO();
        Controller co = new Controller(io);
        
        
        
        co.start();
    }
}
