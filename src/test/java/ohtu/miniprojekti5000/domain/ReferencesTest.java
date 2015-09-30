package ohtu.miniprojekti5000.domain;

import ohtu.miniprojekti5000.domain.References;
import ohtu.miniprojekti5000.domain.ReferenceInterface;
import ohtu.miniprojekti5000.domain.BookReference;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferencesTest {
    private References references;

    @Before
    public void setUp() {
        references = new References();
    }

    @Test
    public void constructorCreatesAnEmptyList() {
        assertEquals(0, references.getAll().size());
    }

    @Test
    public void addingReferenceIncreasesReferencesCountByOne() {
        ReferenceInterface reference = new BookReference();
        references.add(reference);

        assertEquals(1, references.getAll().size());
    }
}
