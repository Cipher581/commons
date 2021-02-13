package art.cipher581.commons.gui.table;


import java.awt.Color;


public class DefaultTableCellBackgroundColorProvider implements ITableCellColorProvider {

    private Color backgroundColorBright = new Color(255, 255, 255);

    private Color backgroundColorDark = new Color(244, 244, 244);

    private Color backgroundColorSelectedBright = new Color(233, 233, 233);

    private Color backgroundColorSelectedDark = new Color(222, 222, 222);


    public DefaultTableCellBackgroundColorProvider() {
        super();
    }


    public DefaultTableCellBackgroundColorProvider(Color backgroundColorBright, Color backgroundColorDark, Color backgroundColorSelectedBright, Color backgroundColorSelectedDark) {
        super();

        this.backgroundColorBright = backgroundColorBright;
        this.backgroundColorDark = backgroundColorDark;
        this.backgroundColorSelectedBright = backgroundColorSelectedBright;
        this.backgroundColorSelectedDark = backgroundColorSelectedDark;
    }


    @Override
    public Color getColor(Object value, boolean isSelected, int row, int column) {
        return getColor(row, isSelected);
    }


    private Color getColor(int row, boolean isSelected) {
        Color color;

        if (row % 2 == 0) {
            color = isSelected ? backgroundColorSelectedBright : backgroundColorBright;
        } else {
            color = isSelected ? backgroundColorSelectedDark : backgroundColorDark;
        }

        return color;
    }

}
