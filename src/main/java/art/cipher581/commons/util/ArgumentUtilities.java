package art.cipher581.commons.util;


import java.util.Locale;


public class ArgumentUtilities {

	public static final int NEXT_ARGUMENT_IS_VALUE = 0;

	public static final int VALUE_SEPARATED_BY_EQUALSIGN = 1;

	private String[] args = new String[] {};


	public ArgumentUtilities(final String[] args) {
		if (args == null) {
			throw new IllegalArgumentException("null is not allowed for args");
		} else {
			this.args = args.clone();
		}
	}


	/**
	 * Prüft, ob ein Argument existiert.
	 * 
	 * @param argument
	 *            Argument, dass auf Existenz geprüft werden soll
	 * @param caseSensitive
	 *            Gibt an, ob Groß- und Kleinschreibung beachtet werden soll
	 * @return true wenn das Argument existiert oder false, wenn das Argument
	 *         nicht existiert
	 * @throws IllegalArgumentException
	 *             wenn argument null oder leer ist
	 */
	public boolean isArgumentSet(final String argument, final boolean caseSensitive) throws IllegalArgumentException {
		if (argument == null || argument.equals("")) {
			throw new IllegalArgumentException("null and \"\" are not allowed for argument");
		} else {
			boolean isArgumentSet = false;
			for (int i = 0; i != args.length; i++) {
				String currentArgument = args[i];
				if (matches(argument, currentArgument, caseSensitive)) {
					isArgumentSet = true;
					break;
				}
			}
			return isArgumentSet;
		}
	}


	/**
	 * ruft isArgumentSet(argument, false) auf
	 * 
	 * @param argument
	 *            Argument, dass auf Existenz geprüft werden soll
	 * @return true wenn das Argument existiert oder false, wenn das Argument
	 *         nicht existiert
	 * @throws IllegalArgumentException
	 *             wenn argument null oder leer ist
	 */
	public boolean isArgumentSet(final String argument) throws IllegalArgumentException {
		return isArgumentSet(argument, false);
	}


	private boolean matches(final String search, final String argument, final boolean caseSensitive) {
		boolean matches;
		if (caseSensitive) {
			matches = matches(search, argument);
		} else {
			matches = matches(search.toLowerCase(Locale.getDefault()), argument.toLowerCase(Locale.getDefault()));
		}
		return matches;
	}


	private boolean matches(final String search, final String argument) {
		String pattern;
		if (argument.contains("=")) {
			pattern = "^-{0,2}" + search + "=.*$";
		} else {
			pattern = "^-{0,2}" + search + "$";
		}

		boolean matches = false;
		if (argument.matches(pattern)) {
			matches = true;
		}
		return matches;
	}


	public String getValue(final String argument, final int type, final boolean caseSensitive) throws IllegalArgumentException {
		if (argument == null || argument.equals("")) {
			throw new IllegalArgumentException("null and \"\" are not allowed for argument");
		} else {
			String value = null;
			for (int i = 0; i != args.length; i++) {
				String currentArgument = args[i];

				if (matches(argument, currentArgument, caseSensitive)) {
					if (type == VALUE_SEPARATED_BY_EQUALSIGN && currentArgument.contains("=")) {
						int pos = currentArgument.indexOf('=');
						value = currentArgument.substring(pos + 1);
					} else if (type == NEXT_ARGUMENT_IS_VALUE && args.length > i + 1) {
						value = args[i + 1];
					}
				}
			}
			return value;
		}
	}


	public String getValue(final String argument, final int type) {
		return getValue(argument, type, false);
	}


	public int getValueAsInteger(final String option, final int type) throws NumberFormatException, NullPointerException {
		return getValueAsInteger(option, type, false);
	}


	public int getValueAsInteger(final String option, final int type, final int alternate) {
		int value;
		try {
			value = getValueAsInteger(option, type);
		} catch (Exception ex) {
			value = alternate;
		}
		return value;
	}


	public int getValueAsInteger(final String option, final int type, final boolean caseSensitive) throws NumberFormatException, NullPointerException {
		String strValue = getValue(option, type, caseSensitive);
		if (strValue == null) {
			throw new NullPointerException(); // NOPMD
		} else {
			return Integer.parseInt(strValue);
		}
	}


	public int getValueAsInteger(final String option, final int type, final boolean caseSensitive, final int alternate) {
		int value;
		try {
			value = getValueAsInteger(option, type, caseSensitive);
		} catch (Exception ex) {
			value = alternate;
		}
		return value;
	}


	public long getValueAsLong(final String option, final int type) throws NumberFormatException, NullPointerException {
		return getValueAsLong(option, type, false);
	}


	public long getValueAsLong(final String option, final int type, final long alternate) {
		long value;
		try {
			value = getValueAsLong(option, type);
		} catch (Exception ex) {
			value = alternate;
		}
		return value;
	}


	public long getValueAsLong(final String option, final int type, final boolean caseSensitive) throws NumberFormatException, NullPointerException {
		String strValue = getValue(option, type, caseSensitive);
		if (strValue == null) {
			throw new NullPointerException(); // NOPMD
		} else {
			return Long.parseLong(strValue);
		}
	}


	public long getValueAsLong(final String option, final int type, final boolean caseSensitive, final long alternate) {
		long value;
		try {
			value = getValueAsLong(option, type);
		} catch (Exception ex) {
			value = alternate;
		}
		return value;
	}


	public float getValueAsFloat(final String option, final int type) throws NumberFormatException, NullPointerException {
		return getValueAsFloat(option, type, false);
	}


	public float getValueAsFloat(final String option, final int type, final float alternate) {
		float value;
		try {
			value = getValueAsFloat(option, type);
		} catch (Exception ex) {
			value = alternate;
		}
		return value;
	}


	public float getValueAsFloat(final String option, final int type, final boolean caseSensitive) throws NumberFormatException, NullPointerException {
		return Float.parseFloat(getValue(option, type, caseSensitive));
	}


	public float getValueAsFloat(final String option, final int type, final boolean caseSensitive, final float alternate) {
		float value;
		try {
			value = getValueAsFloat(option, type, caseSensitive);
		} catch (Exception ex) {
			value = alternate;
		}
		return value;
	}


	public double getValueAsDouble(final String option, final int type) throws NumberFormatException, NullPointerException {
		return getValueAsDouble(option, type, false);
	}


	public double getValueAsDouble(final String option, final int type, final double alternate) {
		double value;
		try {
			value = getValueAsDouble(option, type);
		} catch (Exception ex) {
			value = alternate;
		}
		return value;
	}


	public double getValueAsDouble(final String option, final int type, final boolean caseSensitive) throws NumberFormatException, NullPointerException {
		return Double.parseDouble(getValue(option, type, caseSensitive));
	}


	public double getValueAsDouble(final String option, final int type, final boolean caseSensitive, final double alternate) {
		double value;
		try {
			value = getValueAsDouble(option, type, caseSensitive);
		} catch (Exception ex) {
			value = alternate;
		}
		return value;
	}

}
