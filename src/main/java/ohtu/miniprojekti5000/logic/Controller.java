package ohtu.miniprojekti5000.logic;

import java.sql.Ref;
import java.util.ArrayList;

import ohtu.miniprojekti5000.domain.InproceedingsReference;
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
                System.exit(0); // exits with success
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
                case 3: if (readInproceedings()) io.printAdded("Inproceedings");
                    break;
                case 4: // edit reference, if has references
                {
                    if (references.isEmpty()) io.printError("not a valid command");
                    else { if(editReference()) io.printEdited(); }
                } break;
                case 5: // delete reference, if has references
                {
                    if (references.isEmpty()) io.printError("not a valid command");
                    else { if(deleteReference()) io.printDeleted(); }
                } break;
                case 6: // print references, if has references
                {
                    if (references.isEmpty()) io.printError("not a valid command");
                    else { io.printReferencesWithIds(references); }
                } break;
                case 7: // print references, if has references
                {
                    if (references.isEmpty()) io.printError("not a valid command");
                    else { io.printReferences(references); }
                } break;
            }
        } else io.printError("not a valid command.");
        
        run();
    }

    private boolean deleteReference() {
        if(!references.isEmpty()) {
            int id = Integer.parseInt(io.askReferenceId("delete")) - 1;

            if(references.get(id) != null) {
                references.remove(id);
                filehandler.rewriteFile(filename, references, specialCharConverter);
                return true;
            }
        }

        return false;
    }

    private boolean editReference() {
        if(!references.isEmpty()) {
            Integer id = Integer.parseInt(io.askReferenceId("edit")) - 1;
            if(references.get(id) != null) {
                if(references.get(id) instanceof ArticleReference) {
                    ArticleReference ar = readArticleAsEdit((ArticleReference) references.get(id));

                    if(ar != null) {
                        references.set(id, ar);
                        filehandler.rewriteFile(filename, references, specialCharConverter);
                    }
                } else if(references.get(id) instanceof BookReference) {
                    BookReference br = readBookAsEdit((BookReference) references.get(id));

                    if(br != null) {
                        references.set(id, br);
                        filehandler.rewriteFile(filename, references, specialCharConverter);
                    }
                } else if(references.get(id) instanceof InproceedingsReference) {
                    InproceedingsReference ir = readInproceedingsAsEdit((InproceedingsReference) references.get(id));

                    if(ir != null) {
                        references.set(id, ir);
                        filehandler.rewriteFile(filename, references, specialCharConverter);
                    }
                }
            }
        }

        return false;
    }

    private boolean readInproceedings()
    {
        InproceedingsReference ir = new InproceedingsReference();
        for (int i = 3; i >= 0; i--) {
            Input input = ioValidator.validateHeaderInput(references, io.askHeader());
            if (input.isValid())
            {
                ir.setHeading(input.getStringValue());
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
                ir.setAuthor(input.getStringValue());
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
                ir.setTitle(input.getStringValue());
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
            Input input = ioValidator.validateStringInput(io.askBookTitle());
            if (input.isValid())
            {
                ir.setBookTitle(input.getStringValue());
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
                ir.setYear(input.getStringValue());
                break;
            } else if (i > 0) io.printError("not a year, please try again. "+i+" attempts left");
            else
            {
                io.printError("fail -> return to main menu");
                return false;
            }
        }


        // tähän kysely optionaalisista kentistä ja niiden lisäys

        references.add(ir);
        filehandler.appendFile(filename, ir.toString(specialCharConverter));

        return true;
    }

    private InproceedingsReference readInproceedingsAsEdit(InproceedingsReference ir_old)
    {
        InproceedingsReference ir = new InproceedingsReference();
        Input input = ioValidator.validateHeaderInput(references, io.askHeader());
        String val = input.getStringValue();

        if(!val.equals(""))
            ir.setHeading(val);
        else
            ir.setHeading(ir_old.getHeading());

        input = ioValidator.validateStringInput(io.askAuthor());
        val = input.getStringValue();

        if(!val.equals(""))
            ir.setAuthor(val);
        else
            ir.setAuthor(ir_old.getAuthor());

        input = ioValidator.validateStringInput(io.askTitle());
        val = input.getStringValue();

        if(!val.equals(""))
            ir.setTitle(val);
        else
            ir.setTitle(ir_old.getTitle());

        input = ioValidator.validateStringInput(io.askBookTitle());
        val = input.getStringValue();

        if(!val.equals(""))
            ir.setBookTitle(val);
        else
            ir.setBookTitle(ir_old.getBookTitle());

        input = ioValidator.validateStringInput(io.askYear());
        val = input.getStringValue();

        if(!val.equals(""))
            ir.setYear(val);
        else
            ir.setYear(ir_old.getYear());

        return ir;
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

    private ArticleReference readArticleAsEdit(ArticleReference ar_old)
    {
        ArticleReference ar = new ArticleReference();
            Input input = ioValidator.validateHeaderInput(references, io.askHeader());
        String val = input.getStringValue();

        if(!val.equals(""))
            ar.setHeading(val);
        else
            ar.setHeading(ar_old.getHeading());

        input = ioValidator.validateStringInput(io.askAuthor());
        val = input.getStringValue();
        if(!val.equals(""))
                ar.setAuthor(input.getStringValue());
        else
            ar.setAuthor(ar_old.getAuthor());

        input = ioValidator.validateStringInput(io.askTitle());
        val = input.getStringValue();
        if(!val.equals(""))
                ar.setTitle(input.getStringValue());
        else
                ar.setTitle(ar_old.getTitle());


       input = ioValidator.validateStringInput(io.askJournal());
        val = input.getStringValue();
        if(!val.equals(""))
                ar.setJournal(input.getStringValue());
        else
                ar.setJournal(ar_old.getJournal());

        input = ioValidator.validateYearInput(io.askYear());
        val = input.getStringValue();
        if(!val.equals(""))
                ar.setYear(input.getStringValue());
        else
                ar.setYear(ar_old.getYear());

        input = ioValidator.validateStringInput(io.askVolume());
        val = input.getStringValue();
        if(!val.equals(""))
                ar.setVolume(input.getStringValue());
        else
                ar.setVolume(ar_old.getVolume());
        return ar;
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

    private BookReference readBookAsEdit(BookReference br_old)
    {
        BookReference br = new BookReference();
        Input input = ioValidator.validateHeaderInput(references, io.askHeader());
        String val = input.getStringValue();

        if(!val.equals(""))
            br.setHeading(val);
        else
            br.setHeading(br_old.getHeading());


        input = ioValidator.validateStringInput(io.askAuthor());
        val = input.getStringValue();

        if(!val.equals(""))
                br.setAuthor(input.getStringValue());
        else
                br.setAuthor(br_old.getAuthor());

        input = ioValidator.validateStringInput(io.askTitle());
        val = input.getStringValue();

        if(!val.equals(""))
                br.setTitle(input.getStringValue());
        else
                br.setTitle(br_old.getTitle());

        input = ioValidator.validateStringInput(io.askPublisher());
        val = input.getStringValue();

        if(!val.equals(""))
                br.setPublisher(input.getStringValue());
        else
            br.setPublisher(br_old.getPublisher());

        input = ioValidator.validateYearInput(io.askYear());
        val = input.getStringValue();

        if(!val.equals(""))
                br.setYear(input.getStringValue());
        else
                br.setYear(br_old.getYear());

        return br;
    }
}
