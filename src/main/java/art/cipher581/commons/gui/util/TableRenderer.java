package art.cipher581.commons.gui.util;


import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class TableRenderer implements TableCellRenderer {

	private Map<Integer, IValueFormatter> formatters = new HashMap<Integer, IValueFormatter>();

	private IValueFormatter defaultFormatter = new IValueFormatter() {

		@Override
		public String format(Object value) {
			if (value == null) {
				return "";
			} else {
				return value.toString();
			}
		}
	};


	public TableRenderer() {
		super();
	}
	
	
	public void setFormatter(int column, IValueFormatter formatter) {
		formatters.put(column, formatter);
	}


	@Override
	public JLabel getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		IValueFormatter formatter = formatters.get(Integer.valueOf(column));

		if (formatter == null) {
			formatter = defaultFormatter;
		}

		Color colorBG;
		if (row % 2 == 0) {
			colorBG = Color.WHITE;
		} else {
			colorBG = new Color(210, 255, 255);
		}

		if (isSelected) {
			colorBG = ColorUtilities.darker(colorBG, 0.2f);
		}

		String text = formatter.format(value);

		JLabel label = new JLabel(text);
		
		Font font = new Font(label.getFont().getName(), Font.PLAIN, 11);
		
		label.setFont(font);
		label.setBackground(colorBG);
		label.setOpaque(true);

		return label;
	}

}
