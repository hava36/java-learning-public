import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Solution {

    public static void main(String[] args) {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            String filename = bf.readLine();
            double size = Files.walk(Paths.get(filename))
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .mapToLong(File::length).sum();
            DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
            formatSymbols.setDecimalSeparator('.');
            DecimalFormat decimalFormat = new DecimalFormat("#.###", formatSymbols);
            System.out.printf("Размер папки составляет %s MB", decimalFormat.format(size / 1048576));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
