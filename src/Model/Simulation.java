package Model;

import java.util.List;

public abstract class Simulation {

  protected Grid grid;

  /**
   * Set configuration variables
   *
   * @param name  Name of the configuration, such as "gameSpeed".
   * @param value Value of the configuration, can be integer, double, etc. The specific type of the
   *              variable depends on the type of simulation and the configuration variable that is
   *              set.
   * @param <T>   Type of the value.
   */
  public abstract <T> void setConfig(String name, T value);

  /**
   * Set state of a cell
   *
   * @param r         Index of row, starting at 0
   * @param c         Index of column, starting at 0
   * @param s         An instance of State's subclass
   * @param immediate Set the state immediately if true, otherwise the state of the cell will be
   *                  changed in the next generation
   */
  public void setState(int r, int c, State s, boolean immediate) {
    grid.setState(r, c, s, immediate);
  }

  protected abstract void updateNextStates();

  /**
   * Update simulation to the next generation
   *
   * @return true if at least one cell's state is updated, can be used to determine if the
   * simulation is over
   */
  public boolean update() {
    updateNextStates();
    return grid.update();
  }

  /**
   * Get 2D list of states representing all states in the gird.
   */
  public List<List<State>> getGrid() {
    // TODO: implement this
    return null;
  }

  @Override
  public String toString() {
    // TODO: show config values
    String ret = "";
    ret += grid.toString();
    return ret;
  }
}
