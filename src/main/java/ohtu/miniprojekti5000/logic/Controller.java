package ohtu.miniprojekti5000.logic;

import ohtu.miniprojekti5000.ui.IO;
import java.util.ArrayList;
import java.util.List;
import ohtu.miniprojekti5000.domain.ReferenceInterface;

public class Controller {

    private final IO io;
    private List<ReferenceInterface> references;

    public Controller(IO io) {
        this.io = io;
        this.references = new ArrayList<ReferenceInterface>();
    }

    public void start() {
        boolean isOn = true;

        while (isOn) {
            io.printAvailableCommands(!references.isEmpty());
            int command = io.getCommand();

            if (command == 1) {
                ReferenceInterface book = io.readBook();
                references.add(book);
                io.printAdded("Book");
            } else if (command == 2) {
                ReferenceInterface article = io.readArticle();
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
        io.printReferences(references);
    }
}
