package art.cipher581.commons.gui.util;


import java.awt.Color;


public class ColorDistanceProviderPrimitive implements IColorDistanceProvider {

	@Override
	public double getDistance(Color a, Color b) {
		int dR = Math.abs(a.getRed() - b.getRed());
		int dG = Math.abs(a.getGreen() - b.getGreen());
		int dB = Math.abs(a.getBlue() - b.getBlue());

		return ((double) (dR + dG + dB)) / 3d;
	}
	
	@Override
	public String toString() {
		return "Primitive Color Distance Provider";
	}
	
}
