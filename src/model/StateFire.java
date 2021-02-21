package model;

/**
 * EMPTY = 0, TREE = 1, BURNING = 2
 */
public enum StateFire implements StateEnum {

  EMPTY, TREE, BURNING;

  public static final int EMPTY_VAL = 0;
  public static final int TREE_VAL = 1;
  public static final int BURNING_VAL = 2;
  public static final int[] ALL_VALS = new int[]{0, 1, 2};

  public static StateFire fromInt(int val) {
    return switch (val) {
      case TREE_VAL -> TREE;
      case BURNING_VAL -> BURNING;
      default -> EMPTY;
    };
  }

  public int toInteger() {
    if (this == EMPTY) {
      return EMPTY_VAL;
    } else if (this == TREE) {
      return TREE_VAL;
    } else {
      return BURNING_VAL;
    }
  }

  @Override
  public String toString() {
    if (this == TREE) {
      return "\uD83C\uDF32"; // tree emoji
    } else if (this == BURNING) {
      return "\uD83D\uDD25"; // fire emoji
    } else {
      return "\u3000"; // space with the same width as emoji
    }
  }
}
