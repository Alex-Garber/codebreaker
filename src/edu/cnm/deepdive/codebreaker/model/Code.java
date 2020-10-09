package edu.cnm.deepdive.codebreaker.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Contains the randomly generated code.
 */
public class Code {

  private final char[] secret;

  /**
   * Generates and randomizes the pool and length of the secret code.
   *
   * @param pool Allowed Characters in the secret.
   * @param length Total length of secret code.
   * @param rng Randomizes the secret code.
   */
  public Code(String pool, int length, Random rng) {
    secret = new char[length];
    for (int i = 0; i < secret.length; i++) {
      secret[i] = pool.charAt(rng.nextInt(pool.length()));
    }

  }

  @Override
  public String toString() {
    return new String(secret);
  }

  /**
   * Represents a single attempt of the users guess.
   */
  public class Guess {

    private static final String STRING_FORMAT = "{text: \"%s}\" , correct: %d' close: %d}";
    private String text;
    private final int correct;
    private final int close;

    /**
     * Computes how many of the characters in the users guess that are correct.
     * @param text
     */
    public Guess(String text) {
      this.text = text;
      int correct = 0;
      int close = 0;

      Map<Character, Set<Integer>> letterMap = getletterMap(text);

      char[] work = Arrays.copyOf(secret, secret.length);
      for (int i = 0; i < work.length; i++) {

        char letter = work[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
        if (positions.contains((i))) {
          correct++;
          positions.remove(i);
          work[i] = 0;
        }
      }

      for (char letter : work) {
        if (letter != 0) {
          Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
          if (positions.size() > 0) {
            close++;
            Iterator<Integer> iter = positions.iterator();
            iter.next();
            iter.remove();

          }
        }
      }

      this.correct = correct;
      this.close = close;

    }


    private Map<Character, Set<Integer>> getletterMap(String text) {
      Map<Character, Set<Integer>> letterMap = new HashMap<>();
      char[] letters = text.toCharArray();
      for (int i = 0; i < letters.length; i++) {
        char letter = letters[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, new HashSet<>());
        positions.add(i);
        letterMap.putIfAbsent(letter, positions);
      }
      return letterMap;
    }

    /**
     * Returns the text of this instance.
     */
    public String getText() {
      return text;
    }

    /**
     * Returns the number of correct in the users guess that are also in the secret code.
     */
    public int getCorrect() {
      return correct;
    }

    /**
     * Returns the number of close in the users guess that are also in the secret code .
     */
    public int getClose() {
      return close;
    }

    @Override
    public String toString() {
      return String.format(STRING_FORMAT, text, correct, close);
    }
  }

}

