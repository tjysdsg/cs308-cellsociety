package Model;

/**
 * BLOCKED = 0, OPEN = 1, PERCOLATED = 2
 */
public class StatePercolation extends State {

  public static final StatePercolation BLOCKED = new StatePercolation(0);
  public static final StatePercolation OPEN = new StatePercolation(1);
  public static final StatePercolation PERCOLATED = new StatePercolation(2);

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
