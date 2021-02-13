package Model;

public class State {

  protected final int val;

  State(int val) {
    this.val = val;
  }

  /**
   * Convert State to a integer, the integer is guaranteed to uniquely identify different states.
   * See docs of individual State subclasses to find out the specific int value.
   */
  public int toInteger() {
    return this.val;
  }
}
