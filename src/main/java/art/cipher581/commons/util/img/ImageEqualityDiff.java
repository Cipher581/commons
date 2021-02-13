package art.cipher581.commons.util.img;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageEqualityDiff implements IImageEquality {

	private int threshold = 64;

	public ImageEqualityDiff() {
		super();
	}
	
	public ImageEqualityDiff(int threshold) {
		super();

		this.threshold = threshold;
	}

	@Override
	public boolean equals(BufferedImage a, BufferedImage b) {
		BufferedImage diff = ImageUtils.diff(a, b);
		
		for (int x = 0; x < diff.getWidth(); x++) {
			for (int y = 0; y < diff.getHeight(); y++) {
				Color c = ImageUtils.getColor(diff, x, y);
				
				if (c.getRed() >= threshold) {
					return false;
				}
				if (c.getGreen() >= threshold) {
					return false;
				}
				if (c.getBlue() >= threshold) {
					return false;
				}
			}	
		}

		return true;
	}
	
}
