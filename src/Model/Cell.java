package Model;

public class Cell {

  private State state;
  private State nextState;

  public Cell() {
  }

  /**
   * Lazy write the value of state, call update() to actually change the value
   */
  public void setState(State state) {
    this.nextState = state;
  }

  public State getState() {
    return state;
  }

  public boolean update() {
    boolean ret = state != nextState;
    state = nextState;
    return ret;
  }

  @Override
  public String toString() {
    return state.toString();
  }
}
