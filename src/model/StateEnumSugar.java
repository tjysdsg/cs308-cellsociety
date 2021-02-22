package model;

/**
 * EMPTY = 0, AGENT = 1
 */
public enum StateEnumSugar implements StateEnum {

  EMPTY, AGENT;

  public static final int EMPTY_VAL = 0;
  public static final int AGENT_VAL = 1;
  public static final int[] ALL_VALS = new int[]{0, 1};

  public static StateEnumSugar fromInt(int val) {
    return switch (val) {
      case EMPTY_VAL -> EMPTY;
      default -> AGENT;
    };
  }

  @Override
  public int toInteger() {
    if (this == AGENT) {
      return AGENT_VAL;
    } else {
      return EMPTY_VAL;
    }
  }

  @Override
  public String toString() {
    if (this == AGENT) {
      return "x";
    } else {
      return " ";
    }
  }
}
