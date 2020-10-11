package edu.cnm.deepdive.codebreaker.model;

import edu.cnm.deepdive.codebreaker.model.Code.Guess;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * {@link Game} takes generated secret code and compares the users guess to the generated secret code pool of characters.
 * If the guess is to short or if the guess doesnt contain the correct pool of characters it throws {@link IllegalArgumentException}
 *
 */
public class Game {

  private static final String BAD_GUESS_PATTERN_FORMAT = "^.*[^%s].*$";
  private static final String ILLEGAL_CHARACTER_MESSAGE =
      "Guess include invalid characters: required=%s; provided =%s.";
  private static final String ILLEGAL_LENGHT_MESSAGE =
      "Invalid guess length, required=%d; provided=%d.";

  private final Code code;
  private final List<Guess> guesses;
  private final String pool;
  private final int length;
  private final String badGuessPattern;

  /**
   * Uses the randomized pool of characters length to compare the users guess. If the guess is to short the user is notified.
   *  @param pool Characters allowed in guess.
   * @param length Total length of characters in the pool.
   * @param rng Source of random.
   */
  public Game(String pool, int length, Random rng) {
    code = new Code(pool, length, rng);
    guesses = new LinkedList<>();
    this.pool = pool;
    this.length = length;
    badGuessPattern = String.format(BAD_GUESS_PATTERN_FORMAT, pool);
  }

  /**
   * Returns randomized code.
   *
   */
  public Code getCode() {
    return code;
  }

  /**
   * Returns users guess.
   *
   */
  public List<Guess> getGuesses() {
    return  Collections.unmodifiableList(guesses);
  }

  /**
   * Returns the allowed pool of characters.
   *
   */
  public String getPool() {
    return pool;
  }

  /**
   * Returns allowed length for the pool of characters.
   *
   */
  public int getLength() {
    return length;
  }

  /**
   * Returns the users guess size.
   *
   */
  public int getGuessCount() {
    return guesses.size();
  }


  /**
   * Throws {@link IllegalArgumentException} if the text length of users guess is not the allowed length or if characters that are not allowed characters are usded.
   *If guess meets the required length and required pool of characters the guess is accepted as a valid guess
   *
   * @param text Message to user about guess requirements.
   * @return Returns the valid guess.
   * @throws IllegalGuessLengthException If the characters in the guess dont match the correct length IllegalGuessLengthException is thrown.
   * @throws IllegalGuessCharacterException If characters are not in the secret pool of characters IllegalGuessCharacterException is thrown.
   */
  public Guess guess(String text)
      throws IllegalGuessLengthException, IllegalGuessCharacterException{
    if (text.length() != length) {
      throw new IllegalGuessLengthException(String.format(
          ILLEGAL_LENGHT_MESSAGE, length, text.length()));
    }
    if (text.matches(badGuessPattern)){
      throw new IllegalGuessCharacterException(String.format(
          ILLEGAL_CHARACTER_MESSAGE, pool, text));
    }
    Guess guess = code.new Guess(text);
    guesses.add(guess);
    return guess;

  }

  /**
   * Clears the guesses.
   */
  public void restart() {
    guesses.clear();
  }



}
