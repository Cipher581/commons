package art.cipher581.commons.gui.util;


import java.awt.Color;


/**
 * See:<br>
 * <br>
 * https://en.wikipedia.org/wiki/Color_difference#Euclidean
 */
public class ColorDistanceProviderEuclideanApproximated implements IColorDistanceProvider {

	private double fR = 2d;

	private double fG = 4d;

	private double fB = 3d;


	public ColorDistanceProviderEuclideanApproximated() {
		super();
	}


	public ColorDistanceProviderEuclideanApproximated(double fR, double fG, double fB) {
		super();

		this.fR = fR;
		this.fG = fG;
		this.fB = fB;
	}


	@Override
	public double getDistance(Color a, Color b) {
		double dR = a.getRed() - b.getRed();
		double dG = a.getGreen() - b.getGreen();
		double dB = a.getBlue() - b.getBlue();

		double v = fR * Math.pow(dR, 2d) + fG * Math.pow(dG, 2d) + fB * Math.pow(dB, 2d);
		double d = Math.pow(v, 0.5);

		return d;
	}


	@Override
	public String toString() {
		return "Approximated Eucledian Color Distance Provider";
	}
}
