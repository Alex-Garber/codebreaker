package edu.cnm.deepdive.codebreaker.model;

/**
 * Exception that extends {@link IllegalArgumentException} for the length of the guess.
 */
public class IllegalGuessCharacterException extends IllegalArgumentException{

  /**
   * Exception for incorrect guess characters used.
   */
  public IllegalGuessCharacterException() {
  }
/**
 * Prompts message to user about exception.
 */
  public IllegalGuessCharacterException(String message) {
    super(message);
  }

  /**
   * If the user uses characters that are not in the secret pool the exception alerts the user to the characters allowed in guess.
   *
   * @param message message to user that has valid guess characters.
   * @param cause Reason for exception thrown.
   */
  public IllegalGuessCharacterException(String message, Throwable cause) {
    super(message, cause);
  }
  public IllegalGuessCharacterException(Throwable cause) {
    super(cause);
  }
}
