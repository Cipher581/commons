package art.cipher581.commons.util.img;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class ImageTestHelper {

	public static BufferedImage load(String resourceName) throws IOException {
		try (InputStream is = ImageTestHelper.class.getResourceAsStream(resourceName)) {
			return ImageUtils.load(is);
		}
	}

}
