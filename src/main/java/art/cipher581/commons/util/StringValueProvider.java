package art.cipher581.commons.util;


public class StringValueProvider<O> implements IValueProvider<String, O> {

	private final IValueProvider<?, O> valueProvider;
	
	
	public StringValueProvider(IValueProvider<?, O> valueProvider) {
		super();

		this.valueProvider = valueProvider;
	}


	@Override
	public String getValue(O obj) {
		Object value = valueProvider.getValue(obj);
		
		return value == null ? null : value.toString();
	}

}
