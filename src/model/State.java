package model;

import java.util.Objects;

/**
 * Base class of states.
 * <p>
 * Nothing important here, please see the subclasses of State.
 */
public class State {

  protected StateEnum stateType;

  /**
   * Constructor
   *
   * @param stateType: type of the state, such as EMPTY vs FIRE, FISH vs SHARK, etc.
   */
  public State(StateEnum stateType) {
    this.stateType = stateType;
  }

  /**
   * Get current state type
   */
  StateEnum getStateType() {
    return stateType;
  }

  /**
   * Boilerplate for comparing State objects
   */
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

  /**
   * Boilerplate for hashing a State object
   */
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

  /**
   * Controls how the state is printed on terminal, doesn't affect View at all
   */
  @Override
  public String toString() {
    return stateType.toString();
  }
}
