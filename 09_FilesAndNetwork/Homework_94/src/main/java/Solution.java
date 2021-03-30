import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {

        writeImagesToFile(getImgRefs());


    }

    private static void writeImagesToFile(List<String> refs) {

        refs.forEach(ref -> {
            try {
                downloadImage(ref);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private static void downloadImage(String urlImage) throws IOException {

        URL url = new URL(urlImage);

        Path directoryPath = Paths.get("lenta/images/");
        Path imagePath = Paths.get("lenta/images/" + FilenameUtils.getName(url.getPath()));

        Files.createDirectories(directoryPath);

        InputStream inputStream = new BufferedInputStream(url.openStream());
        Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();

        System.out.printf("Image %s was transfered into %s\n", urlImage, imagePath.toFile().getAbsoluteFile());

    }


    private static List<String> getImgRefs() {
        List<String> refs = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://lenta.ru").get();
            Elements elements = document.select("img");
            elements.forEach(img -> {
                refs.add(img.attr("abs:src"));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return refs;
    }

}
