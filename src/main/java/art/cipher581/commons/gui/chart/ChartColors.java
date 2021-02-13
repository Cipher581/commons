package art.cipher581.commons.gui.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class ChartColors {

	/**
	 * https://kuler.adobe.com/Watermelon-color-theme-3097745/
	 * 
	 * @return
	 */
	public static List<Color> getDefaultColorsWaterMelon() {
		List<Color> colors = new ArrayList<Color>(5);
		
		colors.add(new Color(125, 138, 46));
		colors.add(new Color(201, 215, 135));
		colors.add(new Color(213, 221, 255));
		colors.add(new Color(255, 192, 169));
		colors.add(new Color(255, 133, 152));
		
		return colors;
	}
}
