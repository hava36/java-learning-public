import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Solution {

    private static final String url = "https://lenta.ru/";

    public static void main(String[] args) {

        TreeLink rootLink = new TreeLink(url);
        LinkSearcher linkExecutor = new LinkSearcher(rootLink);
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Set<TreeLink> links = forkJoinPool.invoke(linkExecutor);

        links.forEach(link -> {
            if (!rootLink.contains(link.getUrlAddress())) {
                rootLink.add(link);
            }
        });

        LinkFileWriter fileWriter = new LinkFileWriter(rootLink);
        try {
            fileWriter.write((new URL(url)).getHost() + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
