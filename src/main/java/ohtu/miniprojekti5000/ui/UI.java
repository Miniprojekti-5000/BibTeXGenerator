package ohtu.miniprojekti5000.ui;

import ohtu.miniprojekti5000.logic.BookReference;
import ohtu.miniprojekti5000.logic.ReferenceInterface;
import ohtu.miniprojekti5000.logic.References;

import java.util.Scanner;

public class UI {
    
    
    private References references;
    //private Scanner user_input;
    private int user_interface_state;

    public UI(References references) {
        this.references = references;
        //user_input = new Scanner(System.in);
        user_interface_state = 0;
    }
    
    private void printChoices()
    {
        System.out.println("select one and hit ENTER:\n");
        if (references.getAll().size() == 0) // references cannot be printed
        {
            System.out.println("1) add book");
        } else
        {
            System.out.println("1) add book");
            System.out.println("2) create bibtex from all entries");
        }
    }
    
    
    
    
    private String show_selection(int selection)
    {
        switch (selection)
        {
            case 1 : return "\"Book\" type selected\n";
        }
        return "selection not valid";
    }
    private int selectionInquiry(Scanner user_input)
    {
        return user_input.nextInt();
    }
    
    private String headingInquiry(Scanner user_input)
    {
        System.out.println("Heading:");
        return user_input.nextLine();
    }
    private String authorInquiry(Scanner user_input)
    {
        System.out.println("Author:");
        return user_input.nextLine();
    }
    private String titleInquiry(Scanner user_input)
    {
        System.out.println("Title:");
        return user_input.nextLine();
    }
    private String publisherInquiry(Scanner user_input)
    {
        System.out.println("Publisher:");
        return user_input.nextLine();
    }
    private String yearInquiry(Scanner user_input)
    {
        System.out.println("Year:");
        return user_input.nextLine();
    }
    
    private boolean inputValidator(int input)
    {
        switch (input)
        {
            case 0 : return false;
            case 1 : return true;
            case 2 : return true;
        }
        return false;
    }
    

    public void run(int user_interface_state) {
        Scanner scanner = new Scanner(System.in);
        
        switch(user_interface_state)
        {
            case 0 : // asks for what to do.
            {
                printChoices();
                int input = selectionInquiry(scanner);
                while (!inputValidator(input))
                {
                    printChoices();
                    input = selectionInquiry(scanner);
                }
                user_interface_state = input;
            } break;
                
            case 1 : // asks book type inputs
            {
                System.out.println(show_selection(user_interface_state));
                String heading = headingInquiry(scanner);
                String author = authorInquiry(scanner);
                String title = titleInquiry(scanner);
                String publisher = publisherInquiry(scanner);
                String year = yearInquiry(scanner);
                
                BookReference bookreference = new BookReference();
                bookreference.setHeading(heading);
                bookreference.setAuthor(author);
                bookreference.setTitle(title);
                bookreference.setPublisher(publisher);
                bookreference.setYear(year);
                references.add(bookreference);
                user_interface_state = 0;
            } break;
            case 2 : // prints bibtex
            {
                System.out.println("Generated bibtex");
                for (ReferenceInterface reference : references.getAll())
                {
                    System.out.println(reference.toString());
                    System.out.println("");
                }
                user_interface_state = 0;
            } break;
        }
        
        run(user_interface_state);
        
        
        
        
//        boolean addNewEntry = true;
//
//        Scanner user_input = new Scanner(System.in);
//
//        while (addNewEntry) {
//            System.out.println("What type of reference?");
//            System.out.println("1) Book");
//            System.out.println("");
//
//            System.out.print("Make your selection and hit ENTER: ");
//
//            if (user_input.nextLine().equals("1")) {
//                BookReference bookReference = new BookReference();
//
//                System.out.println("Add new \"Book\" entry");
//                System.out.println("");
//
//                System.out.print("Heading:");
//                bookReference.setHeading(user_input.nextLine());
//
//                System.out.print("Author:");
//                bookReference.setAuthor(user_input.nextLine());
//
//                System.out.print("Title:");
//                bookReference.setTitle(user_input.nextLine());
//
//                System.out.print("Publisher:");
//                bookReference.setPublisher(user_input.nextLine());
//
//                System.out.print("Year:");
//                bookReference.setYear(user_input.nextLine());
//
//                references.add(bookReference);
//
//                System.out.println("");
//                System.out.println("");
//                System.out.println("Entry added!");
//
//                boolean invalidSelection = true;
//
//                while(invalidSelection) {
//                    System.out.println("Choose: 1) Add another entry 2) Get all entries in Bibtex-format");
//                    System.out.println("");
//
//                    System.out.print("Make your selection and hit ENTER: ");
//
//                    String input = user_input.next();
//
//                    if (input.equals("1")) {
//                        invalidSelection = false;
//                    } else if(input.equals("2")) {
//                        addNewEntry = false;
//                        invalidSelection = false;
//                    }
//                }
//            } else {
//                System.out.println("Invalid selection.");
//                System.out.println("");
//            }
//        }
//
//        System.out.println("");
//        System.out.println("Generated BibTeX:");
//        System.out.println("");
//
//        for(ReferenceInterface reference : references.getAll()) {
//            System.out.println(reference);
//            System.out.println("");
//        }
    }
}
