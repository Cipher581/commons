package art.cipher581.commons.gui.table;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;


public class MultiLineCellRenderer implements TableCellRenderer {

    private ITableCellColorProvider backgroundColorProvider = new DefaultTableCellBackgroundColorProvider();

    private DecimalFormat numberFormatDecimal = new DecimalFormat("0.00##");

    private DecimalFormat numberFormat = new DecimalFormat("0");

    private SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private Font font = new JLabel().getFont();


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JTextArea component = new JTextArea();

        Color backGroundColor = backgroundColorProvider.getColor(value, isSelected, row, column);

        String text = getText(value);

        component.setFont(font);
        component.setText(text);
        component.setEditable(false);
        component.setWrapStyleWord(true);
        component.setLineWrap(true);
        component.setBackground(backGroundColor);

        return component;
    }


    public ITableCellColorProvider getBackgroundColorProvider() {
        return backgroundColorProvider;
    }


    public void setBackgroundColorProvider(ITableCellColorProvider backgroundColorProvider) {
        this.backgroundColorProvider = backgroundColorProvider;
    }


    private String getText(Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof String) {
            return (String) value;
        } else if (value instanceof Double || value instanceof Float) {
            return numberFormatDecimal.format(value);
        } else if (value instanceof Integer || value instanceof Long) {
            return numberFormat.format(value);
        } else if (value instanceof Timestamp) {
            synchronized (timeFormat) {
                return timeFormat.format(value);
            }
        } else if (value instanceof Date) {
            synchronized (dateFormat) {
                return dateFormat.format(value);
            }
        }

        return value.toString();
    }

}
