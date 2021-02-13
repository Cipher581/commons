package art.cipher581.commons.util.img;


import java.awt.Color;


@FunctionalInterface
public interface IColorMatcher {

	public boolean matches(Color c);


	public static boolean matchesBlack(Color c) {
		return c.getRed() < 10 && c.getGreen() < 10 && c.getBlue() < 10;
	}

}
