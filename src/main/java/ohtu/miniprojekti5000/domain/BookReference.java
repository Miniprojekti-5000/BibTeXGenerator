package ohtu.miniprojekti5000.domain;

import ohtu.miniprojekti5000.logic.SpecialCharConverter;

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
    public String toString(SpecialCharConverter specialCharConverter) {
        return "@Book{" + specialCharConverter.convertSpecialChars(heading) + ",\n"
                + "    author = \"" + specialCharConverter.convertSpecialChars(author) + "\",\n"
                + "    title = \"" + specialCharConverter.convertSpecialChars(title) + "\",\n"
                + "    year = \"" + specialCharConverter.convertSpecialChars(year) + "\",\n"
                + "    publisher = \"" + specialCharConverter.convertSpecialChars(publisher) + "\",\n"
                + "}\n";

    }
}
