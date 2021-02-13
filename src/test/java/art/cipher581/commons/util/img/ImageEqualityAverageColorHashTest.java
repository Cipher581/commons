package art.cipher581.commons.util.img;


public class ImageEqualityAverageColorHashTest extends AbstractImageEqualityTest {

	@Override
	protected IImageEquality create() {
		return ImageEqualityImageHash.averageColorHash();
	}

}
