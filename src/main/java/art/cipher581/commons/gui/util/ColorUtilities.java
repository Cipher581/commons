package art.cipher581.commons.gui.util;


import java.awt.Color;


public class ColorUtilities {

    public static Color brighter(Color color, float percent) {
        return darker(color, percent * -1);
    }


    /**
     * makes the color darker by x percent
     *
     * @param color that should be darker
     * @param percent -1 ... 0 ... 1 as float
     *
     * @return
     */
    public static Color darker(Color color, float percent) {
        float r = color.getRed();
        float g = color.getGreen();
        float b = color.getBlue();

        int newR = (int) (r - (r * percent));
        int newG = (int) (g - (g * percent));
        int newB = (int) (b - (b * percent));

        if (newR > 255) {
            newR = 255;
        } else if (newR < 0) {
            newR = 0;
        }

        if (newG > 255) {
            newG = 255;
        } else if (newG < 0) {
            newG = 0;
        }

        if (newB > 255) {
            newB = 255;
        } else if (newB < 0) {
            newB = 0;
        }

        Color newColor = new Color(newR, newG, newB);

        return newColor;
    }


    /**
     * http://www.easyrgb.com/en/math.php
     *
     * @param rgb
     * @param xyz
     */
    public static void convertRGB2XYZ(double[] rgb, double[] xyz) {
        if (rgb.length != 3) {
            throw new IllegalArgumentException("Illegal length of rgb array");
        }
        if (xyz.length != 3) {
            throw new IllegalArgumentException("Illegal length of xyz array");
        }

        double r = modifyXYZ(rgb[0]);
        double g = modifyXYZ(rgb[1]);
        double b = modifyXYZ(rgb[2]);

        double x = r * 0.4124 + g * 0.3576 + b * 0.1805;
        double y = r * 0.2126 + g * 0.7152 + b * 0.0722;
        double z = r * 0.0193 + g * 0.1192 + b * 0.9505;

        xyz[0] = x;
        xyz[1] = y;
        xyz[2] = z;
    }


    /**
     * @param i RGB Value (0-255)
     *
     * @return
     */
    private static double modifyXYZ(double i) {
        double r = i / 255;

        if (r > 0.04045) {
            r = Math.pow(((r + 0.055) / 1.055), 2.4);
        } else {
            r = r / 12.92;
        }

        r = r * 100;

        return r;
    }


    public static Color getColor(String colorStr) {
        colorStr = colorStr.trim();
        String[] arr = colorStr.split(",");

        if (arr.length == 1) {
            int colorCode;
            if (colorStr.startsWith("x")) {
                colorCode = Integer.parseInt(colorStr.replaceFirst("x", ""), 16);
            } else {
                colorCode = Integer.parseInt(colorStr);
            }

            return new Color(colorCode);
        } else if (arr.length >= 3) {
            int r = Integer.parseInt(arr[0].trim());
            int g = Integer.parseInt(arr[1].trim());
            int b = Integer.parseInt(arr[2].trim());
            int a = 255;

            if (arr.length >= 4) {
                a = Integer.parseInt(arr[3].trim());
            }

            return new Color(r, g, b, a);
        } else {
            return null;
        }
    }

}
