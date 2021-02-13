package art.cipher581.commons.gui.table;


import java.awt.Color;


public interface ITableCellColorProvider {

    public Color getColor(Object value, boolean isSelected, int row, int column);

}
