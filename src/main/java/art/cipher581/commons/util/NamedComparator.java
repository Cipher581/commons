package art.cipher581.commons.util;


import java.util.Comparator;


public class NamedComparator<E extends INamed> implements Comparator<E> {

	private boolean ignoreCase = true;


	public NamedComparator() {
		super();
	}


	public NamedComparator(boolean ignoreCase) {
		super();

		this.ignoreCase = ignoreCase;
	}


	public boolean isIgnoreCase() {
		return ignoreCase;
	}


	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}


	@Override
	public int compare(E named1, E named2) {
		String name1 = named1.getName() == null ? "" : named1.getName();
		String name2 = named2.getName() == null ? "" : named2.getName();

		if (ignoreCase) {
			return name1.compareToIgnoreCase(name2);
		} else {
			return name1.compareTo(name2);
		}
	}

}
