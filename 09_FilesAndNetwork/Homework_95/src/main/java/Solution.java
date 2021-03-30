import core.Metro;
import utils.MetroHtmlParser;
import utils.MetroJsonReader;
import utils.MetroJsonWriter;

public class Solution {

    public static void main(String[] args) {

        MetroHtmlParser metroParser = new MetroHtmlParser();

        Metro metro1 = metroParser.parse("https://www.moscowmap.ru/metro.html#lines");

        MetroJsonWriter metroWriter = new MetroJsonWriter(metro1);
        metroWriter.write("metro.json");

        System.out.printf("Количество переходов %s\n\n", metro1.getConnections().size());

        MetroJsonReader jsonReader = new MetroJsonReader();
        Metro metro2 = jsonReader.read("metro.json");
        metro2.getLines().forEach(line -> {
            System.out.printf("%s - %s\n", line.toString(), line.getStations().size());
        });

    }

}
