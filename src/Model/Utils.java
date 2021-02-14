package Model;

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
}
