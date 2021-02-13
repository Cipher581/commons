package art.cipher581.commons.util.img;


public class ImageEqualityDifferenceHashTest extends AbstractImageEqualityTest {

	@Override
	protected IImageEquality create() {
		return ImageEqualityImageHash.differenceHash();
	}

}
