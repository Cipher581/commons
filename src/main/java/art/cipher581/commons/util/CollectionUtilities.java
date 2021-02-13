package art.cipher581.commons.util;


import java.util.Collection;
import java.util.Iterator;


public class CollectionUtilities {

    public static String toString(Collection<?> list, String separator) {
        if (list == null) {
            return null;
        } else {
            StringBuilder builder = new StringBuilder();

            for (Iterator<?> it = list.iterator(); it.hasNext();) {
                Object obj = it.next();

                String current = obj == null ? "" : obj.toString(); //$NON-NLS-1$
                builder.append(current);

                if (it.hasNext()) {
                    builder.append(separator);
                }
            }

            return builder.toString();
        }
    }

}
