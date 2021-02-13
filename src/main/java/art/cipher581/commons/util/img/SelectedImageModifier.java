package art.cipher581.commons.util.img;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class SelectedImageModifier implements IImageModifier {

	@Override
	public BufferedImage modify(BufferedImage image) {
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		Graphics g = newImage.getGraphics();

		g.drawImage(image, 0, 0, null);

		Color color = new Color(0f, 0f, 1f, 0.2f);
		g.setColor(color);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());

		return newImage;
	}

}
