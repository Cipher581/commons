package art.cipher581.commons.util.img;

public class ImageEqualityAverageKernelHashTest extends AbstractImageEqualityTest {

	@Override
	protected IImageEquality create() {
		return ImageEqualityImageHash.averageKernelHash();
	}

}
