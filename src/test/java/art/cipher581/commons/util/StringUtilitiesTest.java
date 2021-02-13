package art.cipher581.commons.util;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import static org.junit.Assert.*;


public class StringUtilitiesTest {

    @Test
    public void test_formatByteValue_001() {
        long bytes = 0;

        String str = StringUtilities.formatByteValue(bytes);

        assertEquals("0 b", str);
    }


    @Test
    public void test_formatByteValue_002() {
        long bytes = 1024;

        String str = StringUtilities.formatByteValue(bytes);

        assertEquals("1 KB", str);
    }


    @Test
    public void test_formatByteValue_003() {
        long bytes = (long) Math.pow(1024, 5);

        String str = StringUtilities.formatByteValue(bytes);

        assertEquals("1 PB", str);
    }


    @Test
    public void test_formatByteValue_004() {
        long bytes = (long) Math.pow(1024, 4);

        String str = StringUtilities.formatByteValue(bytes);

        assertEquals("1 TB", str);
    }


    @Test
    public void test_formatByteValue_005() {
        long bytes = (long) Math.pow(1024, 3);

        String str = StringUtilities.formatByteValue(bytes);

        assertEquals("1 GB", str);
    }


    @Test
    public void test_formatByteValue_006() {
        long bytes = (long) Math.pow(1024, 2);

        String str = StringUtilities.formatByteValue(bytes);

        assertEquals("1 MB", str);
    }


    @Test
    public void test_fill_001() {
        String str = StringUtilities.fill("abc", 10, ' ', true);

        assertEquals("abc       ", str);
    }


    @Test
    public void test_fill_002() {
        String str = StringUtilities.fill("abc", 10, ' ', false);

        assertEquals("       abc", str);
    }


    @Test
    public void test_fill_003() {
        String str = StringUtilities.fill("abc", 2, ' ', false);

        assertEquals("abc", str);
    }


    @Test
    public void test_toTextTable_001() {
        List<Map<String, Object>> data = new LinkedList<>();

        Map<String, Object> row = new HashMap<>();
        row.put("COL_1", "abc");
        row.put("COL_2", "abcdefghijklmnopqrstuvwxyz");
        row.put("COL_3", "1234567890");

        data.add(row);

        row = new HashMap<>();
        row.put("COL_1", "a\nbc");
        row.put("COL_2", "abcd\nefghijklm\nnopqrstuvwxyz");
        row.put("COL_3", "12345\n67890");

        data.add(row);

        List<String> columns = Arrays.asList(new String[]{"COL_1", "COL_2"});

        String str = StringUtilities.toTextTable(data, columns, 3, 10);

        StringBuilder expected = new StringBuilder();
        expected.append("COL_1   COL_2     \n");
        expected.append("------------------\n");
        expected.append("abc     abcd...   \n");
        expected.append("abc     abcd...   \n");

        assertEquals(expected.toString(), str);
    }

}
