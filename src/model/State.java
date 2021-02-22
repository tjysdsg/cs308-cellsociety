package model;

import java.util.Objects;

/**
 * Base class of states.
 * <p>
 * Nothing important here, please see the subclasses of State.
 */
public class State {

  protected StateEnum stateType;

  public State(StateEnum stateType) {
    this.stateType = stateType;
  }

  StateEnum getStateType() {
    return stateType;
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
    return stateType == state.stateType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(stateType);
  }

  /**
   * Convert State to a integer, the integer is guaranteed to uniquely identify different states.
   * See docs of individual State subclasses to find out the specific int value.
   */
  public int toInteger() {
    return stateType.toInteger();
  }

  @Override
  public String toString() {
    return stateType.toString();
  }
}
