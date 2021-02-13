package art.cipher581.commons.util.img;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Test;


public class ImageUtilsTest {

	@Test
	public void test_removeBlackBars_001() throws Exception {
		BufferedImage image = ImageTestHelper.load("image_with_bars.png");

		BufferedImage r = ImageUtils.removeBlackBars(image);

		assertEquals(600, r.getWidth());
		assertEquals(400, r.getHeight());

		assertTrue(ImageUtils.areEqual(image.getSubimage(0, 25, 600, 400), r));
	}

}
