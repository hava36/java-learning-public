package exception.parser.student;

public class ParseStudentAgeException extends NumberFormatException {

  /**
   * Constructs a <code>NumberFormatException</code> with the specified detail message.
   *
   * @param s the detail message.
   */
  public ParseStudentAgeException(String s) {
    super(s);
  }
}
