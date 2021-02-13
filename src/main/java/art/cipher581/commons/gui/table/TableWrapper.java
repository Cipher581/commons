package art.cipher581.commons.gui.table;


import art.cipher581.commons.util.AbstractWrapper;


public class TableWrapper<W> extends AbstractWrapper<W> implements Comparable<TableWrapper<W>> {

    private Object value;


    public TableWrapper(W wrappedValue, Object value) {
        super(wrappedValue);

        this.value = value;
    }


    @Override
    public String toString() {
        return value == null ? "" : value.toString();
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public int compareTo(TableWrapper<W> o) {
        if (value != null && o.value != null) {
            if (value instanceof Comparable) {
                return ((Comparable) value).compareTo(value);
            } else {
                return value.toString().compareTo(o.toString());
            }
        } else if (value == null && o.value == null) {
            return 0;
        } else {
            return value == null ? -1 : 1;
        }
    }

}
