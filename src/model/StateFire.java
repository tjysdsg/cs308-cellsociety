package model;

import java.util.Map;

/**
 * EMPTY = 0, TREE = 1, BURNING = 2
 */
public class StateFire extends State {

  public static final int EMPTY_VAL = 0;
  public static final int TREE_VAL = 1;
  public static final int BURNING_VAL = 2;

  public static final StateFire EMPTY = new StateFire(EMPTY_VAL);
  public static final StateFire TREE = new StateFire(TREE_VAL);
  public static final StateFire BURNING = new StateFire(BURNING_VAL);

  public static final Map<Integer, StateFire> INT_TO_STATES = Map.of(
      EMPTY_VAL, EMPTY,
      TREE_VAL, TREE,
      BURNING_VAL, BURNING
  );

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
