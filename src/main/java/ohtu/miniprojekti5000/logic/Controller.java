package ohtu.miniprojekti5000.logic;

import java.util.ArrayList;
import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;
import ohtu.miniprojekti5000.domain.References;
import ohtu.miniprojekti5000.ui.*;

public class Controller {
    private References references;
    private UserInputImpl u_input;
    private IO io;
    ArrayList<String> availableCommands = new ArrayList<>();

    public Controller(References references) {
        u_input = new UserInputImpl();
        this.references = references;
        this.io = new IO();
    }

    public void start(int state) {
        io.printAvailableCommands(state);
        processUserInput(state);
    }

    public void processUserInput(int state) {
        availableCommands = new ArrayList<String>();

        // commands index:
        // 0: add book
        // 1: make bibtex
        switch (state) {
            case 0: // add all available commands to list and ask user input
            {
                availableCommands.add("add book");
                if (u_input.ask_input(1)) {
                    processOnelineCommand();
                } else {
                    start(state);
                }
            }
            break;
            case 1: // add all available commands to list
            {
                availableCommands.add("add book");
                availableCommands.add("make bibtex");
                if (u_input.ask_input(1)) {
                    processOnelineCommand();
                } else {
                    start(state);
                }
            }
            break;
            case 2: {

            }
        }
    }

    public void processOnelineCommand() {
        int command = -1;
        if (availableCommands.contains(u_input.getUserInput()[0])) // if "valid command"
        {
            for (int i = 0; i < availableCommands.size(); i++) {
                if (availableCommands.get(i).matches(
                        u_input.getUserInput()[0])) {
                    command = i;
                }
            }
        }

        switch (command) {
            case -1: // not valid command
            {
                start(0);
            }
            case 0: // add book
            {
                askBookInfo();
            }
            break;
            case 1: // make bibtex
            {
                makeBibtex();
            }
            break;
        }
    }

    public void makeBibtex() {
        for (ReferenceInterface ref : references.getAll()) {
            System.out.println(ref.toString());
        }

        if (references.getAll().isEmpty()) {
            start(0);
        } else {
            start(1);
        }
    }

    public void askBookInfo() {
        System.out.println("please enter: header, author, title, publisher and year. hit Enter between every line");
        if (u_input.ask_input(5)) {
            BookReference bookreference = new BookReference();
            bookreference.setHeading(u_input.getUserInput()[0]);
            bookreference.setAuthor(u_input.getUserInput()[1]);
            bookreference.setTitle(u_input.getUserInput()[2]);
            bookreference.setPublisher(u_input.getUserInput()[3]);
            bookreference.setYear(u_input.getUserInput()[4]);
            references.add(bookreference);

            start(1);
        } else {
            start(0);
        }
    }
}
