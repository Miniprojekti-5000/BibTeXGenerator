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
        this.references = new ArrayList<>();
    }
    
    public void start() {
        boolean hasReferences = !references.isEmpty();
        io.printAvailableCommands(hasReferences);
        int command = io.getCommand();
        
        if (command == 1) {
            io.readBook();
        } else if (command == 2) {
            makeBibtex();
        }
    }

    public void makeBibtex() {
        io.printReferences(references);
    }
}
