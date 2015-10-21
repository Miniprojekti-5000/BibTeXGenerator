package ohtu.miniprojekti5000.domain;

import ohtu.miniprojekti5000.logic.SpecialCharConverter;

public interface ReferenceInterface
{
    String getHeading();
    void setHeading(String heading);
    void setAuthor(String author);
    void setTitle(String title);
    void setBookTitle(String bookTitle);
    void setYear(String year);
    void setVolume(String volume);
    void setSeries(String series);
    void setAddress(String address);
    void setEditor(String editor);
    void setOrganization(String organization);
    void setPublisher(String publisher);
    void setMonth(String month);
    void setNote(String note);
    void setKey(String key);
    void setJournal(String journal);
    void setNumber(String number);
    void setPages(String pages);
    String toString(SpecialCharConverter specialCharConverter);
    String userFriendlyBibtex();
}
