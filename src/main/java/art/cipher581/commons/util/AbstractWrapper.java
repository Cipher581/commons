package art.cipher581.commons.util;


public abstract class AbstractWrapper<W> implements IWrapper<W> {

    private W wrappedValue;


    public AbstractWrapper() {
        super();
    }


    public AbstractWrapper(W wrappedValue) {
        super();

        this.wrappedValue = wrappedValue;
    }


    @Override
    public W getWrappedValue() {
        return wrappedValue;
    }


    public void setWrappedValue(W wrappedValue) {
        this.wrappedValue = wrappedValue;
    }

}
