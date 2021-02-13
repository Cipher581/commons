package art.cipher581.commons.util;


import java.io.File;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileComparatorExtractSortNumber implements Comparator<File> {

	private final Pattern extractPattern;


	public FileComparatorExtractSortNumber(String extractPattern) {
		super();

		this.extractPattern = Pattern.compile(extractPattern);
	}


	@Override
	public int compare(File f1, File f2) {
		return extract(f1) - extract(f2);
	}


	private int extract(File f) {
		Matcher m = extractPattern.matcher(f.getName());

		String s;
		if (m.matches()) {
			s = m.group(1);
		} else {
			throw new IllegalStateException("Pattern (" + extractPattern.pattern() + ") is not compatible with file name " + f.getName());
		}

		return Integer.parseInt(s);
	}

}
