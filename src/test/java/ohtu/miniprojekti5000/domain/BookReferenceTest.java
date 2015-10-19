package ohtu.miniprojekti5000.domain;

import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.logic.SpecialCharConverter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookReferenceTest {

    private BookReference reference;
    private SpecialCharConverter converter;

    @Before
    public void setUp() {
        reference = new BookReference();
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
    public void publisherIsSet() {
        String publisher = "publisher";

        reference.setPublisher(publisher);
        assertEquals(publisher, reference.required_fields.get("publisher"));
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
        assertEquals(volume, reference.optional_fields.get("volume"));
    }

    @Test
    public void seriesIsSet() {
        String series = "series";

        reference.setSeries(series);
        assertEquals(series, reference.optional_fields.get("series"));
    }

    @Test
    public void addressIsSet() {
        String address = "address";

        reference.setAddress(address);
        assertEquals(address, reference.optional_fields.get("address"));
    }

    @Test
    public void editionIsSet() {
        String edition = "edition";

        reference.setEdition(edition);
        assertEquals(edition, reference.optional_fields.get("edition"));
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
        reference.setPublisher("publisher");
        reference.setYear("year");

        assertEquals("@Book{" + reference.getHeading() + ",\n"
                + "     year = \"" + reference.getYear() + "\",\n"
                + "     author = \"" + reference.getAuthor() + "\",\n"
                + "     publisher = \"" + reference.getPublisher() + "\",\n"
                + "     title = \"" + reference.getTitle() + "\"\n"
                + "}\n", reference.toString(new SpecialCharConverter()));
    }
}
