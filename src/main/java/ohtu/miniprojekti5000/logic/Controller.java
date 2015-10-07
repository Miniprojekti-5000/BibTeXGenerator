package ohtu.miniprojekti5000.logic;

import ohtu.miniprojekti5000.data_access.FileHandler;
import ohtu.miniprojekti5000.ui.ConsoleIO;
import java.util.ArrayList;
import java.util.List;
import ohtu.miniprojekti5000.domain.ReferenceInterface;

public class Controller {
    private final ConsoleIO io;
    private List<ReferenceInterface> references;

    public Controller(ConsoleIO io) {
        this.io = io;
        io.askFileName();
        this.references = io.fileHandler.parseFile();
    }

    public boolean headingExists(String heading) {
        for (ReferenceInterface ref : references) {
            if(ref.getHeading().equals(heading)) {
                return true;
            }
        }

        return false;
    }

    public void start() {
        boolean isOn = true;

        while (isOn) {
            io.printAvailableCommands(!references.isEmpty());
            int command = io.getCommand();

            if (command == 1) {
                ReferenceInterface book = io.readBook();

                while(headingExists(book.getHeading())) {
                    io.printHeadingAlreadyExists(book.getHeading());
                    book = io.readBook();
                }

                io.appendFile(book);

                references.add(book);
                io.printAdded("Book");
            } else if (command == 2) {
                ReferenceInterface article = io.readArticle();

                while(headingExists(article.getHeading())) {
                    io.printHeadingAlreadyExists(article.getHeading());
                    article = io.readArticle();
                }

                io.appendFile(article);

                references.add(article);
                io.printAdded("Article");
            } if (command == 3) {
                makeBibtex();
            } else if (command == 4) {
                isOn = false;
            }
        }
    }

    public void makeBibtex() {
        //io.printReferencesFromFile();
        io.printReferences(references);
    }
}
