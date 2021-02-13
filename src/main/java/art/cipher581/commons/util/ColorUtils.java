package art.cipher581.commons.util;


import java.awt.Color;


public class ColorUtils {
    
    public static boolean isBlack(Color c, int threshold) {
        return (c.getRed() < threshold && c.getGreen() < threshold && c.getBlue() < threshold);
    }

}
