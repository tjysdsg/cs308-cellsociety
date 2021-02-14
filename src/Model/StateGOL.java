package Model;

/**
 * DEAD = 0, ALIVE = 1
 */
public class StateGOL extends State {

  public static final StateGOL DEAD = new StateGOL(0);
  public static final StateGOL ALIVE = new StateGOL(1);

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
