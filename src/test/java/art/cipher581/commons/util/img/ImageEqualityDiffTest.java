package art.cipher581.commons.util.img;


import java.util.HashSet;
import java.util.Set;


public class ImageEqualityDiffTest extends AbstractImageEqualityTest {

	@Override
	protected IImageEquality create() {
		return new ImageEqualityDiff();
	}


	@Override
	public Set<String> getExcludeKeys() {
		Set<String> excludes = new HashSet<>();

		excludes.addAll(keysOf("image1.jpg", "image2_blurred.jpg"));
		excludes.addAll(keysOf("image1.jpg", "image2_scaled90.jpg"));
		excludes.addAll(keysOf("image1.jpg", "image2_grey.jpg"));

		excludes.addAll(keysOf("image2.jpg", "image2_blurred.jpg"));
		excludes.addAll(keysOf("image2.jpg", "image2_scaled90.jpg"));
		excludes.addAll(keysOf("image2.jpg", "image2_grey.jpg"));

		excludes.addAll(keysOf("image2_scaled90.jpg", "image2_blurred.jpg"));
		excludes.addAll(keysOf("image2_scaled90.jpg", "image2_grey.jpg"));
		excludes.addAll(keysOf("image2_scaled90.jpg", "image2_darker.jpg"));
		excludes.addAll(keysOf("image2_scaled90.jpg", "image2_brighter.jpg"));
		excludes.addAll(keysOf("image2_scaled90.jpg", "image2_lowerContrast.jpg"));
		excludes.addAll(keysOf("image2_scaled90.jpg", "image2_higherContrast.jpg"));

		excludes.addAll(keysOf("image2_blurred.jpg", "image2_grey.jpg"));
		excludes.addAll(keysOf("image2_blurred.jpg", "image2_darker.jpg"));
		excludes.addAll(keysOf("image2_blurred.jpg", "image2_brighter.jpg"));
		excludes.addAll(keysOf("image2_blurred.jpg", "image2_lowerContrast.jpg"));
		excludes.addAll(keysOf("image2_blurred.jpg", "image2_higherContrast.jpg"));

		excludes.addAll(keysOf("image2_grey.jpg", "image2_darker.jpg"));
		excludes.addAll(keysOf("image2_grey.jpg", "image2_brighter.jpg"));
		excludes.addAll(keysOf("image2_grey.jpg", "image2_lowerContrast.jpg"));
		excludes.addAll(keysOf("image2_grey.jpg", "image2_higherContrast.jpg"));

		return excludes;
	}

}
