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
    
    public void run(int user_interface_state) {
        Scanner scanner = new Scanner(System.in);
        
        switch(user_interface_state)
        {
            case 0 : // asks for what to do.
            {
                user_interface_state = askWhatToDo(scanner);
                
            } break;
                
            case 1 : // asks book type inputs
            {
                askBookTypeInputs(scanner);
                user_interface_state = 0;
            } break;
            case 2 : // prints bibtex
            {
                printBibtexs();
                user_interface_state = 0;
            } break;
        }
        
        run(user_interface_state);
    }
    private int askWhatToDo(Scanner scanner)
    {
        printChoices();
        int input = selectionInquiry(scanner);
        while (!inputValidator(input))
        {
            printChoices();
            input = selectionInquiry(scanner);
        }
        return input;
    }
    
    private void askBookTypeInputs(Scanner scanner)
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
    }
    
    
    private void printBibtexs()
    {
        System.out.println("Generated bibtex");
        for (ReferenceInterface reference : references.getAll())
        {
            System.out.println(reference.toString());
            System.out.println("");
        }
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
}