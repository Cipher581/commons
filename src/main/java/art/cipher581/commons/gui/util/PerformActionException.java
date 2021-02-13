package art.cipher581.commons.gui.util;


public class PerformActionException extends Exception {

	/**
	 * SVUID
	 */
	private static final long serialVersionUID = -1928226475286481805L;

	
	public PerformActionException(Throwable cause) {
		super(cause);
	}
	
	
	public PerformActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PerformActionException(String message) {
		super(message);
	}
	
}
