import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String srcFolder = "D:\\workspace\\git\\repositories\\Skillbox\\java_basics\\images\\in";
        String dstFolder = "D:\\workspace\\git\\repositories\\Skillbox\\java_basics\\images\\out";

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        if (files != null && files.length > 0) {

            int totalCountOfProcessors = Runtime.getRuntime().availableProcessors();
            int remainCountOfFiles = files.length;
            int remainCountOfprocessors = totalCountOfProcessors;
            int positionOfMainFileList = 0;

            for (int step = 0; step < totalCountOfProcessors; step++) {
                int countForStep = remainCountOfFiles / remainCountOfprocessors;

                File[] filesForThread = new File[countForStep];

                System.arraycopy(files, positionOfMainFileList, filesForThread, 0, filesForThread.length);

                ImageResizer imageResizer = new ImageResizer(srcFolder, dstFolder,
                        filesForThread, 1000, step);
                imageResizer.start();

                positionOfMainFileList += filesForThread.length;
                remainCountOfFiles -= filesForThread.length;
                remainCountOfprocessors--;
            }
        }
    }
}
