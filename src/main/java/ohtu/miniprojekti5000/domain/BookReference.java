package ohtu.miniprojekti5000.domain;

import ohtu.miniprojekti5000.logic.SpecialCharConverter;

import java.util.HashMap;
import java.util.Map;

public class BookReference implements ReferenceInterface {
    public String heading = "";
    public Map<String, String> required_fields = new HashMap<>();
    public Map<String, String> optional_fields = new HashMap<>();

    public BookReference() {
        initializeRequiredFields();
    }

    private void initializeRequiredFields() {
        required_fields.put("author", "");
        required_fields.put("title", "");
        required_fields.put("publisher", "");
        required_fields.put("year", "");
    }

    public BookReference(String heading, String author, String title, String publisher, String year) {
        this.heading = heading;
        required_fields.put("author", author);
        required_fields.put("title", title);
        required_fields.put("publisher", publisher);
        required_fields.put("year", year);
    }

    public String getHeading() { return heading; }
    public String getAuthor() { return required_fields.get("author"); }
    public String getTitle() { return required_fields.get("title"); }
    public String getPublisher() { return required_fields.get("publisher"); }
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

    public void setPublisher(String publisher) {
        required_fields.put("publisher", publisher);
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

    public void setEdition(String edition) {
        setOptionalField("edition", edition);
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
        return "@Book{" + specialCharConverter.convertSpecialChars(heading) + ",\n"
                + getRequiredFieldsOutput(specialCharConverter)
                + getOptionalFieldsOutput(specialCharConverter)
                + "}\n";

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
