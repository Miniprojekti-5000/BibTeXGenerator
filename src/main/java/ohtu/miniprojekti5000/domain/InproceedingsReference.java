package ohtu.miniprojekti5000.domain;

import ohtu.miniprojekti5000.logic.SpecialCharConverter;

import java.util.HashMap;
import java.util.Map;

public class InproceedingsReference implements ReferenceInterface {
    public String heading = "";
    public Map<String, String> required_fields = new HashMap<>();
    public Map<String, String> optional_fields = new HashMap<>();

    public InproceedingsReference() {
        initializeRequiredFields();
    }

    private void initializeRequiredFields() {
        required_fields.put("author", "");
        required_fields.put("title", "");
        required_fields.put("booktitle", "");
        required_fields.put("year", "");
    }

    public InproceedingsReference(String heading, String author, String title, String bookTitle, String year) {
        this.heading = heading;
        required_fields.put("author", author);
        required_fields.put("title", title);
        required_fields.put("booktitle", bookTitle);
        required_fields.put("year", year);
    }

    public String getHeading() { return heading; }
    public String getAuthor() { return required_fields.get("author"); }
    public String getTitle() { return required_fields.get("title"); }
    public String getBookTitle() { return required_fields.get("booktitle"); }
    public String getYear() { return required_fields.get("year"); }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setAuthor(String author) {
        required_fields.put("author", author);
    }

    public void setTitle(String title) {
        required_fields.put("title", title);
    }

    public void setBookTitle(String bookTitle) {
        required_fields.put("booktitle", bookTitle);
    }

    public void setYear(String year) {
        required_fields.put("year", year);
    }

    public void setVolume(String volume) {
        setOptionalField("volume", volume);
    }

    public void setSeries(String series) {
        setOptionalField("series", series);
    }

    public void setAddress(String address) {
        setOptionalField("address", address);
    }

    public void setEditor(String editor) {
        setOptionalField("editor", editor);
    }

    public void setOrganization(String organization) {
        setOptionalField("organization", organization);
    }
    public void setPublisher(String publisher) {
        setOptionalField("publisher", publisher);
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

    @Override
    public String toString(SpecialCharConverter specialCharConverter) {
        return "@Inproceedings{" + specialCharConverter.convertSpecialChars(heading) + ",\n"
                + getRequiredFieldsOutput(specialCharConverter)
                + getOptionalFieldsOutput(specialCharConverter)
                + "}\n";

    }
    @Override
     public String userFriendlyBibtex() {
        return getAuthor() + ". "
                + getTitle() + ". "
                + getBookTitle() + ". "
                + getYear() + ".\n";
    }

    private void setOptionalField(String fieldName, String fieldValue) {
        if(!fieldValue.isEmpty())
            optional_fields.put(fieldName, fieldValue);
        else {
            if(optional_fields.containsKey(fieldValue)) optional_fields.remove(fieldName);
        }
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

}
