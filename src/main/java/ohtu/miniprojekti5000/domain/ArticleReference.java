package ohtu.miniprojekti5000.domain;

public class ArticleReference {
    public String heading = "", author = "", title = "", journal = "", year = "", volume =  "";

    public ArticleReference() {
    }

    public ArticleReference(String heading, String author, String title, String journal, String year, String volume) {
        this.heading = heading;
        this.author = author;
        this.title = title;
        this.journal = journal;
        this.year = year;
        this.volume = volume;
    }

    public String getHeading() { return heading; }
    public String getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getJournal() { return journal; }
    public String getYear() { return year; }
    public String getVolume() { return volume; }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "@Book{" + heading + ",\n"
                + "    author = \"" + author + "\",\n"
                + "    title = \"" + title + "\",\n"
                + "    journal = \"" + journal + "\",\n"
                + "    year = \"" + year + "\",\n"
                + "    volume = \"" + volume + "\",\n"
                + "}\n";

    }
}
