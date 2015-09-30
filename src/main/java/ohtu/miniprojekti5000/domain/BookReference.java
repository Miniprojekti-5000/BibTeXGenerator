package ohtu.miniprojekti5000.domain;

public class BookReference implements ReferenceInterface {
    public String heading = "", author = "", title = "", publisher = "", year = "";

    public BookReference() {
    }

    public BookReference(String heading, String author, String title, String publisher, String year) {
        this.heading = heading;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
    }

    public String getHeading() { return heading; }
    public String getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getPublisher() { return publisher; }
    public String getYear() { return year; }

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
