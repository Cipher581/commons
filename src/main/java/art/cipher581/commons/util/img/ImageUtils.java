package art.cipher581.commons.util.img;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;


public class ImageUtils {

	private static final Pattern IMAGE_FILE_PATTERN = Pattern.compile("(?i)^.+\\.(jpg|png|jpeg|bmp)$"); //$NON-NLS-1$


	public static BufferedImage createImage(int width, int height, Color color) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int rgb = color.getRGB();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, rgb);
			}
		}

		return image;
	}


	public static BufferedImage createImage(Image image, int width, int height) {
		BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		buffered.getGraphics().drawImage(image, 0, 0, null);

		return buffered;
	}


	public static BufferedImage load(File file) throws IOException {
		BufferedImage image = ImageIO.read(file);

		return image;
	}


	public static BufferedImage load(InputStream is) throws IOException {
		BufferedImage image = ImageIO.read(is);

		return image;
	}


	public static boolean isImageFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("file is null"); //$NON-NLS-1$
		}

		String filename = file.getName();

		Matcher matcher = IMAGE_FILE_PATTERN.matcher(filename);

		return matcher.matches();
	}


	public static FileFilter createImageFileFilter() {
		return new FileFilter() {

			@Override
			public boolean accept(File file) {
				return isImageFile(file);
			}

		};
	}


	public static Color getTransparentColor(Color color, float alpha) {
		float red = ((float) color.getRed()) / 255f;
		float green = ((float) color.getGreen()) / 255f;
		float blue = ((float) color.getBlue()) / 255f;

		return new Color(red, green, blue, 0.5f);
	}


	public static List<File> getImageFiles(List<File> files) {
		List<File> imageFiles = new LinkedList<File>();

		for (File file : files) {
			if (ImageUtils.isImageFile(file)) {
				imageFiles.add(file);
			}
		}

		return imageFiles;
	}


	public static BufferedImage createImage(Component component) {
		BufferedImage img = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		component.paint(g);
		g.dispose();
		return img;
	}


	public static boolean areEqual(BufferedImage a, BufferedImage b) {
		if (a.getHeight() != b.getHeight()) {
			return false;
		}
		if (a.getWidth() != b.getWidth()) {
			return false;
		}

		for (int x = 0; x < a.getWidth(); x++) {
			for (int y = 0; y < a.getHeight(); y++) {
				int rgbA = a.getRGB(x, y);
				int rgbB = b.getRGB(x, y);

				if (rgbA != rgbB) {
					return false;
				}
			}
		}

		return true;
	}


	public static String getMD5Hash(BufferedImage image) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int rgb = image.getRGB(x, y);

				Color c = new Color(rgb);

				md.update((byte) c.getRed());
				md.update((byte) c.getGreen());
				md.update((byte) c.getBlue());
				md.update((byte) c.getAlpha());
			}
		}

		byte[] digested = md.digest();

		String str = new HexBinaryAdapter().marshal(digested);

		return str;
	}


	public static BufferedImage reduce(BufferedImage image, int stepSize) {
		int newWidth = image.getWidth() / stepSize;
		int newHeight = image.getHeight() / stepSize;

		BufferedImage r = createImage(newWidth, newHeight, Color.WHITE);

		for (int x = 0; x < newWidth; x++) {
			for (int y = 0; y < newHeight; y++) {
				int rgb = image.getRGB(x * stepSize, y * stepSize);

				r.setRGB(x, y, rgb);
			}
		}

		return r;
	}


	public static BufferedImage reduceColor(BufferedImage image, int stepSize) {
		BufferedImage reduced = createImage(image.getWidth(), image.getHeight(), Color.WHITE);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int rgb = image.getRGB(x, y);

				Color c = new Color(rgb);

				int r = (c.getRed() / stepSize) * stepSize;
				int g = (c.getGreen() / stepSize) * stepSize;
				int b = (c.getBlue() / stepSize) * stepSize;

				Color nc = new Color(r, g, b);

				reduced.setRGB(x, y, nc.getRGB());
			}
		}

		return reduced;
	}


	public static BufferedImage diff(BufferedImage a, BufferedImage b) {
		BufferedImage diff = createImage(a.getWidth(), a.getHeight(), Color.WHITE);

		for (int x = 0; x < a.getWidth(); x++) {
			for (int y = 0; y < a.getHeight(); y++) {
				Color cA = getColor(a, x, y, Color.WHITE);
				Color cB = getColor(b, x, y, Color.WHITE);

				int red = Math.abs(cA.getRed() - cB.getRed());
				int green = Math.abs(cA.getGreen() - cB.getGreen());
				int blue = Math.abs(cA.getBlue() - cB.getBlue());

				Color c = new Color(red, green, blue);

				diff.setRGB(x, y, c.getRGB());
			}
		}
		return diff;
	}


	public static Color getColor(BufferedImage image, int x, int y) {
		return getColor(image, x, y, null);
	}


	public static Color getColor(BufferedImage image, int x, int y, Color defaultValue) {
		if (image.getHeight() > y && image.getWidth() > x) {
			int rgb = image.getRGB(x, y);

			return new Color(rgb);
		} else {
			return defaultValue;
		}
	}


	public static BufferedImage removeBlackBars(BufferedImage image) {
		return removeBars(image, IColorMatcher::matchesBlack);
	}


	public static BufferedImage removeBars(BufferedImage image, IColorMatcher colorMatcher) {
		int horizontalLine = image.getHeight() / 2;
		int verticalLine = image.getWidth() / 2;

		int startX = 0;
		int startY = 0;
		int endX = image.getWidth() - 1;
		int endY = image.getHeight() - 1;

		for (int x = 0; x < verticalLine; x++) {
			Color color = new Color(image.getRGB(x, horizontalLine));

			if (!colorMatcher.matches(color)) {
				startX = x;
				break;
			}
		}
		for (int x = endX; x >= verticalLine; x--) {
			Color color = new Color(image.getRGB(x, horizontalLine));

			if (!colorMatcher.matches(color)) {
				endX = x;
				break;
			}
		}

		for (int y = 0; y < horizontalLine; y++) {
			Color color = new Color(image.getRGB(verticalLine, y));

			if (!colorMatcher.matches(color)) {
				startY = y;
				break;
			}
		}
		for (int y = endY; y >= horizontalLine; y--) {
			Color color = new Color(image.getRGB(verticalLine, y));

			if (!colorMatcher.matches(color)) {
				endY = y;
				break;
			}
		}

		image = image.getSubimage(startX, startY, (endX - startX) + 1, (endY - startY) + 1);

		return image;
	}

}
