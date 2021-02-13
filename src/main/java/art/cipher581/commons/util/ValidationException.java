package art.cipher581.commons.util;


public class ValidationException extends Exception {

	/**
	 * SVUID
	 */
	private static final long serialVersionUID = -3977122578460446642L;

	
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}
	

	public ValidationException(String message) {
		super(message);
	}

}
