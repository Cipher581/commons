package art.cipher581.commons.util.img;

import java.awt.Color;
import java.awt.image.BufferedImage;

import art.cipher581.commons.gui.util.ColorDistanceProviderCIEDE2000;
import art.cipher581.commons.gui.util.IColorDistanceProvider;

public class SelectedColorImageModifier implements IImageModifier {

	private final Color color;

	private final double colorDistance;

	private IColorDistanceProvider distanceProvider = new ColorDistanceProviderCIEDE2000();

	private Color targetColor = Color.BLACK;

	private Color targetColorSpace = Color.WHITE;

	public SelectedColorImageModifier(Color color, double colorDistance) {
		super();

		this.color = color;
		this.colorDistance = colorDistance;
	}

	@Override
	public BufferedImage modify(BufferedImage imgPaint) {
		BufferedImage image = ImageUtils.createImage(imgPaint.getWidth(), imgPaint.getHeight(), targetColorSpace);

		for (int x = 0; x < imgPaint.getWidth(); x++) {
			for (int y = 0; y < imgPaint.getHeight(); y++) {
				Color c = ImageUtils.getColor(imgPaint, x, y);

				double distance = distanceProvider.getDistance(c, color);
				distance = Math.abs(distance);

				if (distance < colorDistance) {
					image.setRGB(x, y, targetColor.getRGB());
				} else {
					image.setRGB(x, y, targetColorSpace.getRGB());
				}
			}
		}

		return image;
	}

	public void setTargetColor(Color targetColor) {
		this.targetColor = targetColor;
	}

	public void setTargetColorSpace(Color targetColorSpace) {
		this.targetColorSpace = targetColorSpace;
	}

}
