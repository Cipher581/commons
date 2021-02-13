package art.cipher581.commons.util;

import java.io.File;
import java.util.Comparator;

public class FileComparatorCreation implements Comparator<File> {

	@Override
	public int compare(File a, File b) {
		return (int) (a.lastModified() - b.lastModified());
	}

}
