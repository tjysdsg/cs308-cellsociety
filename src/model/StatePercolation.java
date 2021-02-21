package model;

import java.util.Map;

/**
 * BLOCKED = 0, OPEN = 1, PERCOLATED = 2
 */
public class StatePercolation extends State {

  public static final int BLOCKED_VAL = 0;
  public static final int OPEN_VAL = 1;
  public static final int PERCOLATED_VAL = 2;

  public static final StatePercolation BLOCKED = new StatePercolation(0);
  public static final StatePercolation OPEN = new StatePercolation(1);
  public static final StatePercolation PERCOLATED = new StatePercolation(2);

  public static final Map<Integer, StatePercolation> INT_TO_STATES = Map.of(
      BLOCKED_VAL, BLOCKED,
      OPEN_VAL, OPEN,
      PERCOLATED_VAL, PERCOLATED
  );

  StatePercolation(int val) {
    super(val);
  }

  @Override
  public String toString() {
    if (this == OPEN) {
      return " ";
    } else if (this == PERCOLATED) {
      return "~";
    } else {
      return "\u25A0";
    }
  }
}
