package art.cipher581.commons.util;


import java.util.regex.Pattern;


/**
 * basic patterns allows you to use wild card characters '*' and '?'
 */
public class BasicPattern {

	public static Pattern compileBasicPattern(String pattern) {
		return compileBasicPattern(pattern, true);
	}
	
	
	public static Pattern compileBasicPattern(String pattern, boolean ignoreCase) {
		if (pattern == null) {
			throw new IllegalArgumentException("pattern is null");
		}

		pattern = escape(pattern);

		pattern = pattern.replaceAll("\\*", ".*");
		pattern = pattern.replaceAll("\\?", ".?");

		if (ignoreCase) {
			pattern = "(?i)" + pattern;
		}

		Pattern compiled = Pattern.compile(pattern);

		return compiled;
	}


	public static String escape(String pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException("pattern is null");
		}

		pattern = pattern.replaceAll("\\\\", "\\\\\\\\");
		pattern = pattern.replaceAll("\\)", "\\\\)");
		pattern = pattern.replaceAll("\\(", "\\\\(");
		pattern = pattern.replaceAll("\\{", "\\\\{");
		pattern = pattern.replaceAll("\\}", "\\\\}");
		pattern = pattern.replaceAll("\\]", "\\\\]");
		pattern = pattern.replaceAll("\\[", "\\\\[");
		pattern = pattern.replaceAll("\\+", "\\\\+");
		pattern = pattern.replaceAll("\\'", "\\\\'");
		pattern = pattern.replaceAll("\\^", "\\\\^");
		pattern = pattern.replaceAll("\\.", "\\\\.");
		pattern = pattern.replaceAll("\\$", "\\\\$");
		pattern = pattern.replaceAll("\\|", "\\\\|");

		return pattern;
	}

}
