package Model;

import java.util.List;

/**
 * Base class of all simulation, responsible of:
 * <ul>
 *   <li>
 *     update cells to next generation
 *   </li>
 *   <li>
 *     receive configuration values
 *   </li>
 *   <li>
 *     collect statistics
 *   </li>
 *   <li>
 *     returns the grid
 *   </li>
 * </ul>
 *
 * @author jt304
 */
public abstract class Simulation {

  protected Grid grid;
  protected boolean isOver = false;

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
   */
  public void update() {
    updateNextStates();
    grid.update();
  }

  /**
   * Returns true if the simulation is over
   */
  public boolean isOver() {
    return isOver;
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
