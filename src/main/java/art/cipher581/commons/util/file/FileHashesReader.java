package art.cipher581.commons.util.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import art.cipher581.commons.util.FileUtilities;
import art.cipher581.commons.util.Safely;


public class FileHashesReader {

	private static final Logger LOGGER = LogManager.getLogger(FileHashesReader.class);


	public static Map<String, File> readHashFiles(File dir) throws IOException {
		Map<String, File> fileHashes = new HashMap<>();
		
		readHashFiles(dir, fileHashes);

		return fileHashes;
	}
	
	public static void readHashFiles(File dir, Map<String, File> fileHashes) throws IOException {
		readHashFiles(dir, fileHashes, "file_hashes.txt");
	}


	public static void readHashFiles(File dir, Map<String, File> fileHashes, String hashFileName) throws IOException {
		if (dir.isDirectory()) {
			List<File> hashFiles = FileUtilities.getFiles(dir, hashFileName);
	
			for (File hashFile : hashFiles) {
				readHashFile(hashFile, fileHashes);
			}
		}
	}


	public static Map<String, File> readHashFile(File hashFile) {
		Map<String, File> fileHashes = new HashMap<>();

		readHashFile(hashFile, fileHashes);

		return fileHashes;
	}


	public static void readHashFile(File hashFile, Map<String, File> fileHashes) {
		LOGGER.info("reading hash file " + hashFile);

		try {
			File dir = hashFile.getParentFile();

			BufferedReader r = new BufferedReader(new FileReader(hashFile));

			try {
				String line;
				while ((line = r.readLine()) != null) {
					String[] seg = line.split("\t");

					if (seg.length >= 2) {
						String hash = seg[0];
						String fileName = seg[1];

						fileHashes.put(hash, new File(dir, fileName));
					}
				}
			} finally {
				Safely.close(r);
			}
		} catch (Exception ex) {
			LOGGER.error("Error while reading hash file " + hashFile, ex);
		}
	}

}
