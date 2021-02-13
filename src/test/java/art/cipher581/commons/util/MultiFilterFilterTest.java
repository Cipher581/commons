package art.cipher581.commons.util;


import org.junit.Test;

import art.cipher581.commons.util.MultiFilterFilter.LogicalOperator;

import static org.junit.Assert.*;


public class MultiFilterFilterTest {

    private IFilter<String> createAcceptFilter() {
        return new IFilter<String>() {

            @Override
            public boolean accept(String obj) {
                return true;
            }


            @Override
            public void evaluate(String obj) {
                // do nothing
            }

        };
    }


    private IFilter<String> createNotAcceptFilter() {
        return new IFilter<String>() {

            @Override
            public boolean accept(String obj) {
                return false;
            }


            @Override
            public void evaluate(String obj) {
                // do nothing
            }

        };
    }


    @Test
    public void test_001() {
        MultiFilterFilter<String> mf = new MultiFilterFilter<String>(LogicalOperator.OR);
        mf.addFilter(createAcceptFilter());
        mf.addFilter(createNotAcceptFilter());

        assertTrue(mf.accept(""));
    }


    @Test
    public void test_002() {
        MultiFilterFilter<String> mf = new MultiFilterFilter<String>(LogicalOperator.AND);
        mf.addFilter(createAcceptFilter());
        mf.addFilter(createNotAcceptFilter());

        assertFalse(mf.accept(""));
    }


    @Test
    public void test_003() {
        MultiFilterFilter<String> mf = new MultiFilterFilter<String>(LogicalOperator.OR);
        mf.addFilter(createAcceptFilter());

        assertTrue(mf.accept(""));
    }


    @Test
    public void test_004() {
        MultiFilterFilter<String> mf = new MultiFilterFilter<String>(LogicalOperator.AND);
        mf.addFilter(createAcceptFilter());

        assertTrue(mf.accept(""));
    }

}
