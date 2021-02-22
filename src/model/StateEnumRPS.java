package model;

/**
 * ROCK = 0, PAPER = 1, SCISSORS = 2
 */
public enum StateEnumRPS implements StateEnum {

  ROCK, PAPER, SCISSORS;

  public static final int ROCK_VAL = 0;
  public static final int PAPER_VAL = 1;
  public static final int SCISSORS_VAL = 2;
  public static final int[] ALL_VALS = new int[]{0, 1, 2};

  public static StateEnumRPS fromInt(int val) {
    return switch (val) {
      case PAPER_VAL -> PAPER;
      case SCISSORS_VAL -> SCISSORS;
      default -> ROCK;
    };
  }

  @Override
  public int toInteger() {
    if (this == ROCK) {
      return ROCK_VAL;
    } else if (this == PAPER) {
      return PAPER_VAL;
    } else {
      return SCISSORS_VAL;
    }
  }

  @Override
  public String toString() {
    if (this == ROCK) {
      return "\u25CF"; // black circle
    } else if (this == PAPER) {
      return "\u25A0"; // rectangle
    } else { // scissors
      return "\u2702";
    }
  }
}
