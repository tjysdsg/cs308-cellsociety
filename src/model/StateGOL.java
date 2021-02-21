package model;

import java.util.Map;

/**
 * DEAD = 0, ALIVE = 1
 */
public class StateGOL extends State {

  public static final int DEAD_VAL = 0;
  public static final int ALIVE_VAL = 1;

  public static final StateGOL DEAD = new StateGOL(DEAD_VAL);
  public static final StateGOL ALIVE = new StateGOL(ALIVE_VAL);

  public static final Map<Integer, StateGOL> INT_TO_STATES = Map.of(
      DEAD_VAL, DEAD,
      ALIVE_VAL, ALIVE
  );

  StateGOL(int val) {
    super(val);
  }

  @Override
  public String toString() {
    if (this == ALIVE) {
      return "\u25A0";
    }
    return " ";
  }
}
