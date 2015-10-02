package ohtu.miniprojekti5000.logic;

import java.util.HashMap;
import java.util.Iterator;

public class SpecialCharConverter {
    private HashMap<String, String> replacer;

    public SpecialCharConverter() {
        replacer = new HashMap<String, String>();
    }

    public void addReplace(String replaceFrom, String replaceTo) {
        replacer.put(replaceFrom, replaceTo);
    }

    public String convertSpecialChars(String str) {
        Iterator<String> keySetIterator = replacer.keySet().iterator();

        while(keySetIterator.hasNext()) {
            String replaceFrom = keySetIterator.next();
            String replaceTo = replacer.get(replaceFrom);

            str = str.replace(replaceFrom.toUpperCase(), replaceTo.toUpperCase())
                     .replace(replaceFrom.toLowerCase(), replaceTo.toLowerCase());
        }

        return str;
    }
}
