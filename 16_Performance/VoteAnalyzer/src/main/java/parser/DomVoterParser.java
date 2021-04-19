package parser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.Voter;
import model.WorkTime;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomVoterParser extends AbstractVoterParser {

  @Override
  public void parseFile(String fileName)
      throws ParserConfigurationException, IOException, SAXException, ParseException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(new File(fileName));

    findEqualVoters(doc);
    fixWorkTimes(doc);
  }

  private void findEqualVoters(Document doc) throws ParseException {
    NodeList voters = doc.getElementsByTagName("voter");
    int votersCount = voters.getLength();
    for (int i = 0; i < votersCount; i++) {
      Node node = voters.item(i);
      NamedNodeMap attributes = node.getAttributes();

      String name = attributes.getNamedItem("name").getNodeValue();
      Date birthDay = getBirthDayFormat()
          .parse(attributes.getNamedItem("birthDay").getNodeValue());

      Voter voter = new Voter(name, birthDay);
      Integer count = getVoterCounts().get(voter);
      putVoterCount(voter, count == null ? 1 : count + 1);
    }
  }

  private void fixWorkTimes(Document doc) throws ParseException {
    NodeList visits = doc.getElementsByTagName("visit");
    int visitCount = visits.getLength();
    for (int i = 0; i < visitCount; i++) {
      Node node = visits.item(i);
      NamedNodeMap attributes = node.getAttributes();

      Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
      Date time = getVisitDateFormat().parse(attributes.getNamedItem("time").getNodeValue());
      WorkTime workTime = getVoteStationWorkTimes().get(station);
      if (workTime == null) {
        workTime = new WorkTime();
        putVoteStationWorkTimes(station, workTime);
      }
      workTime.addVisitTime(time.getTime());
    }
  }

}
