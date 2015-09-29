package ohtu.miniprojekti5000.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookReferenceTest {
    private BookReference reference;

    @Before
    public void setUp() {
        reference = new BookReference();
    }

    @Test
    public void headingIsSet() {
        String heading = "heading";

        reference.setHeading(heading);
        assertEquals(heading, reference.heading);
    }

    @Test
    public void headingIsGet() {
        String heading = "heading";

        reference.setHeading(heading);
        assertEquals(heading, reference.getHeading());
    }

    @Test
    public void authorIsSet() {
        String author = "author";

        reference.setAuthor(author);
        assertEquals(author, reference.author);
    }

    @Test
    public void authorIsGet() {
        String author = "author";

        reference.setAuthor(author);
        assertEquals(author, reference.getAuthor());
    }

    @Test
    public void titleIsSet() {
        String title = "title";

        reference.setTitle(title);
        assertEquals(title, reference.title);
    }

    @Test
    public void titleIsGet() {
        String title = "title";

        reference.setTitle(title);
        assertEquals(title, reference.getTitle());
    }

    @Test
    public void publisherIsSet() {
        String publisher = "publisher";

        reference.setPublisher(publisher);
        assertEquals(publisher, reference.publisher);
    }

    @Test
    public void publisherIsGet() {
        String publisher = "publisher";

        reference.setPublisher(publisher);
        assertEquals(publisher, reference.getPublisher());
    }

    @Test
    public void yearIsSet() {
        String year = "year";

        reference.setYear(year);
        assertEquals(year, reference.year);
    }

    @Test
    public void yearIsGet() {
        String year = "year";

        reference.setYear(year);
        assertEquals(year, reference.getYear());
    }

    @Test
    public void correctBibtexIsOutputted() {
        reference.setHeading("heading");
        reference.setAuthor("author");
        reference.setTitle("title");
        reference.setPublisher("publisher");
        reference.setYear("year");

        assertEquals("@Book{" + reference.getHeading() + ",\n"
                + "    author = \"" + reference.getAuthor() + "\",\n"
                + "    title = \"" + reference.getTitle() + "\",\n"
                + "    year = \"" + reference.getYear() + "\",\n"
                + "    publisher = \"" + reference.getPublisher() + "\",\n"
                + "}\n", reference.toString());
    }
}
