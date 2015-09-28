package ohtu.miniprojekti5000.logic;

/**
 * Book type Reference. holds info for creating simplified book type bibtex
 * syntax.
 *
 * @author miniprojekti-5000
 */
public class BookReference implements ReferenceInterface {

    private String heading, author, title, publisher;
    private String year;

    public BookReference() {
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String get_heading_line() {
        return "@Book{" + heading + ",";
    }

    public String get_author_line() {
        return "author = \"" + author + "\",";
    }

    public String get_title_line() {
        return "  title = \"" + title + "\",";
    }

    public String get_year_line() {
        return "  year = \"" + year + "\",";
    }

    public String get_publisher_line() {
        return "  publisher = \"" + publisher + "\",";
    }

    public String get_end_line() {
        return "}";
    }

    public String toString() {
        return get_heading_line() + "\n"
                + get_author_line() + "\n"
                + get_title_line() + "\n"
                + get_year_line() + "\n"
                + get_publisher_line() + "\n"
                + get_end_line();
    }

}
