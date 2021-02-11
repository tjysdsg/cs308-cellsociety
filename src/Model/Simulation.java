package Model;

import java.util.List;

public abstract class Simulation {

  public Grid grid;

  public abstract <T> void setConfig(String name, T value);

  public void setState(int r, int c, State s) {
    grid.setState(r, c, s);
  }

  protected abstract void updateNextStates();

  /**
   * Update simulation to the next time step
   *
   * @return true if at least one cell's state is updated, can be used to determine if the
   * simulation is over
   */
  public boolean update() {
    updateNextStates();
    return grid.update();
  }

  public abstract List<List<State>> getGrid();

  @Override
  public String toString() {
    // TODO: show config values
    String ret = "";
    ret += grid.toString();
    return ret;
  }
}
