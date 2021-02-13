package art.cipher581.commons.util;


import java.util.Comparator;


public class StringComparatorIgnoreCase implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }

}
