package art.cipher581.commons.util.img;


import java.awt.image.BufferedImage;

import com.github.kilianB.hash.Hash;
import com.github.kilianB.hashAlgorithms.AverageColorHash;
import com.github.kilianB.hashAlgorithms.AverageHash;
import com.github.kilianB.hashAlgorithms.AverageKernelHash;
import com.github.kilianB.hashAlgorithms.DifferenceHash;
import com.github.kilianB.hashAlgorithms.DifferenceHash.Precision;
import com.github.kilianB.hashAlgorithms.HashingAlgorithm;
import com.github.kilianB.hashAlgorithms.MedianHash;
import com.github.kilianB.hashAlgorithms.PerceptiveHash;


public class ImageEqualityImageHash implements IImageEquality {

	private double similarityThreshold = 0.2;

	private HashingAlgorithm hashingAlgorithm;


	public ImageEqualityImageHash(HashingAlgorithm hashingAlgorithm) {
		super();

		this.hashingAlgorithm = hashingAlgorithm;
	}


	@Override
	public boolean equals(BufferedImage a, BufferedImage b) {
		Hash ha = hashingAlgorithm.hash(a);
		Hash hb = hashingAlgorithm.hash(b);

		double similarityScore = ha.normalizedHammingDistance(hb);
		
		System.out.println("similarityScore: " + similarityScore);

		return similarityScore < similarityThreshold;
	}


	public ImageEqualityImageHash withSimilarityThreshold(double similarityThreshold) {
		this.similarityThreshold = similarityThreshold;

		return this;
	}


	public static ImageEqualityImageHash averageHash() {
		return averageHash(128);
	}


	public static ImageEqualityImageHash averageHash(int bitResolution) {
		return new ImageEqualityImageHash(new AverageHash(bitResolution));
	}


	public static ImageEqualityImageHash averageColorHash() {
		return averageColorHash(128);
	}


	public static ImageEqualityImageHash averageColorHash(int bitResolution) {
		return new ImageEqualityImageHash(new AverageColorHash(bitResolution));
	}


	public static ImageEqualityImageHash differenceHash() {
		return differenceHash(128, Precision.Simple);
	}


	public static ImageEqualityImageHash differenceHash(int bitResolution, Precision precision) {
		return new ImageEqualityImageHash(new DifferenceHash(bitResolution, precision));
	}


	public static ImageEqualityImageHash perceptiveHash() {
		return perceptiveHash(128);
	}


	public static ImageEqualityImageHash perceptiveHash(int bitResolution) {
		return new ImageEqualityImageHash(new PerceptiveHash(bitResolution));
	}


	public static ImageEqualityImageHash medianHash() {
		return medianHash(128);
	}


	public static ImageEqualityImageHash medianHash(int bitResolution) {
		return new ImageEqualityImageHash(new MedianHash(bitResolution));
	}


	public static ImageEqualityImageHash averageKernelHash() {
		return averageKernelHash(128);
	}


	public static ImageEqualityImageHash averageKernelHash(int bitResolution) {
		return new ImageEqualityImageHash(new AverageKernelHash(bitResolution));
	}

}
