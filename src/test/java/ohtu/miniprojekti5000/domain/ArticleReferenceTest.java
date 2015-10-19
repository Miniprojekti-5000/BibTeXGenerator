package ohtu.miniprojekti5000.domain;

import ohtu.miniprojekti5000.logic.SpecialCharConverter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArticleReferenceTest {

    private ArticleReference reference;
    private SpecialCharConverter converter;

    @Before
    public void setUp() {
        reference = new ArticleReference();
        converter = new SpecialCharConverter();
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
        assertEquals(author, reference.required_fields.get("author"));
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
        assertEquals(title, reference.required_fields.get("title"));
    }

    @Test
    public void titleIsGet() {
        String title = "title";

        reference.setTitle(title);
        assertEquals(title, reference.getTitle());
    }

    @Test
    public void JournalIsSet() {
        String journal = "journal";

        reference.setJournal(journal);
        assertEquals(journal, reference.required_fields.get("journal"));
    }

    @Test
    public void journalIsGet() {
        String journal = "journal";

        reference.setJournal(journal);
        assertEquals(journal, reference.getJournal());
    }

    @Test
    public void yearIsSet() {
        String year = "year";

        reference.setYear(year);
        assertEquals(year, reference.required_fields.get("year"));
    }

    @Test
    public void yearIsGet() {
        String year = "year";

        reference.setYear(year);
        assertEquals(year, reference.getYear());
    }

    @Test
    public void volumeIsSet() {
        String volume = "volume";

        reference.setVolume(volume);
        assertEquals(volume, reference.required_fields.get("volume"));
    }

    @Test
    public void volumeIsGet() {
        String volume = "volume";

        reference.setVolume(volume);
        assertEquals(volume, reference.getVolume());
    }

    @Test
    public void numberIsSet() {
        String number = "number";

        reference.setNumber(number);
        assertEquals(number, reference.optional_fields.get("number"));
    }

    @Test
    public void pageIsSet() {
        String pages = "pages";

        reference.setPages(pages);
        assertEquals(pages, reference.optional_fields.get("pages"));
    }

    @Test
    public void monthIsSet() {
        String month = "month";

        reference.setMonth(month);
        assertEquals(month, reference.optional_fields.get("month"));
    }

    @Test
    public void noteIsSet() {
        String note = "note";

        reference.setNote(note);
        assertEquals(note, reference.optional_fields.get("note"));
    }

    @Test
    public void keyIsSet() {
        String key = "key";

        reference.setKey(key);
        assertEquals(key, reference.optional_fields.get("key"));
    }
    
    @Test
    public void optionalFieldisSet() {
        
    }

    @Test
    public void optionalFieldGetReturnCorrectOutput() {
        String field = "field";

        assertEquals("     " + field + " = \"" + field + "\"\n", reference.getOptionalField(converter, field, field, Boolean.FALSE));

    }

    @Test
    public void requiredFieldGetReturnCorrectOutput() {
        String field = "field";

        assertEquals("     " + field + " = \"" + field + "\"\n", reference.getRequiredField(converter, field, field, Boolean.FALSE));

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
                + "     volume = \"" + reference.getVolume() + "\",\n"
                + "     journal = \"" + reference.getJournal() + "\",\n"
                + "     year = \"" + reference.getYear() + "\",\n"
                + "     author = \"" + reference.getAuthor() + "\",\n"
                + "     title = \"" + reference.getTitle() + "\"\n"
                + "}\n", reference.toString(new SpecialCharConverter()));
    }
}
