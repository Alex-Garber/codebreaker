package edu.cnm.deepdive.codebreaker.model;

/**
 * Extends {@link IllegalArgumentException}.IF the users a guess doesnt match the required length of the secret code IllegalGuessException is thrown.
 */
public class
IllegalGuessLengthException extends IllegalArgumentException {

  /**
   * Exception for incorrect length of guess.
   */
  public IllegalGuessLengthException() {
  }

  /**
   * IF the user uses a guess that doesnt match the required length of the secret code a message is displayed to the user
   * @param message Message includes the correct length of the guess.
   */
  public IllegalGuessLengthException(String message) {
    super(message);
  }

  /**
   *
   * @param message Message includes the the cause of the exception.
   * @param cause Reason for throwing exception.
   */
  public IllegalGuessLengthException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalGuessLengthException(Throwable cause) {
    super(cause);
  }
}
