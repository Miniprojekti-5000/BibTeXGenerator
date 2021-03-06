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
     * starts program by asking suitable filename, if fails three times, exits
     * program.
     */
    public void start() {
        for (int i = 3; i >= 0; i--) {
            Input filename = ioValidator.validateFileName(io.askFileName());
            if (filename.isValid()) {
                this.filename = filename.getStringValue();
                filehandler.loadFile(this.filename);
                references = filehandler.parseFile();
                break;
            } else if (i > 0) {
                io.printError("filename must end with .bib, please try again. " + i + " attempts left");
            } else {
                io.printError("failed to give acceptable file name, exiting..");
                System.exit(0); // exits with success
            }
        }

        run();
    }

    /**
     * recursive main menu.
     */
    public void run() {
        io.printAvailableCommands(!references.isEmpty());

        Input command = ioValidator.validateNumeralCommand(io.getInputString());

        if (command.isValid()) // if one has to add new commands, inputvalidator has to be updated, there is a clear mark in ONE LINE for what to change
        {
            switch (command.getIntegerValue()) {
                case 0:
                    System.exit(0); // exits with success.
                    break;
                case 1:
                    if (readBook()) {
                        io.printAdded("Book"); // read book
                    }
                    break;
                case 2:
                    if (readArticle()) {
                        io.printAdded("Article"); // read article
                    }
                    break;
                case 3:
                    if (readInproceedings()) {
                        io.printAdded("Inproceedings");
                    }
                    break;
                case 4: // edit reference, if has references
                {
                    if (references.isEmpty()) {
                        io.printError("not a valid command");
                    } else {
                        if (editReference()) {
                            io.printEdited();
                        }
                    }
                }
                break;
                case 5: // delete reference, if has references
                {
                    if (references.isEmpty()) {
                        io.printError("not a valid command");
                    } else {
                        if (deleteReference()) {
                            io.printDeleted();
                        }
                    }
                }
                break;
                case 6: // print references, if has references
                {
                    if (references.isEmpty()) {
                        io.printError("not a valid command");
                    } else {
                        io.printReferencesWithIds(references);
                    }
                }
                break;
                case 7: // print references, if has references
                {
                    if (references.isEmpty()) {
                        io.printError("not a valid command");
                    } else {
                        io.printReferences(references);
                    }
                }
                break;
            }
        } else {
            io.printError("not a valid command.");
        }

        run();
    }

    private boolean deleteReference() {
        if (!references.isEmpty()) {
            int id = Integer.parseInt(io.askReferenceId("delete")) - 1;

            if (references.get(id) != null) {
                references.remove(id);
                filehandler.rewriteFile(filename, references, specialCharConverter);
                return true;
            }
        }

        return false;
    }

    private boolean editReference() {
        if (!references.isEmpty()) {
            Integer id = Integer.parseInt(io.askReferenceId("edit")) - 1;
            if (references.get(id) != null) {
                if (references.get(id) instanceof ArticleReference) {
                    ArticleReference ar = readArticleAsEdit((ArticleReference) references.get(id));

                    if (ar != null) {
                        references.set(id, ar);
                        filehandler.rewriteFile(filename, references, specialCharConverter);
                    }
                } else if (references.get(id) instanceof BookReference) {
                    BookReference br = readBookAsEdit((BookReference) references.get(id));

                    if (br != null) {
                        references.set(id, br);
                        filehandler.rewriteFile(filename, references, specialCharConverter);
                    }
                } else if (references.get(id) instanceof InproceedingsReference) {
                    InproceedingsReference ir = readInproceedingsAsEdit((InproceedingsReference) references.get(id));

                    if (ir != null) {
                        references.set(id, ir);
                        filehandler.rewriteFile(filename, references, specialCharConverter);
                    }
                }
            }
        }

        return false;
    }

    private boolean readInproceedings() {
        InproceedingsReference ir = new InproceedingsReference();

        if (!readHeader(ir) || !readAuthor(ir) || !readTitle(ir) || !readBookTitle(ir) || !readYear(ir)) {
            return false;
        }
        io.printOptionalFieldsNotice();

        if (!readVolume(ir) || !readSeries(ir) || !readAddress(ir) || !readEditor(ir) || !readOrganization(ir) || !readPublisher(ir) || !readMonth(ir) || !readNote(ir) || !readKey(ir)) {
            return false;
        }

        // tähän kysely optionaalisista kentistä ja niiden lisäys
        references.add(ir);
        filehandler.appendFile(filename, ir.toString(specialCharConverter));

        return true;
    }

    private InproceedingsReference readInproceedingsAsEdit(InproceedingsReference ir_old) {
        InproceedingsReference ir = new InproceedingsReference();
        Input input = ioValidator.validateHeaderInput(references, io.askHeader());
        String val = input.getStringValue();

        if (!val.equals("")) {
            ir.setHeading(val);
        } else {
            ir.setHeading(ir_old.getHeading());
        }

        input = ioValidator.validateStringInput(io.askAuthor());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setAuthor(val);
        } else {
            ir.setAuthor(ir_old.getAuthor());
        }

        input = ioValidator.validateStringInput(io.askTitle());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setTitle(val);
        } else {
            ir.setTitle(ir_old.getTitle());
        }

        input = ioValidator.validateStringInput(io.askBookTitle());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setBookTitle(val);
        } else {
            ir.setBookTitle(ir_old.getBookTitle());
        }

        input = ioValidator.validateStringInput(io.askYear());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setYear(val);
        } else {
            ir.setYear(ir_old.getYear());
        }

        input = ioValidator.validateStringInput(io.askVolume());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setVolume(val);
        } else {
            ir.setVolume(ir_old.getVolume());
        }

        input = ioValidator.validateStringInput(io.askSeries());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setSeries(val);
        } else {
            ir.setSeries(ir_old.getSeries());
        }

        input = ioValidator.validateStringInput(io.askAddress());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setAddress(val);
        } else {
            ir.setAddress(ir_old.getAddress());
        }

        input = ioValidator.validateStringInput(io.askEditor());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setEditor(val);
        } else {
            ir.setEditor(ir_old.getEditor());
        }

        input = ioValidator.validateStringInput(io.askOrganization());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setOrganization(val);
        } else {
            ir.setOrganization(ir_old.getOrganization());
        }

        input = ioValidator.validateStringInput(io.askPublisher());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setPublisher(val);
        } else {
            ir.setPublisher(ir_old.getPublisher());
        }

        input = ioValidator.validateStringInput(io.askMonth());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setMonth(val);
        } else {
            ir.setMonth(ir_old.getMonth());
        }

        input = ioValidator.validateStringInput(io.askNote());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setNote(val);
        } else {
            ir.setNote(ir_old.getNote());
        }

        input = ioValidator.validateStringInput(io.askKey());
        val = input.getStringValue();

        if (!val.equals("")) {
            ir.setKey(val);
        } else {
            ir.setKey(ir_old.getKey());
        }

        return ir;
    }

    /**
     * asks information for journal type reference through console IO. gives
     * three attempts for each, and after three fails returns failure.
     *
     * @return if book type reference was created successfully.
     */
    private boolean readArticle() {
        ArticleReference ar = new ArticleReference();
        if (!readHeader(ar) || !readAuthor(ar) || !readTitle(ar) || !readJournal(ar) || !readYear(ar) || !readVolume(ar)) {
            return false;
        }
        io.printOptionalFieldsNotice();

        if (!readNumber(ar) || !readPages(ar) || !readMonth(ar) || !readNote(ar) || !readKey(ar)) {
            return false;
        }

        references.add(ar);
        filehandler.appendFile(filename, ar.toString(specialCharConverter));

        return true;
    }

    private ArticleReference readArticleAsEdit(ArticleReference ar_old) {
        ArticleReference ar = new ArticleReference();
        Input input = ioValidator.validateHeaderInput(references, io.askHeader());
        String val = input.getStringValue();

        if (!val.equals("")) {
            ar.setHeading(val);
        } else {
            ar.setHeading(ar_old.getHeading());
        }

        input = ioValidator.validateStringInput(io.askAuthor());
        val = input.getStringValue();
        if (!val.equals("")) {
            ar.setAuthor(input.getStringValue());
        } else {
            ar.setAuthor(ar_old.getAuthor());
        }

        input = ioValidator.validateStringInput(io.askTitle());
        val = input.getStringValue();
        if (!val.equals("")) {
            ar.setTitle(input.getStringValue());
        } else {
            ar.setTitle(ar_old.getTitle());
        }

        input = ioValidator.validateStringInput(io.askJournal());
        val = input.getStringValue();
        if (!val.equals("")) {
            ar.setJournal(input.getStringValue());
        } else {
            ar.setJournal(ar_old.getJournal());
        }

        input = ioValidator.validateStringInput(io.askYear());
        val = input.getStringValue();

        if (!val.equals("")) {
            ar.setYear(val);
        } else {
            ar.setYear(ar_old.getYear());
        }

        input = ioValidator.validateStringInput(io.askVolume());
        val = input.getStringValue();
        if (!val.equals("")) {
            ar.setVolume(input.getStringValue());
        } else {
            ar.setVolume(ar_old.getVolume());
        }

        input = ioValidator.validateStringInput(io.askNumber());
        val = input.getStringValue();
        if (!val.equals("")) {
            ar.setNumber(input.getStringValue());
        } else {
            ar.setNumber(ar_old.getNumber());
        }

        input = ioValidator.validateStringInput(io.askPages());
        val = input.getStringValue();
        if (!val.equals("")) {
            ar.setPages(input.getStringValue());
        } else {
            ar.setPages(ar_old.getPages());
        }

        input = ioValidator.validateStringInput(io.askMonth());
        val = input.getStringValue();
        if (!val.equals("")) {
            ar.setMonth(input.getStringValue());
        } else {
            ar.setMonth(ar_old.getMonth());
        }

        input = ioValidator.validateStringInput(io.askNote());
        val = input.getStringValue();
        if (!val.equals("")) {
            ar.setNote(input.getStringValue());
        } else {
            ar.setNote(ar_old.getNote());
        }

        input = ioValidator.validateStringInput(io.askKey());
        val = input.getStringValue();
        if (!val.equals("")) {
            ar.setKey(input.getStringValue());
        } else {
            ar.setKey(ar_old.getKey());
        }

        return ar;
    }

    /**
     * asks information for book type reference through console IO. gives three
     * attempts for each, and after three fails returns failure.
     *
     * @return if book type reference was created successfully.
     */
    private boolean readBook() {
        BookReference br = new BookReference();

        if (!readHeader(br) || !readAuthor(br) || !readTitle(br) || !readPublisher(br) || !readYear(br)) {
            return false;
        }
        io.printOptionalFieldsNotice();

        if (!readVolume(br) || !readSeries(br) || !readAddress(br) || !readEdition(br) || !readMonth(br) || !readNote(br) || !readKey(br)) {
            return false;
        }
        references.add(br);
        filehandler.appendFile(filename, br.toString(specialCharConverter));

        return true;
    }

    private BookReference readBookAsEdit(BookReference br_old) {
        BookReference br = new BookReference();
        Input input = ioValidator.validateHeaderInput(references, io.askHeader());
        String val = input.getStringValue();

        if (!val.equals("")) {
            br.setHeading(val);
        } else {
            br.setHeading(br_old.getHeading());
        }

        input = ioValidator.validateStringInput(io.askAuthor());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setAuthor(input.getStringValue());
        } else {
            br.setAuthor(br_old.getAuthor());
        }

        input = ioValidator.validateStringInput(io.askTitle());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setTitle(input.getStringValue());
        } else {
            br.setTitle(br_old.getTitle());
        }

        input = ioValidator.validateStringInput(io.askPublisher());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setPublisher(input.getStringValue());
        } else {
            br.setPublisher(br_old.getPublisher());
        }

        input = ioValidator.validateStringInput(io.askYear());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setYear(val);
        } else {
            br.setYear(br_old.getYear());
        }

        input = ioValidator.validateStringInput(io.askVolume());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setVolume(val);
        } else {
            br.setVolume(br_old.getVolume());
        }

        input = ioValidator.validateStringInput(io.askSeries());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setSeries(val);
        } else {
            br.setSeries(br_old.getSeries());
        }

        input = ioValidator.validateStringInput(io.askAddress());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setAddress(val);
        } else {
            br.setAddress(br_old.getAddress());
        }

        input = ioValidator.validateStringInput(io.askEdition());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setEdition(val);
        } else {
            br.setEdition(br_old.getEdition());
        }

        input = ioValidator.validateStringInput(io.askMonth());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setMonth(val);
        } else {
            br.setMonth(br_old.getMonth());
        }

        input = ioValidator.validateStringInput(io.askNote());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setNote(val);
        } else {
            br.setNote(br_old.getNote());
        }

        input = ioValidator.validateStringInput(io.askKey());
        val = input.getStringValue();

        if (!val.equals("")) {
            br.setKey(val);
        } else {
            br.setKey(br_old.getKey());
        }

        return br;
    }

    private boolean readHeader(ReferenceInterface ri) {

        for (int i = 3; i >= 0; i--) {
            Input input = ioValidator.validateHeaderInput(references, io.askHeader());
            if (input.isValid()) {
                ri.setHeading(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("cannot be empty or header already exists, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;

    }

    private boolean readAuthor(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            Input input = ioValidator.validateStringInput(io.askAuthor());
            if (input.isValid()) {
                ri.setAuthor(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("cannot be empty, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    private boolean readTitle(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            Input input = ioValidator.validateStringInput(io.askTitle());
            if (input.isValid()) {
                ri.setTitle(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("cannot be empty, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    private boolean readBookTitle(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            Input input = ioValidator.validateStringInput(io.askBookTitle());
            if (input.isValid()) {
                ri.setBookTitle(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("cannot be empty, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    private boolean readYear(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            Input input = ioValidator.validateYearInput(io.askYear());
            if (input.isValid()) {
                ri.setYear(Integer.toString(input.getIntegerValue()));
                break;
            } else if (i > 0) {
                io.printError("not a year, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    private boolean readVolume(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String volumeInput = io.askVolume();
            if (volumeInput.isEmpty()) {
                break;
            }

            Input input = ioValidator.validateVolumeInput(volumeInput);
            if (input.isValid()) {

                ri.setVolume(Integer.toString(input.getIntegerValue()));
                break;
            } else if (i > 0) {
                io.printError("not a volume, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    private boolean readSeries(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String seriesInput = io.askSeries();
            if (seriesInput.isEmpty()) {
                break;
            }

            Input input = ioValidator.validateStringInput(seriesInput);
            if (input.isValid()) {

                ri.setSeries(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a series, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    private boolean readAddress(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String addressInput = io.askAddress();
            if (addressInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateStringInput(addressInput);
            if (input.isValid()) {

                ri.setAddress(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a address, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    private boolean readEditor(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String editorInput = io.askEditor();
            if (editorInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateStringInput(editorInput);
            if (input.isValid()) {

                ri.setEditor(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a editor, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    private boolean readOrganization(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String organizationInput = io.askOrganization();
            if (organizationInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateStringInput(organizationInput);
            if (input.isValid()) {

                ri.setOrganization(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a organization, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    private boolean readPublisher(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String publisherInput = io.askPublisher();
            if (publisherInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateStringInput(publisherInput);
            if (input.isValid()) {

                ri.setPublisher(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a publisher, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    public boolean readMonth(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String monthInput = io.askMonth();
            if (monthInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateMonthInput(monthInput);
            if (input.isValid()) {

                ri.setMonth(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a month, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    public boolean readNote(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String noteInput = io.askNote();
            if (noteInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateNoteInput(noteInput);
            if (input.isValid()) {

                ri.setNote(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a note, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    public boolean readKey(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String keyInput = io.askKey();
            if (keyInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateStringInput(keyInput);
            if (input.isValid()) {

                ri.setKey(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a key, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    public boolean readJournal(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String journalInput = io.askJournal();
            if (journalInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateStringInput(journalInput);
            if (input.isValid()) {
                ri.setJournal(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("cannot be empty, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    public boolean readNumber(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String numberInput = io.askNumber();
            if (numberInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateVolumeInput(numberInput);
            if (input.isValid()) {
                ri.setNumber(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a number, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    public boolean readPages(ReferenceInterface ri) {

        for (int i = 3; i >= 0; i--) {
            String pagesInput = io.askPages();
            if (pagesInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateVolumeInput(pagesInput);
            if (input.isValid()) {
                ri.setPages(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a valid pages value, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

    public boolean readEdition(ReferenceInterface ri) {
        for (int i = 3; i >= 0; i--) {
            String editionInput = io.askEdition();
            if (editionInput.isEmpty()) {
                break;
            }
            Input input = ioValidator.validateStringInput(editionInput);
            if (input.isValid()) {
                ri.setEdition(input.getStringValue());
                break;
            } else if (i > 0) {
                io.printError("not a edition, please try again. " + i + " attempts left");
            } else {
                io.printError("fail -> return to main menu");
                return false;
            }
        }
        return true;
    }

}
