import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;

public class Solution {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.println("Please, input source path:");
            String sourceFilename = reader.readLine();

            System.out.println("Please, input destination path:");
            String destinationFilename = reader.readLine();

            File source = new File(sourceFilename);
            File destination = new File(destinationFilename);

            if (source.isDirectory()) {
                copyFolder(source, destination);
            } else {
                Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            System.out.printf("Copying files from '%s' to '%s' was successful",
                    source, source);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void copyFolder(File source, File destination) throws IOException {
        destination.mkdir();
        File[] files = source.listFiles();
        if (files.length > 0) {
            for (File fileSource : files
            ) {
                File fileDestination = new File(destination.getAbsolutePath() + "\\" + fileSource.getName());
                if (fileSource.isDirectory()) {
                    copyFolder(fileSource, fileDestination);
                } else {
                    Files.copy(fileSource.toPath(), fileDestination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

}
