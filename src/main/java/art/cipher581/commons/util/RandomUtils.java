package art.cipher581.commons.util;


import org.apache.commons.math3.random.RandomDataGenerator;


public class RandomUtils {

    private static final RandomDataGenerator RANDOM = new RandomDataGenerator();


    public static long nextLong(long n) {
        return RANDOM.nextLong(0, n);
    }


    static int nextInt(int lower, int upper) {
        return RANDOM.nextInt(lower, upper);
    }


    static int nextInt(int upper) {
        return RANDOM.nextInt(0, upper);
    }
}
