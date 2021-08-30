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
    
    public static double[] rgb2lab(int R, int G, int B) {
	    //http://www.brucelindbloom.com

	    float r, g, b, X, Y, Z, fx, fy, fz, xr, yr, zr;
	    float Ls, as, bs;
	    float eps = 216.f / 24389.f;
	    float k = 24389.f / 27.f;

	    float Xr = 0.964221f;  // reference white D50
	    float Yr = 1.0f;
	    float Zr = 0.825211f;

	    // RGB to XYZ
	    r = R / 255.f; //R 0..1
	    g = G / 255.f; //G 0..1
	    b = B / 255.f; //B 0..1

	    // assuming sRGB (D65)
	    if (r <= 0.04045)
	        r = r / 12;
	    else
	        r = (float) Math.pow((r + 0.055) / 1.055, 2.4);

	    if (g <= 0.04045)
	        g = g / 12;
	    else
	        g = (float) Math.pow((g + 0.055) / 1.055, 2.4);

	    if (b <= 0.04045)
	        b = b / 12;
	    else
	        b = (float) Math.pow((b + 0.055) / 1.055, 2.4);


	    X = 0.436052025f * r + 0.385081593f * g + 0.143087414f * b;
	    Y = 0.222491598f * r + 0.71688606f * g + 0.060621486f * b;
	    Z = 0.013929122f * r + 0.097097002f * g + 0.71418547f * b;

	    // XYZ to Lab
	    xr = X / Xr;
	    yr = Y / Yr;
	    zr = Z / Zr;

	    if (xr > eps)
	        fx = (float) Math.pow(xr, 1 / 3.);
	    else
	        fx = (float) ((k * xr + 16.) / 116.);

	    if (yr > eps)
	        fy = (float) Math.pow(yr, 1 / 3.);
	    else
	        fy = (float) ((k * yr + 16.) / 116.);

	    if (zr > eps)
	        fz = (float) Math.pow(zr, 1 / 3.);
	    else
	        fz = (float) ((k * zr + 16.) / 116);

	    Ls = (116 * fy) - 16;
	    as = 500 * (fx - fy);
	    bs = 200 * (fy - fz);

	    double[] lab = new double[3];
	    lab[0] = (int) (2.55 * Ls + .5);
	    lab[1] = (int) (as + .5);
	    lab[2] = (int) (bs + .5);
	    return lab;
	}

}
