package ohtu.miniprojekti5000.ui;


public interface UserInput {
    String[] getUserInput();
    void flush(int size);
    boolean ask_input(int lines);
}
