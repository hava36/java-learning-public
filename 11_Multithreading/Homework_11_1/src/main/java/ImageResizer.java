import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResizer extends Thread {

    private String srcFolder;
    private String dstFolder;
    private File[] files;
    private int standardWidth;
    private int threadNumber;

    public ImageResizer(String srcFolder, String dstFolder, File[] files, int standardWidth, int threadNumber) {
        this.srcFolder = srcFolder;
        this.dstFolder = dstFolder;
        this.files = files;
        this.standardWidth = standardWidth;
        this.threadNumber = threadNumber;
    }

    private BufferedImage resizeImage(BufferedImage image) {

        return Scalr.resize(image, standardWidth);

    }


    @Override
    public void run() {

        try {
            for (File file : files) {
                BufferedImage sourceImage = ImageIO.read(file);
                if (sourceImage == null) {
                    continue;
                }
                BufferedImage destinationImage = resizeImage(sourceImage);
                if (destinationImage != null) {
                    File newFile = new File(dstFolder + "/" + file.getName());
                    ImageIO.write(destinationImage, "jpg", newFile);
                }
            }
            System.out.printf("Thread %s is completed!\n", threadNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
