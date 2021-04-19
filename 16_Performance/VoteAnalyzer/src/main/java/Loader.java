import parser.AbstractVoterParser;
import parser.SaxVoterParser;

public class Loader {

  public static void main(String[] args) throws Exception {

    String fileName = "./16_Performance/VoteAnalyzer/res/data-1572M.xml";

    AbstractVoterParser voterParser = new SaxVoterParser();
    voterParser.parseFile(fileName);
    voterParser.printData();

    DBConnection.insertVoterCounts(voterParser.getVoterCounts());

  }

}