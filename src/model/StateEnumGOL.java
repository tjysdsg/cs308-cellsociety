package model;

/**
 * DEAD = 0, ALIVE = 1
 */
public enum StateEnumGOL implements StateEnum {

  DEAD, ALIVE;

  public static final int DEAD_VAL = 0;
  public static final int ALIVE_VAL = 1;
  public static final int[] ALL_VALS = new int[]{0, 1};

  public static StateEnumGOL fromInt(int val) {
    return switch (val) {
      case ALIVE_VAL -> ALIVE;
      default -> DEAD;
    };
  }

  public int toInteger() {
    if (this == ALIVE) {
      return ALIVE_VAL;
    } else {
      return DEAD_VAL;
    }
  }

  @Override
  public String toString() {
    if (this == ALIVE) {
      return "\u25A0"; // black block
    }
    return " ";
  }
}
