package ohtu.miniprojekti5000;

import ohtu.miniprojekti5000.domain.References;
import ohtu.miniprojekti5000.logic.Controller;

public class App {

    public static void main(String[] args) {
        References references = new References();
        Controller co = new Controller(references);
        co.start(0);
    }
}
