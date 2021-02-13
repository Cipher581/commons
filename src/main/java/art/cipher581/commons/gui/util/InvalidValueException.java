package art.cipher581.commons.gui.util;


public class InvalidValueException extends Exception {

	/**
	 * SVUID
	 */
	private static final long serialVersionUID = -555394751068219955L;

	
	public InvalidValueException(String message, Throwable cause) {
		super(message, cause);
	}

	
	public InvalidValueException(String message) {
		super(message);
	}

}
