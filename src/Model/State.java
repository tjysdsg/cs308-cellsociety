package Model;

import java.util.Objects;

public class State {

  protected int val;

  State(int val) {
    this.val = val;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    State state = (State) o;
    return val == state.val;
  }

  @Override
  public int hashCode() {
    return Objects.hash(val);
  }

  /**
   * Convert State to a integer, the integer is guaranteed to uniquely identify different states.
   * See docs of individual State subclasses to find out the specific int value.
   */
  public int toInteger() {
    return this.val;
  }
}
