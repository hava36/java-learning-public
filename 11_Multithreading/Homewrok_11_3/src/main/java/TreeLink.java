import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
@ToString
public class TreeLink implements Comparable<TreeLink> {

    private TreeLink parentLink;
    private String urlAddress;
    private String[] urlPaths;

    @ToString.Exclude
    private List<TreeLink> childLinks;

    public TreeLink(String urlAddress) {
        this.urlAddress = urlAddress;
        this.childLinks = new ArrayList<>();
        this.urlPaths = urlAddress.split("/{1,}");
    }

    public synchronized void add(TreeLink link) {
        String[] urlChildPaths = link.getUrlAddress().split("/{1,}");
        int difference = urlChildPaths.length - urlPaths.length;
        if (difference > 1) {
            String childUrlAddress = urlAddress + urlChildPaths[urlPaths.length] + "/";
            TreeLink childLink = getChild(childUrlAddress);
            if (childLink == null) {
                childLink = new TreeLink(childUrlAddress);
                childLinks.add(childLink);
            }
            childLink.add(link);
        } else {
            childLinks.add(link);
        }
    }

    public synchronized boolean contains(String urlAddress) {
        String[] urlChildPaths = urlAddress.split("/{1,}");
        int difference = urlChildPaths.length - urlPaths.length;
        if (difference > 1) {
            String childUrlAddress = this.urlAddress + urlChildPaths[urlPaths.length] + "/";
            TreeLink childLink = getChild(childUrlAddress);
            if (childLink == null) {
                return false;
            }
            return childLink.contains(urlAddress);
        }
        TreeLink child = getChild(urlAddress);
        if (child == null) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(TreeLink link) {
        return this.getUrlAddress().compareTo(link.getUrlAddress());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeLink link = (TreeLink) o;
        return Objects.equals(urlAddress, link.urlAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlAddress);
    }

    private TreeLink getChild(String urlAddress) {
        for (TreeLink child: childLinks
             ) {
            if (child.getUrlAddress().equals(urlAddress)) {
                return child;
            }
        }
        return null;
    }

}
