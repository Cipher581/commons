package art.cipher581.commons.util;


import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class StringUtilities {

    public static String formatByteValue(double bytes) {
        String[] units = new String[]{"b", "KB", "MB", "GB", "TB", "PB"};

        String unit = units[units.length - 1];

        for (int i = 0; i < units.length; i++) {
            double newBytes = bytes / 1024;

            if (newBytes < 1.0) {
                unit = units[i];
                break;
            } else {
                bytes = newBytes;
            }
        }

        return new DecimalFormat("0.## " + unit).format(bytes);
    }


    public static String timeToHumanReadable(long ms) {
        DecimalFormat dcf = new DecimalFormat("0.00"); //$NON-NLS-1$

        long[] units = new long[]{1l, 1000l, 60000l, 3600000l, 86400000l, 31536000000l};
        String[] unitNames = new String[]{"ms", "sec", "min", "h", "d", "y"};

        String human = ms + " " + unitNames[0];

        for (int i = units.length - 1; i >= 0; i--) {
            double div = ((double) ms) / ((double) units[i]);

            if (div >= 1.0d) {
                human = dcf.format(div) + " " + unitNames[i]; //$NON-NLS-1$

                break;
            }
        }

        return human;
    }


    public static String toTextTable(Collection<Map<String, Object>> data, Collection<String> columns, int padding, int maxColumnLength) {
        if (data == null || data.isEmpty() || columns == null || columns.isEmpty()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        Map<String, Integer> columnSizes = new HashMap<>();

        for (Map<String, Object> row : data) {
            for (String column : columns) {
                Object val = row.get(column);

                String str = getStringValue(val, padding, maxColumnLength);
                String columnStr = getStringValue(column, padding, maxColumnLength);

                int lengthCur = Math.max(str.length(), columnStr.length()) + padding;

                Integer length = columnSizes.get(column);

                if (length == null || lengthCur > length) {
                    columnSizes.put(column, lengthCur);
                }
            }
        }

        for (String column : columns) {
            String str = getStringValue(column, padding, maxColumnLength);

            int length = columnSizes.get(column);

            str = fill(str, length, ' ', true);

            stringBuilder.append(str);
        }

        int lineLength = stringBuilder.length();

        stringBuilder.append("\n");

        for (int i = 0; i < lineLength; i++) {
            stringBuilder.append('-');
        }
        stringBuilder.append("\n");

        for (Map<String, Object> row : data) {
            for (String column : columns) {
                Object val = row.get(column);

                String str = getStringValue(val, padding, maxColumnLength);

                int length = columnSizes.get(column);

                str = fill(str, length, ' ', true);

                stringBuilder.append(str);
            }

            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }


    private static String getStringValue(Object val, int padding, int maxColumnLength) {
        String str = val == null ? "" : val.toString();

        str = str.replaceAll("\n", "");

        if (str.length() + padding > maxColumnLength) {
            str = str.substring(0, maxColumnLength - padding - 3) + "...";
        }

        return str;
    }


    public static String fill(String str, int length, char c, boolean trailing) {
        StringBuilder stringBuilder = new StringBuilder();

        if (trailing) {
            stringBuilder.append(str);
        }

        for (int diff = length - str.length(); diff > 0; diff--) {
            stringBuilder.append(c);
        }

        if (!trailing) {
            stringBuilder.append(str);
        }

        return stringBuilder.toString();
    }
    
    public static int charCount(String s, char c) {
    	int count = 0;
    	
    	for (char a : s.toCharArray()) {
    		if (a == c) {
    			count++;
    		}
    	}
    	
    	return count;
    }

}
