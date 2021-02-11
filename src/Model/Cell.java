package Model;

public class Cell {

  private State state;
  private State nextState;

  public Cell(State s) {
    this.state = s;
    this.nextState = s;
  }

  /**
   * Lazy write the value of state, call update() to actually change the value
   */
  public void setState(State state, boolean immediate) {
    if (immediate) {
      this.state = state;
    } else {
      this.nextState = state;
    }
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
