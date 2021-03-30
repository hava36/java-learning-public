import java.io.FileWriter;
import java.io.IOException;

public class LinkFileWriter {

    private TreeLink rootLink;

    public LinkFileWriter(TreeLink rootLink) {
        this.rootLink = rootLink;
    }

    public void write(String filename) throws IOException {
        int level = 0;
        FileWriter fileWriter = new FileWriter(filename);
        StringBuffer buffer = new StringBuffer();
        execute(rootLink, fileWriter, buffer, level);
        fileWriter.close();
        System.out.printf("File %s was writing\n", filename);
    }

    private void execute(TreeLink link, FileWriter fileWriter, StringBuffer buffer, int level) throws IOException {
        int index = 0;
        while (index < level) {
            buffer.append("\t");
            index++;
        }
        buffer.append(link.getUrlAddress());
        buffer.append("\n");
        fileWriter.write(buffer.toString());
        buffer.setLength(0);
        for (TreeLink childLink: link.getChildLinks()
             ) {
            int nextLevel = level + 1;
            execute(childLink, fileWriter, buffer, nextLevel);
        }

    }

}
