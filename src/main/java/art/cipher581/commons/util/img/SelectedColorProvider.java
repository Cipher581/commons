package art.cipher581.commons.util.img;

import java.awt.Color;


public class SelectedColorProvider {

	public Color getColor(Color c) {
		int blue = c.getBlue(); 

		blue += 100;

		if (blue > 255) {
			blue = 255;
		}
		
		int red = (int) (c.getRed() * 0.75);
		int green = (int) (c.getGreen() * 0.75);

		Color newC = new Color(red, green, blue);
		
		return newC;
	}
}
