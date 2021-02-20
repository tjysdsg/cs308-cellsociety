package model;

import java.util.Map;

/**
 * ROCK = 0, PAPER = 1, SCISSORS = 2
 */
public class StateRPS extends State {

  public static final int ROCK_VAL = 0;
  public static final int PAPER_VAL = 1;
  public static final int SCISSORS_VAL = 2;

  public static final StateRPS ROCK = new StateRPS(ROCK_VAL);
  public static final StateRPS PAPER = new StateRPS(PAPER_VAL);
  public static final StateRPS SCISSORS = new StateRPS(SCISSORS_VAL);

  public static final Map<Integer, StateRPS> INT_TO_STATES = Map.of(
      ROCK_VAL, ROCK,
      PAPER_VAL, PAPER,
      SCISSORS_VAL, SCISSORS
  );

  StateRPS(int val) {
    super(val);
  }

  @Override
  public String toString() {
    if (this.equals(ROCK)) {
      return "\u25CF";
    } else if (this.equals(PAPER)) {
      return "\u25A0";
    } else { // scissors
      return "\u2702";
    }
  }
}
