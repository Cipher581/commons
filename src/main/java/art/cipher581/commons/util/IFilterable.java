package art.cipher581.commons.util;


public interface IFilterable<E> {
	
	public void applyFilter(IFilter<E> filter);

}
