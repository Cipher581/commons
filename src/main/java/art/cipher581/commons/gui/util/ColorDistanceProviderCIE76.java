package art.cipher581.commons.gui.util;


import java.awt.Color;


public class ColorDistanceProviderCIE76 implements IColorDistanceProvider {

	/**
	 * Computes the difference between two RGB colors by converting them to the L*a*b scale and
	 * comparing them using the CIE76 algorithm { http://en.wikipedia.org/wiki/Color_difference#CIE76}
	 */
	@Override
	public double getDistance(Color a, Color b) {
		int r1, g1, b1, r2, g2, b2;

	    r1 = a.getRed();
	    g1 = a.getGreen();
	    b1 = a.getBlue();
	    r2 = b.getRed();
	    g2 = b.getGreen();
	    b2 = b.getBlue();

	    double[] lab1 = ColorUtilities.rgb2lab(r1, g1, b1);
	    double[] lab2 = ColorUtilities.rgb2lab(r2, g2, b2);

	    return Math.sqrt(Math.pow(lab2[0] - lab1[0], 2) + Math.pow(lab2[1] - lab1[1], 2) + Math.pow(lab2[2] - lab1[2], 2));
	}

}
