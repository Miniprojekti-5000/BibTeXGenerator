package ohtu.miniprojekti5000.ui;

//This class is for testing purposes only.

import java.util.ArrayList;
import java.util.List;
import ohtu.miniprojekti5000.domain.ArticleReference;
import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;

public class TestIO implements IO{
    
    private int command;
    private String header;
    private String author;
    private String title;
    private String publisher;
    private String year;
    private String journal;
    private String volume;
    private String confirmation;
    
    public TestIO(int command, String ... values) {
        this.command = command;
        this.header = values[0];
        author = values[1];
        title = values[2];
        publisher = values[3];
        year = values[4];
        journal = values[5];
        volume = values[6];

    }
    public TestIO(int command) {
        this.command = command;
    }

    @Override
    public void printReferences(List<ReferenceInterface> references) {
        this.command = 4; //no infinite loop is created
        for (ReferenceInterface ref : references) {
            System.out.println(ref.toString());
        }
    }

    @Override
    public void printAvailableCommands(boolean hasReferences) {
        System.out.println("Here user sees commands");
        
    }

    @Override
    public int getCommand() {
        return command;
    }
    
    public void setCommand(int command) {
        this.command = command;
    }

    @Override
    public BookReference readBook() {
        this.command = 4; //no infinite loop is created
        BookReference book = new BookReference();
        book.setHeading(header);
        book.setAuthor(author);
        book.setTitle(title);
        book.setPublisher(publisher);
        book.setYear(year);
        return book;
    }

    @Override
    public ArticleReference readArticle() {
        this.command = 4; //no infinite loop is created
        ArticleReference art = new ArticleReference();
        art.setHeading(header);
        art.setAuthor(author);
        art.setTitle(title);
        art.setJournal(journal);
        art.setYear(year);
        art.setVolume(volume);
        return art;
    }

    @Override
    public void printAdded(String type) {
        this.confirmation = type + " was successfully added.";
    }
    public String getConfirmation() {
        return confirmation;
    }

    @Override
    public void printHeadingAlreadyExists(String heading) {
        System.out.println("Heading \"" + heading + "\" already exists. Choose another.");
        System.out.println("");
    }
    
}
