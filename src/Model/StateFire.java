package Model;

/**
 * EMPTY = 0, TREE = 1, BURNING = 2
 */
public class StateFire extends State {

  public static final StateFire EMPTY = new StateFire(0);
  public static final StateFire TREE = new StateFire(1);
  public static final StateFire BURNING = new StateFire(2);

  StateFire(int val) {
    super(val);
  }

  @Override
  public String toString() {
    if (this == TREE) {
      return "\uD83C\uDF32";
    } else if (BURNING.equals(this)) {
      return "\uD83D\uDD25";
    } else {
      return "\u3000"; // space with the same width as emoji
    }
  }
}
