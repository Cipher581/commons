package art.cipher581.commons.util;


public class ExceptionUtilities {

	public static String toString(Exception ex) {
		StringBuilder stringBuilder = new StringBuilder();

		append(ex, stringBuilder);

		return stringBuilder.toString();
	}


	private static void append(Throwable ex, StringBuilder stringBuilder) {
		stringBuilder.append(ex.getClass());
		stringBuilder.append(": ");
		stringBuilder.append(ex.getMessage());
		stringBuilder.append("\n");
		
		StackTraceElement[] stackTraceElements = ex.getStackTrace();
		if (stackTraceElements != null) {
			for (StackTraceElement stackTraceElement : stackTraceElements) {
				stringBuilder.append(stackTraceElement.toString());
			}
		}

		Throwable cause = ex.getCause();

		if (cause != null) {
			stringBuilder.append("Caused by: ");
			append(cause, stringBuilder);
		}
	}

}
