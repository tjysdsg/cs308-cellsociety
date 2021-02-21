package model;

/**
 * EMPTY = 0, O = 1, X = 2
 */
public enum StateEnumSegregation implements StateEnum {

  EMPTY, O, X;

  public static final int EMPTY_VAL = 0;
  public static final int O_VAL = 1;
  public static final int X_VAL = 2;
  public static final int[] ALL_VALS = new int[]{0, 1, 2};

  public static StateEnumSegregation fromInt(int val) {
    return switch (val) {
      case O_VAL -> O;
      case X_VAL -> X;
      default -> EMPTY;
    };
  }

  @Override
  public int toInteger() {
    if (this == EMPTY) {
      return EMPTY_VAL;
    } else if (this == O) {
      return O_VAL;
    } else {
      return X_VAL;
    }
  }

  @Override
  public String toString() {
    if (this == O) {
      return "O";
    } else if (this == X) {
      return "X";
    } else {
      return " ";
    }
  }
}
