package ohtu.miniprojekti5000.ui;

import java.util.ArrayList;
import java.util.List;
import ohtu.miniprojekti5000.logic.BookReference;
import ohtu.miniprojekti5000.logic.ReferenceInterface;
import ohtu.miniprojekti5000.logic.References;

import java.util.Scanner;

public class UI {
    
    
    private References references;
    private UserInput u_input;

    public UI(References references) {
        u_input = new UserInput();
        this.references = references;
    }
    
    public void run2(byte ui_state)
    {
        print_available_commands(ui_state);
        process_user_input(ui_state);
    }
    
    
    
    
    public void print_available_commands(byte ui_state)
    {
        switch (ui_state)
        {
            case 0 : // print: "add book"
            {
                System.out.println("available commands: add book");
            } break;
            case 1 : // print: "add book, make bibtex"
            {
                System.out.println("available commands: add book, make bibtex");
            } break;
        }
    }
    
    public void print_all_bibtexs()
    {
        for (ReferenceInterface ref : references.getAll())
        {
            System.out.println(ref.toString());
        }
    }
    
    ArrayList<String> available_commands = new ArrayList<String>();
    public void process_user_input(byte ui_state)
    {
        available_commands = new ArrayList<String>();
        
        // commands index:
        // 0: add book
        // 1: make bibtex
        
        switch (ui_state)
        {
            case 0 :  // add all available commands to list and ask user input
            {
                available_commands.add("add book");
                if (u_input.ask_input(1)) process_oneline_command();
                else run2(ui_state);
            } break;
            case 1 : // add all available commands to list
            {
                available_commands.add("add book");
                available_commands.add("make bibtex");
                if (u_input.ask_input(1)) process_oneline_command();
                else run2(ui_state);
            } break;
            case 2 :
            {
                
            }
        }
    }
    
    public void process_oneline_command()
    {
        int command = -1;
        if (available_commands.contains(u_input.getUserInput()[0])) // if "valid command"
        {
            for (int i = 0; i < available_commands.size(); i++)
            {
                if (available_commands.get(i).matches(
                        u_input.getUserInput()[0])) command = i;
            }
        }
        
        switch (command)
        {
            case -1 : // not valid command
            {
                run2((byte)0);
            }
            case 0 : // add book
            {
                ask_book_type_infos();
            } break;
            case 1 : // make bibtex
            {
                make_bibtexs();
            } break;
        }
    }

    public void ask_book_type_infos()
    {
        System.out.println("please enter: header, author, title, publisher and year. hit Enter between every line");
        if (u_input.ask_input(5))
        {
            BookReference bookreference = new BookReference();
            bookreference.setHeading(u_input.getUserInput()[0]);
            bookreference.setAuthor(u_input.getUserInput()[1]);
            bookreference.setTitle(u_input.getUserInput()[2]);
            bookreference.setPublisher(u_input.getUserInput()[3]);
            bookreference.setYear(u_input.getUserInput()[4]);
            references.add(bookreference);
        
            run2((byte)1);
        } else run2((byte) 0);
    }
    public void make_bibtexs()
    {
        for (ReferenceInterface ref : references.getAll())
        {
            System.out.println(ref.toString());
        }
        
        if (references.getAll().size() == 0) run2((byte) 0);
        else run2((byte) 1);
    }
}