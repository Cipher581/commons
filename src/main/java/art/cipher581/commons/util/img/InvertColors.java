package art.cipher581.commons.util.img;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class InvertColors implements IImageModifier {

	@Override
	public BufferedImage modify(BufferedImage imgPaint) {
		BufferedImage image = ImageUtils.createImage(imgPaint.getWidth(), imgPaint.getHeight(), Color.WHITE);

		for (int x = 0; x < imgPaint.getWidth(); x++) {
			for (int y = 0; y < imgPaint.getHeight(); y++) {
				Color c = ImageUtils.getColor(imgPaint, x, y);

				Color cInverted = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());

				image.setRGB(x, y, cInverted.getRGB());
			}
		}

		return image;
	}

}
