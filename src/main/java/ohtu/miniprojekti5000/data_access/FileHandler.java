package ohtu.miniprojekti5000.data_access;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import ohtu.miniprojekti5000.domain.ArticleReference;
import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;

/**
 *
 * @author miniprojekti-5000
 */
public class FileHandler
{
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
            if (btx.get(i).contains("@")) // header line
            {
                if (btx.get(i).contains("Book")) // is book type bibtex
                {
                    BookReference br = new BookReference();
                    String[] temp = btx.get(i).split("\\{");
                    br.setHeading(temp[1].split(",")[0]);
                    i++;
                    temp = btx.get(i).split("\"");
                    br.setAuthor(temp[1]);
                    i++;
                    temp = btx.get(i).split("\"");
                    br.setTitle(temp[1]);
                    i++;
                    temp = btx.get(i).split("\"");
                    br.setYear(temp[1]);
                    i++;
                    temp = btx.get(i).split("\"");
                    br.setPublisher(temp[1]);
                    
                    returnlist.add(br);
                }
                else if (btx.get(i).contains("Article"))
                {
                    ArticleReference ar = new ArticleReference();
                    String[] temp = btx.get(i).split("\\{");
                    ar.setHeading(temp[1].split(",")[0]);
                    i++;
                    ar.setAuthor(btx.get(i).split("\"")[1]);
                    i++;
                    ar.setTitle(btx.get(i).split("\"")[1]);
                    i++;
                    ar.setJournal(btx.get(i).split("\"")[1]);
                    i++;
                    ar.setYear(btx.get(i).split("\"")[1]);
                    i++;
                    ar.setVolume(btx.get(i).split("\"")[1]);
                    
                    returnlist.add(ar);
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
