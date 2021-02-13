package art.cipher581.commons.util.img;


public class ImageEqualityPerceptiveHashTest extends AbstractImageEqualityTest {

	@Override
	protected IImageEquality create() {
		return ImageEqualityImageHash.perceptiveHash();
	}

}
