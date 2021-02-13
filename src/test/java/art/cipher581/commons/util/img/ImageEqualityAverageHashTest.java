package art.cipher581.commons.util.img;


public class ImageEqualityAverageHashTest extends AbstractImageEqualityTest {

	@Override
	protected IImageEquality create() {
		return ImageEqualityImageHash.averageHash();
	}

}
