package art.cipher581.commons.util.img;


import java.awt.Image;
import java.awt.image.BufferedImage;


public class JavaImageScaler implements IImageScaler {

	private int scaleMode = Image.SCALE_DEFAULT;


	public JavaImageScaler() {
		super();
	}


	public JavaImageScaler(int scaleMode) {
		super();

		this.scaleMode = scaleMode;
	}


	public int getScaleMode() {
		return scaleMode;
	}


	public void setScaleMode(int scaleMode) {
		this.scaleMode = scaleMode;
	}


	@Override
	public BufferedImage scale(BufferedImage img, int width, int height) {
		if (img == null) {
			throw new IllegalArgumentException("img is null");
		}

		Image scaled = img.getScaledInstance(width, height, scaleMode);

		return ImageUtils.createImage(scaled, width, height);
	}

}
