import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

import java.util.concurrent.RecursiveTask;

@Getter
@Setter
public class LinkSearcher extends RecursiveTask<Set<TreeLink>> {

    private Set<TreeLink> links;
    private List<TreeLink> searchlist;
    private TreeLink rootLink;
    private TreeLink currentLink;

    public LinkSearcher(TreeLink rootLink) {
        this.links = new HashSet<>();
        this.rootLink = rootLink;
        this.currentLink = rootLink;
        this.searchlist = new ArrayList<>();
    }

    public LinkSearcher(TreeLink rootLink, TreeLink currentLink, Set<TreeLink> links) {
        this.rootLink = rootLink;
        this.currentLink = currentLink;
        this.links = links;
        this.searchlist = new ArrayList();
    }

    @SneakyThrows
    @Override
    protected Set<TreeLink> compute() {
        parseURL();
        executeTasks();
        return links;
    }

    private void executeTasks() {
        List<LinkSearcher> tasks = new ArrayList<>();
        searchlist.forEach(link -> {
            LinkSearcher task = new LinkSearcher(rootLink, link, links);
            task.fork();
            tasks.add(task);
        });
        for (LinkSearcher task : tasks
        ) {
            task.join();
        }
    }

    private void parseURL() throws InterruptedException {
        Thread.sleep(300);
        Connection connection = Jsoup.connect(currentLink.getUrlAddress());
        try {
            Document document = connection.userAgent("Mozilla").get();
            Elements elements = document.select("a");
            if (elements.isEmpty()) {
                return;
            }
            synchronized (links) {
                elements.forEach(element -> {
                    String urlAddress = element.attr("abs:href");
                    if (urlAddress.matches(rootLink.getUrlAddress() + "[A-Za-z/_-]+")) {
                        TreeLink newLink = new TreeLink(urlAddress);
                        if (!links.contains(newLink)) {
                            links.add(newLink);
                            searchlist.add(newLink);
                        }
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
