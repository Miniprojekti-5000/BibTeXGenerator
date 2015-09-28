package ohtu.miniprojekti5000.logic;

public class BookReference implements ReferenceInterface {
    private String heading = "", author = "", title = "", publisher = "", year = "";

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

    @Override
    public String toString() {
        return "@Book{" + heading + ",\n"
                + "    author = \"" + author + "\",\n"
                + "    title = \"" + title + "\",\n"
                + "    year = \"" + year + "\",\n"
                + "    publisher = \"" + publisher + "\",\n"
                + "}\n";

    }
}
