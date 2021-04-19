package parser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import model.Voter;
import model.WorkTime;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxVoterParser extends AbstractVoterParser {

  private final SAXParserFactory factory;
  private SAXParser saxParser;

  public SaxVoterParser() {
    factory = SAXParserFactory.newInstance();

  }

  @Override
  public void parseFile(String filename)
      throws ParserConfigurationException, IOException, SAXException {

    saxParser = factory.newSAXParser();
    XMLHandler xmlHandler = new XMLHandler();
    saxParser.parse(new File(filename), xmlHandler);

  }

  class XMLHandler extends DefaultHandler {

    private Voter voter;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
      try {
        if (qName.equals("voter") && voter == null) {
          Date birthday = getBirthDayFormat().parse(attributes.getValue("birthDay"));
          this.voter = new Voter(attributes.getValue("name"), birthday);
        } else if (qName.equals("visit") && voter != null) {
          int count = getVoterCounts().getOrDefault(voter, 0);
          putVoterCount(voter, ++count);
          Integer station = Integer.valueOf(attributes.getValue("station"));
          Date time = getVisitDateFormat().parse(attributes.getValue("time"));
          WorkTime workTime = getVoteStationWorkTimes().get(station);
          if (workTime == null) {
            workTime = new WorkTime();
            putVoteStationWorkTimes(station, workTime);
          }
          workTime.addVisitTime(time.getTime());
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
      if (qName.equals("voter")) {
        voter = null;
      }
    }

  }

}
