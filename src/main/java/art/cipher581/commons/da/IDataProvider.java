package art.cipher581.commons.da;


public interface IDataProvider<E> {
	
	public E get() throws DataAccessException;

}
