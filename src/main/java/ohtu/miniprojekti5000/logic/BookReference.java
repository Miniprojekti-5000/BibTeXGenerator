package ohtu.miniprojekti5000.logic;

/**
 * Book type Reference.
 * holds info for creating simplified book type bibtex syntax.
 *
 * @author miniprojekti-5000
 */
public class BookReference implements ReferenceInterface
{
    private final String heading, author, title, publisher;
    private final short year;
    
    public BookReference(String heading, String author, String title, String publisher, short year)
    {
        this.heading = heading;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
    }

    public String get_heading_line()
    {
        return "@Book{"+heading+",";
    }
    public String get_author_line()
    {
        return "  author = \""+author+"\",";
    }
    public String get_publisher_line()
    {
        return "  year = "+year+",";
    }
    public String get_end_line()
    {
        return "}";
    }

    public String toString() {
        return null;
    }
}
