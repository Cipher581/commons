package art.cipher581.commons.gui.util;


import java.awt.Color;


/**
 * See:<br>
 * <br>
 * https://en.wikipedia.org/wiki/Color_difference#Euclidean
 */
public class ColorDistanceProviderEuclideanApproximatedV2 implements IColorDistanceProvider {


	public ColorDistanceProviderEuclideanApproximatedV2() {
		super();
	}


	@Override
	public double getDistance(Color a, Color b) {
		double dR = a.getRed() - b.getRed();
		double dG = a.getGreen() - b.getGreen();
		double dB = a.getBlue() - b.getBlue();

		double mR = ((double) (a.getRed() + b.getRed())) / 2d;
		
		double fR = 2d + mR / 256d;
		double fG = 4d;
		double fB = 2d + (255 - mR) / 256d;
		
		double v = fR * Math.pow(dR, 2d) + fG * Math.pow(dG, 2d) + fB * Math.pow(dB, 2d);
		double d = Math.pow(v, 0.5);

		return d;
	}


	@Override
	public String toString() {
		return "Improved Approximated Eucledian Color Distance Provider";
	}
}
