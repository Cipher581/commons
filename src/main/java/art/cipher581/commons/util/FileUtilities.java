package art.cipher581.commons.util;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;


public class FileUtilities {

    private static final Random RANDOM = new Random(System.currentTimeMillis());


    public static String getFileNameWithoutExtension(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file is null"); //$NON-NLS-1$
        }

        String fileName = file.getName();

        int idx = file.getName().lastIndexOf('.');

        if (idx < 0) {
            return fileName;
        } else {
            return fileName.substring(0, idx);
        }
    }


    public static String getExtension(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file is null"); //$NON-NLS-1$
        }

        String fileName = file.getName();

        int idx = file.getName().lastIndexOf('.');

        if (idx < 0) {
            return ""; //$NON-NLS-1$
        } else {
            return fileName.substring(idx + 1);
        }
    }


    /**
     * Checks if file exists. If file exists the filename will be extended by a
     * number until the file doesn't exist.<br>
     * <br>
     * Example:<br>
     * <br>
     * /home/user/test.txt ... exists<br>
     * /home/user/test (1).txt ... exists<br>
     * /home/user/test (2).txt ... exists<br>
     * /home/user/test (3).txt ... doesn't exist -> so this file is
     * returned<br>
     *
     * @param file
     *
     * @return file non-existing file with the desired name (possibly extended
     * by a number if it already exists)
     */
    public static File getNumberedFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file is null"); //$NON-NLS-1$
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException("file is a directory"); //$NON-NLS-1$
        }

        // System.out.println("file " + file);
        File dir = file.getParentFile();
        String fileName = FileUtilities.getFileNameWithoutExtension(file);
        String extension = FileUtilities.getExtension(file);

        // System.out.println("fileName " + fileName);
        // System.out.println("extension " + extension);
        int i = 1;
        while (file.exists()) {
            String numberedFilename = fileName + " (" + i + ")." + extension; //$NON-NLS-1$ //$NON-NLS-2$

            file = new File(dir, numberedFilename);

            // System.out.println("check file " + file);
            i++;
        }

        return file;
    }


    public static void deleteFile(File file) throws IOException {
        boolean success = file.delete();

        if (!success) {
            throw new IOException("file " + file + " could not be deleted");
        }
    }


    public static void createDir(File dir) throws IOException {
        if (!dir.getParentFile().exists()) {
            createDir(dir.getParentFile());
        }

        boolean success = dir.mkdir();

        if (!success) {
            throw new IOException("error creating directory " + dir);
        }
    }


    public static File getTempDir() {
        return new File(System.getProperty("java.io.tmpdir"));
    }


    public static File getRandomTempDir() {
        String dirName = String.valueOf(RANDOM.nextLong());

        return new File(getTempDir(), dirName);
    }


    public static File getTempFile() {
        return getTempFile(getTempDir());
    }


    public static File getTempFile(File dir) {
        String fileName = RANDOM.nextLong() + ".tmp";

        return new File(dir, fileName);
    }


    public static byte[] read(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);

        try {
            DataInputStream dis = new DataInputStream(fis);

            try {
                byte[] keyBytes = new byte[(int) file.length()];

                dis.readFully(keyBytes);

                return keyBytes;
            } finally {
                Safely.close(dis);
            }
        } finally {
            Safely.close(fis);
        }
    }


    public static void writeFile(byte[] data, File dest, boolean overwrite) throws IOException {
        if (dest.exists() && !overwrite) {
            throw new FileAlreadyExistsException(dest.getAbsolutePath());
        }

        FileOutputStream fos = new FileOutputStream(dest);

        try {
            fos.write(data);
        } finally {
            Safely.close(fos);
        }
    }

    
    public static List<File> getFiles(File dir, String pattern) throws IOException {
    	Pattern patternCompiled = Pattern.compile(pattern);
    	
    	return getFiles(dir, patternCompiled);
    }
   
    
    public static List<File> getFiles(File dir, Pattern pattern) throws IOException {
    	return getFiles(dir, pattern, false);
    }
   

    public static List<File> getFiles(File dir, Pattern pattern, boolean recursive) throws IOException {
        if (dir == null) {
            throw new IllegalArgumentException("dir is null");
        }

        File[] all = dir.listFiles();

        if (all == null) {
            throw new IOException("error while listing content of " + dir);
        } else {
            List<File> matching = new LinkedList<>();

            for (File file : all) {
                if (file.isFile()) {
                    if (pattern.matcher(file.getName()).matches()) {
                        matching.add(file);
                    }
                } else if (file.isDirectory() && recursive) {
                	List<File> matchingSubDir = getFiles(file, pattern, recursive);

                	matching.addAll(matchingSubDir);
                }
            }

            return matching;
        }
    }


    public static File getHomeDir(String child) {
        String homeDirStr = System.getProperty("user.home");

        if (homeDirStr == null || homeDirStr.trim().isEmpty()) {
            throw new IllegalStateException("System variable user.home is not set");
        }

        File file = new File(homeDirStr);
        file = new File(file, child);

        return file;
    }
    
    public static String replaceInvalidFileNameChars(String s) {
    	return s.replaceAll("[^a-zA-Z0-9\\.\\-_]", "");
    }

}
