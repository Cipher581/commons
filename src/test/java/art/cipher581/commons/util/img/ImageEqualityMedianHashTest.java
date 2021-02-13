package art.cipher581.commons.util.img;


public class ImageEqualityMedianHashTest extends AbstractImageEqualityTest {

	@Override
	protected IImageEquality create() {
		return ImageEqualityImageHash.medianHash();
	}

}
