package exception;

public class ParseActionException extends Exception {

  /**
   * Constructs a new runtime exception with {@code null} as its detail message.  The cause is not
   * initialized, and may subsequently be initialized by a call to {@link #initCause}.
   */
  public ParseActionException(String message) {
    super(message);
  }

}
