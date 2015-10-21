package ohtu.miniprojekti5000.ui;

import java.util.List;
import java.util.Scanner;

import ohtu.miniprojekti5000.data_access.FileHandler;
import ohtu.miniprojekti5000.domain.ArticleReference;
import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;
import ohtu.miniprojekti5000.logic.SpecialCharConverter;

public class ConsoleIO implements IO {

    public final SpecialCharConverter specialCharConverter;
    private final Scanner scanner; // can be deleted, newest version of ui uses anonymous scanner
    public final FileHandler fileHandler;
    private String filename;

    public ConsoleIO() {
        filename = "test.bib";
        fileHandler = new FileHandler();
        fileHandler.loadFile(filename);

        specialCharConverter = new SpecialCharConverter();

        specialCharConverter.addReplace("å", "\\a");
        specialCharConverter.addReplace("ä", "\\\"a");
        specialCharConverter.addReplace("ö", "\\\"o");

        scanner = new Scanner(System.in); // can be deleted, newest version of ui uses anonymous scanner
    }

    public String askFileName() {
        System.out.println("enter file name: (if doesnt exist creates a new blank file)");
        return new Scanner(System.in).nextLine();

    }

    public void printReferences(List<ReferenceInterface> references) {
        for (ReferenceInterface ref : references) {
            System.out.println(ref.toString(specialCharConverter));
        }
    }

    public void printReferencesWithIds(List<ReferenceInterface> references) {
        Integer i = 1;
        for (ReferenceInterface ref : references) {
<<<<<<< HEAD
            System.out.println(i + ": " + ref.toString(specialCharConverter));
=======
            System.out.println(i + ": " + ref.userFriendlyBibtex());
>>>>>>> d1e6371756f26a2f7f5647301812fd3870c5bd75

            i++;
        }
    }

    public void printAvailableCommands(boolean hasReferences) {
        System.out.println("1) Add book");
        System.out.println("2) Add article");
        System.out.println("3) Add inproceedings");

        if (hasReferences) {
            System.out.println("4) Edit reference");
            System.out.println("5) Delete reference");
            System.out.println("6) List all references");
            System.out.println("7) Make bibtex");
        }

        System.out.println("0) Quit");
    }

    public String getInputString() {
        return new Scanner(System.in).nextLine();
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public void printSuccess(String message) {
        System.out.println(message);
    }

    public String askReferenceId(String action) {
        System.out.print("ID to " + action + ": ");
        return new Scanner(System.in).nextLine();
    }

    public String askHeader() {
        System.out.print("Header: ");
        return new Scanner(System.in).nextLine();
    }

    public String askBookTitle() {
        System.out.print("Booktitle: ");
        return new Scanner(System.in).nextLine();
    }

    public String askAuthor() {
        System.out.print("Author: ");
        return new Scanner(System.in).nextLine();
    }

    public String askTitle() {
        System.out.print("Title: ");
        return new Scanner(System.in).nextLine();
    }

    public String askPublisher() {
        System.out.print("Publisher: ");
        return new Scanner(System.in).nextLine();
    }

    public String askYear() {
        System.out.print("Year: ");
        return new Scanner(System.in).nextLine();
    }

    public String askJournal() {
        System.out.print("Journal: ");
        return new Scanner(System.in).nextLine();
    }

    public String askVolume() {
        System.out.print("Volume: ");
        return new Scanner(System.in).nextLine();
    }

    public String askSeries() {
        System.out.print("Series: ");
        return new Scanner(System.in).nextLine();
    }

    public String askAddress() {
        System.out.print("Address: ");
        return new Scanner(System.in).nextLine();
    }

    public String askEditor() {
        System.out.print("Editor: ");
        return new Scanner(System.in).nextLine();
    }

    public String askOrganization() {
        System.out.print("Organization: ");
        return new Scanner(System.in).nextLine();
    }

    public String askMonth() {
        System.out.print("Month: ");
        return new Scanner(System.in).nextLine();
    }

    public String askNote() {
        System.out.print("Note: ");
        return new Scanner(System.in).nextLine();
    }

    public String askKey() {
        System.out.print("Key: ");
        return new Scanner(System.in).nextLine();
    }

    public void printAdded(String type) {
        System.out.println(type + " was succesfully added.\n");
    }

    public void printEdited() {
        System.out.println("Item was successfully edited.\n");
    }

    public void printDeleted() {
        System.out.println("Item was succesfully deleted.\n");
    }

    // obsolete methods from now on, not yet removed due test and interface dependencies.
    /**
     * not in use anymore with newest UI.
     *
     * @return
     */
    public BookReference readBook() {
        BookReference book = new BookReference();

        System.out.print("Header: ");
        book.setHeading(scanner.nextLine());

        System.out.print("Author: ");
        book.setAuthor(scanner.nextLine());

        System.out.print("Title: ");
        book.setTitle(scanner.nextLine());

        System.out.print("Publisher: ");
        book.setPublisher(scanner.nextLine());

        System.out.print("Year: ");
        book.setYear(scanner.nextLine());

        System.out.print("Volume (optional): ");
        book.setVolume(scanner.nextLine());
        System.out.println("");

        return book;
    }

    /**
     * not in use anymore with newest UI.
     *
     * @return
     */
    public ArticleReference readArticle() {
        ArticleReference article = new ArticleReference();

        System.out.print("Header: ");
        article.setHeading(scanner.nextLine());

        System.out.print("Author: ");
        article.setAuthor(scanner.nextLine());

        System.out.print("Title: ");
        article.setTitle(scanner.nextLine());

        System.out.print("Journal: ");
        article.setJournal(scanner.nextLine());

        System.out.print("Year: ");
        article.setYear(scanner.nextLine());

        System.out.print("Volume: ");
        article.setVolume(scanner.nextLine());

        System.out.println("");

        return article;
    }

    /**
     * not in use anymore with newest UI.
     *
     * @return
     */
    public void printHeadingAlreadyExists(String heading) {
        System.out.println("Heading \"" + heading + "\" already exists. Choose another.");
        System.out.println("");
    }

    /**
     * not in use anymore with newest UI. functionality moved behind controller
     *
     * @return
     */
    public void appendFile(ReferenceInterface reference) {
        fileHandler.appendFile(filename, reference.toString(specialCharConverter));
    }

    /**
     * not in use anymore with newest UI. abandoned due crashing program with
     * incorrect input.
     *
     * @return
     */
    public int getCommand() {
        return Integer.parseInt(scanner.nextLine());
    }
}
