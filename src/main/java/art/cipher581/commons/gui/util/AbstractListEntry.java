package art.cipher581.commons.gui.util;


import art.cipher581.commons.util.AbstractWrapper;


public class AbstractListEntry<W> extends AbstractWrapper<W> implements Comparable<AbstractListEntry<?>>{

	private String name;
	
	
	public AbstractListEntry(W wrappedValue, String name) {
		super(wrappedValue);
		
		this.name = name;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return name;
	}


	@Override
	public int compareTo(AbstractListEntry<?> o) {
		return name.compareToIgnoreCase(o.name);
	}
	
}
