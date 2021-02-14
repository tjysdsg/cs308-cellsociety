package model;

import java.util.Map;

/**
 * EMPTY = 0, O = 1, X = 2
 */
public class StateSegregation extends State {

  public static final int EMPTY_VAL = 0;
  public static final int O_VAL = 1;
  public static final int X_VAL = 2;

  public static final StateSegregation EMPTY = new StateSegregation(0);
  public static final StateSegregation O = new StateSegregation(1);
  public static final StateSegregation X = new StateSegregation(2);

  public static final Map<Integer, StateSegregation> INT_TO_STATES = Map.of(
      EMPTY_VAL, EMPTY,
      O_VAL, O,
      X_VAL, X
  );

  StateSegregation(int val) {
    super(val);
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
