package ohtu.miniprojekti5000.domain;

import ohtu.miniprojekti5000.logic.SpecialCharConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArticleReference implements ReferenceInterface  {
    public String heading = "";
    public Map<String, String> required_fields = new HashMap<>();
    public Map<String, String> optional_fields = new HashMap<>();

    public ArticleReference() {
        initializeRequiredFields();
    }

    private void initializeRequiredFields() {
        required_fields.put("author", "");
        required_fields.put("title", "");
        required_fields.put("journal", "");
        required_fields.put("year", "");
        required_fields.put("volume", "");
    }

    public ArticleReference(String heading, String author, String title, String journal, String year, String volume) {
        this.heading = heading;

        required_fields.put("author", author);
        required_fields.put("title", title);
        required_fields.put("journal", journal);
        required_fields.put("year", year);
        required_fields.put("volume", volume);
    }

    public String getHeading() { return heading; }
    public String getAuthor() { return required_fields.get("author"); }
    public String getTitle() { return required_fields.get("title"); }
    public String getJournal() { return required_fields.get("journal"); }
    public String getYear() { return required_fields.get("year"); }
    public String getVolume() { return required_fields.get("volume"); }
    public String getNumber() { return optional_fields.get("number"); }
    public String getPages() { return optional_fields.get("pages"); }
    public String getMonth() { return optional_fields.get("month"); }
    public String getNote() { return optional_fields.get("note"); }
    public String getKey() { return optional_fields.get("key"); }
    

    public void setHeading(String heading) {
        this.heading = heading;
    }
    public void setAuthor(String author) {
        required_fields.put("author", author);
    }
    public void setTitle(String title) {
        required_fields.put("title", title);
    }
    public void setJournal(String journal) {
        required_fields.put("journal", journal);
    }
    public void setYear(String year) {
        required_fields.put("year", year);
    }
    public void setVolume(String volume) {
        required_fields.put("volume", volume);
    }

    private void setOptionalField(String fieldName, String fieldValue) {
        if(!fieldValue.isEmpty())
            optional_fields.put(fieldName, fieldValue);
        else {
            if(optional_fields.containsKey(fieldValue)) optional_fields.remove(fieldName);
        }
    }
    public void setNumber(String number) {
        setOptionalField("number", number);
    }

    public void setPages(String pages) {
        setOptionalField("pages", pages);
    }

    public void setMonth(String month) {
        setOptionalField("month", month);
    }

    public void setNote(String note) {
        setOptionalField("note", note);
    }

    public void setKey(String key) {
        setOptionalField("key", key);
    }

    public String getOptionalField(SpecialCharConverter specialCharConverter, String field, String fieldName, Boolean hasNext) {
        if(!field.isEmpty()) {
            String addComma = "";

            if(hasNext) addComma = ",";

            return "     " + fieldName + " = \"" + specialCharConverter.convertSpecialChars(field) + "\"" + addComma + "\n";
        }

        return null;
    }

    public String getRequiredField(SpecialCharConverter specialCharConverter, String field, String fieldName, Boolean hasNext) {
        String addComma = "";

        if(hasNext) addComma = ",";

        return "     " + fieldName + " = \"" + specialCharConverter.convertSpecialChars(field) + "\"" + addComma + "\n";
    }


    private String getOptionalFieldsOutput(SpecialCharConverter specialCharConverter) {
        String optional_fields_output = "";
        Integer curField  = 1;

        for (Map.Entry<String, String> entry : optional_fields.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();

            if(curField < optional_fields.size())
                optional_fields_output += getOptionalField(specialCharConverter, fieldValue, fieldName, true);
            else
                optional_fields_output += getOptionalField(specialCharConverter, fieldValue, fieldName, false);

            curField++;
        }

        return optional_fields_output;
    }

    private String getRequiredFieldsOutput(SpecialCharConverter specialCharConverter) {
        String required_fields_output = "";

        Integer curField = 1;
        for (Map.Entry<String, String> entry : required_fields.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();

            if(curField < required_fields.size())
                required_fields_output += getRequiredField(specialCharConverter, fieldValue, fieldName, true);
            else {
                if(optional_fields.size() > 0)
                    required_fields_output += getRequiredField(specialCharConverter, fieldValue, fieldName, true);
                else
                    required_fields_output += getRequiredField(specialCharConverter, fieldValue, fieldName, false);
            }

            curField++;
        }

        return required_fields_output;
    }

    @Override
    public String toString(SpecialCharConverter specialCharConverter) {
        return "@Article{" + specialCharConverter.convertSpecialChars(heading) + ",\n"
                + getRequiredFieldsOutput(specialCharConverter)
                + getOptionalFieldsOutput(specialCharConverter)
                + "}\n";

    }
    @Override
     public String userFriendlyBibtex() {
        return getAuthor() + ". "
                + getTitle() + ". "
                + getJournal() + ". "
                + getVolume() + ", "
                + getYear() + ".\n";
    }

    @Override
    public void setBookTitle(String bookTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSeries(String series) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAddress(String address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEditor(String editor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOrganization(String organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPublisher(String publisher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEdition(String edition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
