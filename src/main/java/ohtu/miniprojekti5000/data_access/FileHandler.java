package ohtu.miniprojekti5000.data_access;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ohtu.miniprojekti5000.domain.ArticleReference;
import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.InproceedingsReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;

/**
 *
 * @author miniprojekti-5000
 */
public class FileHandler
{
    private final Pattern fieldPattern = Pattern.compile("([a-zA-Z]+)(\\s)*=(\\s)*\"(\\s)*([a-zA-Z]+)(\\s)*\"(\\s)*(,?)");

    private ArrayList<String> btx;
    
    public boolean loadFile(String path)
    {
        try
        {
            Scanner filescanner = new Scanner(new FileReader(path));
            btx = new ArrayList<>();
            while (filescanner.hasNext())
            {
                String line = filescanner.nextLine();
                btx.add(line);
            }
            return true;
        } catch (FileNotFoundException ex)
        {
            appendFile(path, "");
            return false;
        }
    }
    
    public ArrayList<ReferenceInterface> parseFile()
    {
        ArrayList<ReferenceInterface> returnlist = new ArrayList<>();
        
        for (int i = 0; i < btx.size(); i++)
        {
            if (btx.get(i).trim().charAt(0) == '@') // header line
            {
                String[] potentialStr = btx.get(i).substring(1).trim().split("\\{");
                if (potentialStr[0].toLowerCase().contains("book".toLowerCase())) // is book type bibtex
                {
                    BookReference br = new BookReference();
                    br.setHeading(potentialStr[1].split(",")[0].trim());
                    Boolean hasNextField = true;
                    while(hasNextField) {
                        Matcher m = fieldPattern.matcher(btx.get(i).trim());

                        // if our pattern matches the string, we can try to extract our groups
                        if (m.find()) {
                            // get the two groups we were looking for
                            String fieldName = m.group(1);
                            String fieldValue = m.group(5);
                            String possibleComma = m.group(8);

                            if (fieldName.toLowerCase().equals("author".toLowerCase()))
                                br.setAuthor(fieldValue);
                            else if (fieldName.toLowerCase().equals("title".toLowerCase()))
                                br.setTitle(fieldValue);
                            else if (fieldName.toLowerCase().equals("publisher".toLowerCase()))
                                br.setPublisher(fieldValue);
                            else if (fieldName.toLowerCase().equals("year".toLowerCase()))
                                br.setYear(fieldValue);
                            else if (fieldName.toLowerCase().equals("volume".toLowerCase()))
                                br.setVolume(fieldValue);
                            else if (fieldName.toLowerCase().equals("address".toLowerCase()))
                                br.setAddress(fieldValue);
                            else if (fieldName.toLowerCase().equals("series".toLowerCase()))
                                br.setSeries(fieldValue);
                            else if (fieldName.toLowerCase().equals("edition".toLowerCase()))
                                br.setEdition(fieldValue);
                            else if (fieldName.toLowerCase().equals("month".toLowerCase()))
                                br.setMonth(fieldValue);
                            else if (fieldName.toLowerCase().equals("note".toLowerCase()))
                                br.setNote(fieldValue);
                            else if (fieldName.toLowerCase().equals("key".toLowerCase()))
                                br.setKey(fieldValue);


                            if(possibleComma.isEmpty()) hasNextField = false;
                        }
                        i++;
                    }
                    i++;
                    /*
                    br.setAuthor(btx.get(i).split("\"")[1].trim());
                    i++;
                    br.setTitle(btx.get(i).split("\"")[1].trim());
                    i++;
                    br.setYear(btx.get(i).split("\"")[1].trim());
                    i++;
                    br.setPublisher(btx.get(i).split("\"")[1].trim());
                    */
                    returnlist.add(br);
                }
                else if (potentialStr[0].toLowerCase().contains("article".toLowerCase())) // is article type bibtex
                {
                    ArticleReference ar = new ArticleReference();
                    ar.setHeading(potentialStr[1].split(",")[0].trim());
                    i++;
                    Boolean hasNextField = true;
                    while(hasNextField) {
                        Matcher m = fieldPattern.matcher(btx.get(i).trim());

                        // if our pattern matches the string, we can try to extract our groups
                        if (m.find()) {
                            // get the two groups we were looking for
                            String fieldName = m.group(1);
                            String fieldValue = m.group(5);
                            String possibleComma = m.group(8);

                            if (fieldName.toLowerCase().equals("author".toLowerCase()))
                                ar.setAuthor(fieldValue);
                            else if (fieldName.toLowerCase().equals("title".toLowerCase()))
                                ar.setTitle(fieldValue);
                            else if (fieldName.toLowerCase().equals("journal".toLowerCase()))
                                ar.setJournal(fieldValue);
                            else if (fieldName.toLowerCase().equals("year".toLowerCase()))
                                ar.setYear(fieldValue);
                            else if (fieldName.toLowerCase().equals("volume".toLowerCase()))
                                ar.setVolume(fieldValue);
                            else if (fieldName.toLowerCase().equals("number".toLowerCase()))
                                ar.setNumber(fieldValue);
                            else if (fieldName.toLowerCase().equals("pages".toLowerCase()))
                                ar.setPages(fieldValue);
                            else if (fieldName.toLowerCase().equals("month".toLowerCase()))
                                ar.setMonth(fieldValue);
                            else if (fieldName.toLowerCase().equals("key".toLowerCase()))
                                ar.setKey(fieldValue);
                            else if (fieldName.toLowerCase().equals("note".toLowerCase()))
                                ar.setNote(fieldValue);

                            if(possibleComma.isEmpty()) hasNextField = false;
                        }
                        i++;
                   }
/*
                    ar.setAuthor(btx.get(i).split("\"")[1].trim());
                    i++;

                    ar.setTitle(btx.get(i).split("\"")[1].trim());
                    i++;
                    ar.setJournal(btx.get(i).split("\"")[1].trim());
                    i++;
                    ar.setYear(btx.get(i).split("\"")[1].trim());
                    i++;
                    ar.setVolume(btx.get(i).split("\"")[1].trim());
                    */
                    returnlist.add(ar);
                }
                else if (potentialStr[0].toLowerCase().contains("inproceedings".toLowerCase())) // is article type bibtex
                {
                    InproceedingsReference ir = new InproceedingsReference();
                    ir.setHeading(potentialStr[1].split(",")[0].trim());
                    i++;
                    Boolean hasNextField = true;
                    while(hasNextField) {
                        Matcher m = fieldPattern.matcher(btx.get(i).trim());

                        // if our pattern matches the string, we can try to extract our groups
                        if (m.find()) {
                            // get the two groups we were looking for
                            String fieldName = m.group(1);
                            String fieldValue = m.group(5);
                            String possibleComma = m.group(8);

                            if (fieldName.toLowerCase().equals("author".toLowerCase()))
                                ir.setAuthor(fieldValue);
                            else if (fieldName.toLowerCase().equals("title".toLowerCase()))
                                ir.setTitle(fieldValue);
                            else if (fieldName.toLowerCase().equals("booktitle".toLowerCase()))
                                ir.setBookTitle(fieldValue);
                            else if (fieldName.toLowerCase().equals("year".toLowerCase()))
                                ir.setYear(fieldValue);
                            else if (fieldName.toLowerCase().equals("volume".toLowerCase()))
                                ir.setVolume(fieldValue);
                            else if (fieldName.toLowerCase().equals("series".toLowerCase()))
                                ir.setSeries(fieldValue);
                            else if (fieldName.toLowerCase().equals("address".toLowerCase()))
                                ir.setAddress(fieldValue);
                            else if (fieldName.toLowerCase().equals("editor".toLowerCase()))
                                ir.setEditor(fieldValue);
                            else if (fieldName.toLowerCase().equals("organization".toLowerCase()))
                                ir.setOrganization(fieldValue);
                            else if (fieldName.toLowerCase().equals("publisher".toLowerCase()))
                                ir.setPublisher(fieldValue);
                            else if (fieldName.toLowerCase().equals("month".toLowerCase()))
                                ir.setMonth(fieldValue);
                            else if (fieldName.toLowerCase().equals("key".toLowerCase()))
                                ir.setKey(fieldValue);
                            else if (fieldName.toLowerCase().equals("note".toLowerCase()))
                                ir.setNote(fieldValue);

                            if(possibleComma.isEmpty()) hasNextField = false;
                        }
                        i++;
                    }
/*
                    ar.setAuthor(btx.get(i).split("\"")[1].trim());
                    i++;

                    ar.setTitle(btx.get(i).split("\"")[1].trim());
                    i++;
                    ar.setJournal(btx.get(i).split("\"")[1].trim());
                    i++;
                    ar.setYear(btx.get(i).split("\"")[1].trim());
                    i++;
                    ar.setVolume(btx.get(i).split("\"")[1].trim());
                    */
                    returnlist.add(ir);
                }
            }
        }
        return returnlist;
    }

    public boolean appendFile(String path, String reference)
    {
        try
        {
            FileWriter filewriter = new FileWriter(path, true);
            filewriter.write(reference);
            filewriter.close();
            return true;
        } catch (IOException ex)
        {
            return false;
        }
    }
}
