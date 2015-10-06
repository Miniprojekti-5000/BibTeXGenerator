package ohtu.miniprojekti5000.ui;

import java.util.List;
import ohtu.miniprojekti5000.domain.ArticleReference;
import ohtu.miniprojekti5000.domain.BookReference;
import ohtu.miniprojekti5000.domain.ReferenceInterface;

public interface IO {
    void printReferences(List<ReferenceInterface> references);
    void printAvailableCommands(boolean hasReferences);
    int getCommand();
    BookReference readBook();
    ArticleReference readArticle();
    void printAdded(String type);
    void printHeadingAlreadyExists(String heading);
    
}
