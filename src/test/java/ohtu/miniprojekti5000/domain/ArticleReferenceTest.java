package ohtu.miniprojekti5000.domain;

import ohtu.miniprojekti5000.logic.SpecialCharConverter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArticleReferenceTest {

    private ArticleReference reference;

    @Before
    public void setUp() {
        reference = new ArticleReference("heading", "author", "title", "journal", "year", "volume");
    }

    @Test
    public void headingIsSet() {
        String heading = "newHeading";

        reference.setHeading(heading);
        assertEquals(heading, reference.heading);
    }

    @Test
    public void headingIsGet() {
        String heading = "heading";

        assertEquals(heading, reference.getHeading());
    }

    @Test
    public void authorIsSet() {
        String author = "newAuthor";

        reference.setAuthor(author);
        assertEquals(author, reference.author);
    }

    @Test
    public void authorIsGet() {
        String author = "author";

        assertEquals(author, reference.getAuthor());
    }

    @Test
    public void titleIsSet() {
        String title = "newTitle";

        reference.setTitle(title);
        assertEquals(title, reference.title);
    }

    @Test
    public void titleIsGet() {
        String title = "title";

        assertEquals(title, reference.getTitle());
    }

    @Test
    public void JournalIsSet() {
        String journal = "newJournal";

        reference.setJournal(journal);
        assertEquals(journal, reference.journal);
    }

    @Test
    public void journalIsGet() {
        String journal = "journal";

        assertEquals(journal, reference.getJournal());
    }

    @Test
    public void yearIsSet() {
        String year = "newYear";

        reference.setYear(year);
        assertEquals(year, reference.year);
    }

    @Test
    public void yearIsGet() {
        String year = "year";

        assertEquals(year, reference.getYear());
    }

    @Test
    public void volumeIsSet() {
        String volume = "newVolume";

        reference.setVolume(volume);
        assertEquals(volume, reference.volume);
    }

    @Test
    public void volumeIsGet() {
        String volume = "volume";

        assertEquals(volume, reference.getVolume());
    }

    @Test
    public void correctBibtexIsOutputted() {
        reference.setHeading("heading");
        reference.setAuthor("author");
        reference.setTitle("title");
        reference.setJournal("journal");
        reference.setYear("year");
        reference.setVolume("volume");

        assertEquals("@Article{" + reference.getHeading() + ",\n"
                + "    author = \"" + reference.getAuthor() + "\",\n"
                + "    title = \"" + reference.getTitle() + "\",\n"
                + "    journal = \"" + reference.getJournal() + "\",\n"
                + "    year = \"" + reference.getYear() + "\",\n"
                + "    volume = \"" + reference.getVolume() + "\",\n"
                + "}\n", reference.toString(new SpecialCharConverter()));
    }
}
