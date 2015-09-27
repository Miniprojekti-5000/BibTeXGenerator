package ohtu.miniprojekti5000.logic;

/**
 *
 * @author miniprojekti-5000
 */
public class BibtexGenerator
{
    public BookBibtex create_new_book_bibtex(String heading, String author, String title, String publisher, short year)
    {
        // check here that values are valid. Must be implemented before wendsday. <------------------------------------------------------ todo here.
        
        return new BookBibtex(heading, author, title, publisher, year);
    }
}
