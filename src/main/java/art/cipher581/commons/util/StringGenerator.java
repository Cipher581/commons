package art.cipher581.commons.util;


public class StringGenerator {

    public static final char[] NUMERIC = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static final char[] ALPHABETIC = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static final char[] ALPHANUMERIC = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public int minCharCount;

    public int maxCharCount;

    private char[] chars;


    public StringGenerator(int minCharCount, int maxCharCount, char[] chars) {
        super();

        this.minCharCount = minCharCount;
        this.maxCharCount = maxCharCount;
        this.chars = chars;
    }


    public String generate() {
    	int count;
    	if (minCharCount == maxCharCount) {
    		count = minCharCount;
    	} else {
    		count = RandomUtils.nextInt(minCharCount, maxCharCount);
    	}

        char[] strArr = new char[count];

        for (int i = 0; i < count; i++) {
            strArr[i] = chars[RandomUtils.nextInt(chars.length - 1)];
        }

        return new String(strArr);
    }

}
