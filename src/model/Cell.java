package model;

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

  /**
   * Get current cell state
   */
  public State getState() {
    return state;
  }

  /**
   * If lazy written, call this to do the actually update
   */
  public void update() {
    if (needUpdate) {
      state = nextState;
      needUpdate = false;
    }
  }

  /**
   * @see State#toString()
   */
  @Override
  public String toString() {
    return state.toString();
  }
}
