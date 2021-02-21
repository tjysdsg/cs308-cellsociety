package model;

/**
 * EMPTY = 0, ANTS = 1
 */
public enum StateEnumAnt implements StateEnum {

  EMPTY, ANTS;

  public static final int EMPTY_VAL = 0;
  public static final int ANTS_VAL = 1;
  public static final int[] ALL_VALS = new int[]{0, 1};

  public static StateEnumAnt fromInt(int val) {
    return switch (val) {
      case EMPTY_VAL -> EMPTY;
      default -> ANTS;
    };
  }

  @Override
  public int toInteger() {
    if (this == ANTS) {
      return ANTS_VAL;
    } else {
      return EMPTY_VAL;
    }
  }

  @Override
  public String toString() {
    if (this == ANTS) {
      return "\uD83D\uDC1C"; // ant emoji
    } else {
      return "\u3000"; // empty space with same width as an emoji
    }
  }
}
