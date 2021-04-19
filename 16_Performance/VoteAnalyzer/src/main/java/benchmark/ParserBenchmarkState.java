package benchmark;

import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.xml.sax.SAXException;
import parser.AbstractVoterParser;
import parser.DomVoterParser;
import parser.SaxVoterParser;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
public class ParserBenchmarkState {

  @Param("./16_Performance/VoteAnalyzer/res/data-18M.xml")
  private String fileName;

  public static void main(String[] args)
      throws IOException {
    org.openjdk.jmh.Main.main(args);
  }

  @Benchmark
  public void DomVoterParserBenchmark()
      throws ParserConfigurationException, SAXException, ParseException, IOException {
    AbstractVoterParser voterParser = new DomVoterParser();
    voterParser.parseFile(fileName);
  }

  @Benchmark
  public void SaxVoterParserBenchmark()
      throws ParserConfigurationException, SAXException, ParseException, IOException {
    AbstractVoterParser voterParser = new SaxVoterParser();
    voterParser.parseFile(fileName);
  }

}
