package model;

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
}
