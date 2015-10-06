package ohtu.miniprojekti5000.data_access;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import ohtu.miniprojekti5000.domain.ReferenceInterface;

/**
 *
 * @author miniprojekti-5000
 */
public class FileHandler
{
    private String bibtex = "";
    
    public boolean loadFile(String path)
    {
        try
        {
            Scanner filescanner = new Scanner(new FileReader(path));
            while (filescanner.hasNext())
            {
                bibtex += filescanner.nextLine() + "\n";
            }
            return true;
        } catch (FileNotFoundException ex)
        {
            return false;
        }
    }
    
    public boolean appendFile(String path, String reference)
    {
        try
        {
            FileWriter filewriter = new FileWriter(path, true);
            filewriter.write(reference);
            filewriter.close();
            bibtex += reference;
            return true;
        } catch (IOException ex)
        {
            return false;
        }
    }
    public String getContent()
    {
        if (bibtex.isEmpty()) return null;
        else return bibtex;
    }
}
