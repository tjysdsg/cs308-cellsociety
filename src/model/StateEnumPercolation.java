package model;

/**
 * BLOCKED = 0, OPEN = 1, PERCOLATED = 2
 */
public enum StateEnumPercolation implements StateEnum {

  BLOCKED, OPEN, PERCOLATED;

  public static final int BLOCKED_VAL = 0;
  public static final int OPEN_VAL = 1;
  public static final int PERCOLATED_VAL = 2;
  public static final int[] ALL_VALS = new int[]{0, 1, 2};

  public static StateEnumPercolation fromInt(int val) {
    return switch (val) {
      case OPEN_VAL -> OPEN;
      case PERCOLATED_VAL -> PERCOLATED;
      default -> BLOCKED;
    };
  }

  public int toInteger() {
    if (this == BLOCKED) {
      return BLOCKED_VAL;
    }
    if (this == OPEN) {
      return OPEN_VAL;
    } else {
      return PERCOLATED_VAL;
    }
  }

  @Override
  public String toString() {
    if (this == OPEN) {
      return " ";
    } else if (this == PERCOLATED) {
      return "~";
    } else {
      return "\u25A0"; // black block
    }
  }
}
