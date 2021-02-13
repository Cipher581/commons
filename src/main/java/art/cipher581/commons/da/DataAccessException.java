package art.cipher581.commons.da;


public class DataAccessException extends Exception {

	/**
	 * SVUID
	 */
	private static final long serialVersionUID = -3953209857493060857L;


	public DataAccessException() {
		super();
	}


	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}


	public DataAccessException(String message) {
		super(message);
	}


	public DataAccessException(Throwable cause) {
		super(cause);
	}

}
