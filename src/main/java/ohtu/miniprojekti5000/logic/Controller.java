package ohtu.miniprojekti5000.logic;

import java.util.ArrayList;
import ohtu.miniprojekti5000.ui.ConsoleIO;
import java.util.List;
import ohtu.miniprojekti5000.data_access.FileHandler;
import ohtu.miniprojekti5000.domain.ArticleReference;
import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;

public class Controller {
    private final ConsoleIO io;
    private List<ReferenceInterface> references;
    private final InputValidator ioValidator;
    private final FileHandler filehandler;
    private String filename;
    private final SpecialCharConverter specialCharConverter;

    public Controller(ConsoleIO io) {
        this.io = io;
        references = new ArrayList<>();
        ioValidator = new InputValidator();
        filehandler = new FileHandler();
        filename = "test.bib";
        
        specialCharConverter = new SpecialCharConverter();

        specialCharConverter.addReplace("å", "\\a");
        specialCharConverter.addReplace("ä", "\\\"a");
        specialCharConverter.addReplace("ö", "\\\"o");
    }
    
    /**
     * starts program by asking suitable filename, if fails three times, exits program.
     */
    public void start()
    {
        for (int i = 3; i >= 0; i--)
        {
            Input filename = ioValidator.validateFileName(io.askFileName());
            if (filename.isValid())
            {
                this.filename = filename.getStringValue();
                filehandler.loadFile(this.filename);
                references = filehandler.parseFile();
                break;
            } else if (i > 0) io.printError("filename must end with .bib, please try again. "+i+" attempts left");
            else
            {
                io.printError("failed to give acceptable file name, exiting..");
                System.exit(1); // exits with failure
            }
        }
        
        run();
    }
    
    /**
     * recursive main menu.
     */
    public void run()
    {
        io.printAvailableCommands(!references.isEmpty());
        
        Input command = ioValidator.validateNumeralCommand(io.getInputString());
        
        if (command.isValid()) // if one has to add new commands, inputvalidator has to be updated, there is a clear mark in ONE LINE for what to change
        {
            switch (command.getIntegerValue())
            {
                case 0: System.exit(0); // exits with success.
                    break;    
                case 1: if (readBook()) io.printAdded("Book"); // read book
                    break;
                case 2: if (readArticle()) io.printAdded("Article"); // read article
                    break;
                case 3: // print references, if has references
                {
                    if (references.isEmpty()) io.printError("not a valid command");
                    else { io.printReferences(references); }
                } break;
            }
        } else io.printError("not a valid command.");
        
        run();
    }
    
    /**
     * asks information for journal type reference through console IO.
     * gives three attempts for each, and after three fails returns failure.
     * @return if book type reference was created successfully.
     */
    private boolean readArticle()
    {
        ArticleReference ar = new ArticleReference();
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateHeaderInput(references, io.askHeader());
            if (input.isValid())
            {
                ar.setHeading(input.getStringValue());
                break;
            } else if (i > 0) io.printError("cannot be empty or header already exists, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateStringInput(io.askAuthor());
            if (input.isValid())
            {
                ar.setAuthor(input.getStringValue());
                break;
            } else if (i > 0) io.printError("cannot be empty, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateStringInput(io.askTitle());
            if (input.isValid())
            {
                ar.setTitle(input.getStringValue());
                break;
            } else if (i > 0) io.printError("cannot be empty, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateStringInput(io.askJournal());
            if (input.isValid())
            {
                ar.setJournal(input.getStringValue());
                break;
            } else if (i > 0) io.printError("cannot be empty, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateYearInput(io.askYear());
            if (input.isValid())
            {
                ar.setYear(input.getStringValue());
                break;
            } else if (i > 0) io.printError("not a year, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateStringInput(io.askVolume());
            if (input.isValid())
            {
                ar.setVolume(input.getStringValue());
                break;
            } else if (i > 0) io.printError("cannot be empty, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        
        // tähän kysely optionaalisista kentistä ja niiden lisäys
        
        references.add(ar);
        filehandler.appendFile(filename, ar.toString(specialCharConverter));
        
        return true;
    }
    
    /**
     * asks information for book type reference through console IO.
     * gives three attempts for each, and after three fails returns failure.
     * @return if book type reference was created successfully.
     */
    private boolean readBook()
    {
        BookReference br = new BookReference();
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateHeaderInput(references, io.askHeader());
            if (input.isValid())
            {
                br.setHeading(input.getStringValue());
                break;
            } else if (i > 0) io.printError("cannot be empty or header already exists, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateStringInput(io.askAuthor());
            if (input.isValid())
            {
                br.setAuthor(input.getStringValue());
                break;
            } else if (i > 0) io.printError("cannot be empty, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateStringInput(io.askTitle());
            if (input.isValid())
            {
                br.setTitle(input.getStringValue());
                break;
            } else if (i > 0) io.printError("cannot be empty, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateStringInput(io.askPublisher());
            if (input.isValid())
            {
                br.setPublisher(input.getStringValue());
                break;
            } else if (i > 0) io.printError("cannot be empty, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        for (int i = 3; i >= 0; i--)
        {
            Input input = ioValidator.validateYearInput(io.askYear());
            if (input.isValid())
            {
                br.setYear(input.getStringValue());
                break;
            } else if (i > 0) io.printError("not a year, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        
        
        // tähän kysely optionaalisista kentistä ja niiden lisäys
        
        references.add(br);
        filehandler.appendFile(filename, br.toString(specialCharConverter));
        
        return true;
    }
}
