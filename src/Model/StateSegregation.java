package Model;

/**
 * EMPTY = 0, O = 1, X = 2
 */
public class StateSegregation extends State {

  public static final StateSegregation EMPTY = new StateSegregation(0);
  public static final StateSegregation O = new StateSegregation(1);
  public static final StateSegregation X = new StateSegregation(2);

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
