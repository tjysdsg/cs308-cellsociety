package Model;

public class Cell {

  private State state;
  private State nextState;
  private boolean needUpdate = false;

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
      needUpdate = true;
    }
  }

  public State getState() {
    return state;
  }

  public boolean update() {
    if (needUpdate) {
      state = nextState;
      needUpdate = false;
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return state.toString();
  }
}
