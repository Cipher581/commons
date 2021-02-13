package art.cipher581.commons.util.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import art.cipher581.commons.util.Safely;

public class FileHashesWriter {
	
	public static void write(Map<String, File> hashes, String fileName) throws IOException {
		Map<File, Map<String, File>> byDir = groupByDir(hashes);
		
		for (File dir : byDir.keySet()) {
			File hashesFile = new File(dir, fileName);
			Map<String, File> dirHashes = byDir.get(dir);

			write(dirHashes, hashesFile);
		}
	}
	
	private static void write(Map<String, File> hashes, File hashesFile) throws IOException {
		FileWriter fileWriter = new FileWriter(hashesFile);
		
		try {
			for (String hash : hashes.keySet()) {
				File file = hashes.get(hash);

				fileWriter.write(hash);
				fileWriter.write("\t");
				fileWriter.write(file.getName());
				fileWriter.write("\n");
			}
		} finally {
			Safely.flush(fileWriter);
			Safely.close(fileWriter);
		}
	}
	
	private static Map<File, Map<String, File>> groupByDir(Map<String, File> hashes) {
		Map<File, Map<String, File>> byDir = new HashMap<>();

		for (Entry<String, File> entry : hashes.entrySet()) {
			File f = entry.getValue();
			
			if (f != null) {
				File d = f.getParentFile();
				
				Map<String, File> dirHahses = byDir.get(d);
				if (dirHahses == null) {
					dirHahses = new HashMap<>();
					byDir.put(d, dirHahses);
				}
	
				dirHahses.put(entry.getKey(), f);
			}
		}
		
		return byDir;
	}

}
