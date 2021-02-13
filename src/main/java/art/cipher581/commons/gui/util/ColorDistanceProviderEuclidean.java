package art.cipher581.commons.gui.util;


import java.awt.Color;


/**
 * See:<br>
 * <br>
 * https://en.wikipedia.org/wiki/Color_difference#Euclidean
 */
public class ColorDistanceProviderEuclidean implements IColorDistanceProvider {

	@Override
	public double getDistance(Color a, Color b) {
		double dR = a.getRed() - b.getRed();
		double dG = a.getGreen() - b.getGreen();
		double dB = a.getBlue() - b.getBlue();

		double v = Math.pow(dR, 2d) + Math.pow(dG, 2d) + Math.pow(dB, 2d);
		double d = Math.pow(v, 0.5);
		
		return d;
	}


	@Override
	public String toString() {
		return "Eucledian Color Distance Provider";
	}

}
