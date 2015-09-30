package ohtu.miniprojekti5000.ui;

import java.util.List;
import java.util.Scanner;
import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;

public class IO {

    private final Scanner scanner;

    public IO() {
        scanner = new Scanner(System.in);
    }

    public void printReferences(List<ReferenceInterface> references) {
        for (ReferenceInterface ref : references) {
            System.out.println(ref.toString());
        }
    }

    public void printAvailableCommands(boolean hasReferences) {
        System.out.println("1) Add book");

        if (hasReferences) {
            System.out.println("2) Make bibtex");
        }
        System.out.println("3) Quit");
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

        return book;
    }

    public void printBookAdded() {
        System.out.println("Book was succesfully added.\n");
    }
}
