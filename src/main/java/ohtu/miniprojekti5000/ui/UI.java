package ohtu.miniprojekti5000.ui;

import ohtu.miniprojekti5000.logic.BookReference;
import ohtu.miniprojekti5000.logic.ReferenceInterface;
import ohtu.miniprojekti5000.logic.References;

import java.util.Scanner;

public class UI {
    private References references;

    public UI(References references) {
        this.references = references;
    }

    public void run() {
        boolean addNewEntry = true;

        Scanner user_input = new Scanner(System.in);

        while (addNewEntry) {
            System.out.println("What type of reference?");
            System.out.println("1) Book");
            System.out.println("");

            System.out.print("Make your selection and hit ENTER: ");

            if (user_input.next().equals("1")) {
                BookReference bookReference = new BookReference();

                System.out.println("Add new \"Book\" entry");
                System.out.println("");

                System.out.print("Heading:");
                bookReference.setHeading(user_input.next());

                System.out.print("Author:");
                bookReference.setAuthor(user_input.next());

                System.out.print("Title:");
                bookReference.setTitle(user_input.next());

                System.out.print("Publisher:");
                bookReference.setPublisher(user_input.next());

                System.out.print("Year:");
                bookReference.setYear(user_input.next());

                references.add(bookReference);

                System.out.println("");
                System.out.println("");
                System.out.println("Entry added!");

                boolean invalidSelection = true;

                while(invalidSelection) {
                    System.out.println("Choose: 1) Add another entry 2) Get all entries in Bibtex-format");
                    System.out.println("");

                    System.out.print("Make your selection and hit ENTER: ");

                    String input = user_input.next();

                    if (input.equals("1")) {
                        invalidSelection = false;
                    } else if(input.equals("2")) {
                        addNewEntry = false;
                        invalidSelection = false;
                    }
                }
            } else {
                System.out.println("Invalid selection.");
                System.out.println("");
            }
        }

        System.out.println("");
        System.out.println("Generated BibTeX:");
        System.out.println("");

        for(ReferenceInterface reference : references.getAll()) {
            System.out.println(reference);
            System.out.println("");
        }
    }
}
