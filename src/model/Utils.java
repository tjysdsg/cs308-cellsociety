package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

  /**
   * Randomly choose an element in a list. Returns -1 if the list is empty.
   *
   * @param list List to choose from
   * @param <T>  Type of the elements in the list
   * @return The index of the chosen element.
   */
  public static <T> int randomChoose(List<T> list) {
    if (list.isEmpty()) {
      return -1;
    }
    Random rand = new Random();
    return rand.nextInt(list.size());
  }

  /**
   * Wrap around an integer to an interval
   * <p>
   * NOTE: doesn't consider multiple wraps, for example, wrapping -100 into [0, 10] returns -90
   * instead of 0
   *
   * @param val: value
   * @param min: minimum, inclusive
   * @param max: maximum, inclusive
   * @return the wrapped value
   */
  public static int wrapInt(int val, int min, int max) {
    if (val < min) {
      return max - (min - val - 1);
    } else if (val > max) {
      return min + (val - max - 1);
    }

    return val;
  }

  /**
   * Read a text file into an array of String containing each line
   */
  public static String[] readResourceTxtToLines(String filename) {
    // https://stackoverflow.com/a/46613809/7730917
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    InputStream is = classLoader.getResourceAsStream(filename);
    if (is == null) {
      return null;
    }
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader reader = new BufferedReader(isr);

    // create and add blocks
    return reader.lines().toArray(String[]::new);
  }

  /**
   * Based on https://stackoverflow.com/a/19624586/7730917
   */
  public static List<String> permutation(String s) {
    // the result
    ArrayList<String> res = new ArrayList<>();
    // if input string's length is 1, return {s}
    if (s.length() == 1) {
      res.add(s);
    } else if (s.length() > 1) {
      int lastIndex = s.length() - 1;
      // find out the last character
      String last = s.substring(lastIndex);
      // rest of the string
      String rest = s.substring(0, lastIndex);
      // perform permutation on the rest string and merge with the last character
      res = merge(permutation(rest), last);
    }
    return res;
  }

  /**
   * Based on https://stackoverflow.com/a/19624586/7730917
   */
  private static ArrayList<String> merge(List<String> list, String c) {
    ArrayList<String> res = new ArrayList<>();
    // Loop through all the string in the list
    for (String s : list) {
      // for each string, insert the last character to all possible positions and
      // add them to the new list
      for (int i = 0; i <= s.length(); ++i) {
        String ps = new StringBuffer(s).insert(i, c).toString();
        res.add(ps);
      }
    }
    return res;
  }
}
