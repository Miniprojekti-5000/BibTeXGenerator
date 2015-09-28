package ohtu.miniprojekti5000.logic;

import java.util.ArrayList;

public class References
{
    private final ArrayList<ReferenceInterface> references = new ArrayList<ReferenceInterface>();
    
    public void add(ReferenceInterface reference)
    {
        references.add(reference);
    }

    public ArrayList<ReferenceInterface> getAll()
    {
        return references;
    }
}