package parser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import model.Voter;
import model.WorkTime;
import org.xml.sax.SAXException;

public abstract class AbstractVoterParser {

  private final SimpleDateFormat birthDayFormat;
  private final SimpleDateFormat visitDateFormat;

  private Map<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
  private Map<Voter, Integer> voterCounts = new HashMap<>();

  public AbstractVoterParser() {
    this.birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    this.visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    this.voteStationWorkTimes = new HashMap<>();
    this.voterCounts = new HashMap<>();
  }

  public abstract void parseFile(String filename)
      throws ParserConfigurationException, IOException, SAXException, ParseException;

  public void printData() {
    //Printing results
    System.out.println("Voting station work times: ");
    for (Integer votingStation : getVoteStationWorkTimes().keySet()) {
      WorkTime workTime = getVoteStationWorkTimes().get(votingStation);
      System.out.println("\t" + votingStation + " - " + workTime);
    }

    System.out.println("Duplicated voters: ");
    for (Voter voter : getVoterCounts().keySet()) {
      Integer count = getVoterCounts().get(voter);
      if (count > 1) {
        System.out.println("\t" + voter + " - " + count);
      }
    }
  }

  public Map<Integer, WorkTime> getVoteStationWorkTimes() {
    return voteStationWorkTimes;
  }

  public Map<Voter, Integer> getVoterCounts() {
    return voterCounts;
  }

  public void putVoteStationWorkTimes(
      Integer station, WorkTime workTime) {
    voteStationWorkTimes.put(station, workTime);
  }

  public void putVoterCount(Voter voter, Integer count) {
    voterCounts.put(voter, count);
  }

  public SimpleDateFormat getBirthDayFormat() {
    return birthDayFormat;
  }

  public SimpleDateFormat getVisitDateFormat() {
    return visitDateFormat;
  }

}
