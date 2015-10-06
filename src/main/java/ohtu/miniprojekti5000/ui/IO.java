package ohtu.miniprojekti5000.ui;

import java.util.List;
import java.util.Scanner;

import ohtu.miniprojekti5000.data_access.FileHandler;
import ohtu.miniprojekti5000.domain.ArticleReference;
import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;
import ohtu.miniprojekti5000.logic.SpecialCharConverter;

public class IO {
    public final SpecialCharConverter specialCharConverter;
    private final Scanner scanner;
    private final FileHandler fileHandler;
    private String filename;

    public IO() {
        filename = "test.bib";
        fileHandler = new FileHandler();
        fileHandler.loadFile(filename);

        specialCharConverter = new SpecialCharConverter();

        specialCharConverter.addReplace("å", "\\a");
        specialCharConverter.addReplace("ä", "\\\"a");
        specialCharConverter.addReplace("ö", "\\\"o");

        scanner = new Scanner(System.in);
    }

    public void printReferences(List<ReferenceInterface> references) {
        for (ReferenceInterface ref : references) {
            System.out.println(ref.toString(specialCharConverter));
        }
    }

    public void printAvailableCommands(boolean hasReferences) {
        System.out.println("1) Add book");
        System.out.println("2) Add article");

        if (hasReferences) {
            System.out.println("3) Make bibtex");
        }
        System.out.println("4) Quit");
    }

    public int getCommand() {
        return Integer.parseInt(scanner.nextLine());
    }

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
        System.out.println("");

        return book;
    }

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

    public void printAdded(String type) {
        System.out.println(type + " was succesfully added.\n");
    }

    public void printHeadingAlreadyExists(String heading) {
        System.out.println("Heading \"" + heading + "\" already exists. Choose another.");
        System.out.println("");
    }

    public void appendFile(ReferenceInterface reference) {
        fileHandler.appendFile(filename, reference.toString(specialCharConverter));
    }

    public void printReferencesFromFile() {
        System.out.println("All references saved to " + filename);
        System.out.println("");
        System.out.println("Generated BibTeX:");
        System.out.println("");
        System.out.println(fileHandler.getContent());
    }
}
