package ohtu.miniprojekti5000.ui;

//This class is for testing purposes only

public class StubUserInput implements UserInput {
    
    private String[] user_input;
    
    public StubUserInput(String... values) {
        this.user_input = values;
    }

    @Override
    public String[] getUserInput() {
        return user_input;
    }

    @Override
    public void flush(int size) {
        user_input = new String[size];
    }

    @Override
    public boolean ask_input(int lines) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
