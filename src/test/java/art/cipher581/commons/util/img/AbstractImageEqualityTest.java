package art.cipher581.commons.util.img;


import static art.cipher581.commons.util.img.ImageTestHelper.load;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


public abstract class AbstractImageEqualityTest {

	private String[] resources = new String[] { "image1.jpg", "image2.jpg", "image2_blurred.jpg", "image2_scaled90.jpg", "image2_grey.jpg", "image2_darker.jpg", "image2_brighter.jpg", "image2_lowerContrast.jpg", "image2_higherContrast.jpg" };


	protected abstract IImageEquality create();


	public Set<String> getExcludeKeys() {
		Set<String> excludes = new HashSet<>();

		return excludes;
	}


	/**
	 * Tests every resource with every resource
	 * 
	 * @throws IOException
	 */
	@Test
	public final void test_001() throws IOException {
		Set<String> excludeKeys = getExcludeKeys();

		for (String a : resources) {
			for (String b : resources) {
				boolean expectation = !excludeKeys.contains(keyOf(a, b));

				test(a, b, expectation);
			}
		}
	}


	public final void test(String resourceA, String resourceB, boolean expectation) throws IOException {
		BufferedImage a = load(resourceA);
		BufferedImage b = load(resourceB);

		boolean equals = create().equals(a, b);

		if (expectation) {
			assertTrue("Images " + resourceA + " and " + resourceB + " aren't equal", equals);
		} else {
			assertFalse("Images " + resourceA + " and " + resourceB + " are equal", equals);
		}

	}


	protected final String keyOf(String resourceA, String resourceB) {
		return resourceA + " " + resourceB;
	}


	protected final Set<String> keysOf(String resourceA, String resourceB) {
		Set<String> set = new HashSet<>();

		set.add(keyOf(resourceA, resourceB));
		set.add(keyOf(resourceB, resourceA));

		return set;
	}

}
