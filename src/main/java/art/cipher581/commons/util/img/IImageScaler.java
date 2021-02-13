package art.cipher581.commons.util.img;


import java.awt.image.BufferedImage;


public interface IImageScaler {

	public BufferedImage scale(BufferedImage img, int width, int height);
}
