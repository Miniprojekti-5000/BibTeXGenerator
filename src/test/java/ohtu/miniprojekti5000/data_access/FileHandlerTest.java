package ohtu.miniprojekti5000.data_access;

import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;
import ohtu.miniprojekti5000.logic.SpecialCharConverter;
import org.junit.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

public class FileHandlerTest {
    private URL url;
    private static String filename;
    private static File file;
    private static FileHandler fileHandler;

    @BeforeClass
    public static void setUp() {
        filename = "doesnt_exist.bib";
        file = new File(filename);
        fileHandler = new FileHandler();
    }

    @Before
    public void deleteFileIfExists() {
        if(file.exists())
            file.delete();
    }

    @AfterClass
    public static void cleanUp() {
        if(file.exists())
            file.delete();
    }

    @Test
    public void loadingFileWithoutPathReturnsFalse() {
        assertFalse(fileHandler.loadFile(""));
    }

    @Test
    public void loadingFileThatDoesntExistsReturnsFalse() {
        assertFalse(fileHandler.loadFile(filename));
    }

    @Test
    public void writingToFileThatDoesntExistReturnsTrue() {
        ReferenceInterface book = new BookReference("heading", "author", "title", "publisher", "year");

        assertTrue(fileHandler.appendFile(filename, book.toString(new SpecialCharConverter())));
    }

    @Test
    public void writingToFileWithoutPathReturnsFalse() {
        ReferenceInterface book = new BookReference("heading", "author", "title", "publisher", "year");

        assertFalse(fileHandler.appendFile("", book.toString(new SpecialCharConverter())));
    }

    @Test
    public void loadingFileThatExistsReturnsTrue() {
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        assertTrue(fileHandler.loadFile(filename));
    }
}
