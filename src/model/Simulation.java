package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
   * <p>
   * General configurable options available in all Simulation subclasses:
   * <ul>
   *   <li>
   *     "finite" (boolean): Whether the grid edge type is finite, NOTE that REGARDLESS of `value`,
   *     the edge type is set to finite.
   *   </li>
   *   <li>
   *     "infinite" (boolean): Whether the grid edge type is infinite, NOTE that REGARDLESS of `value`,
   *     the edge type is set to infinite.
   *   </li>
   *   <li>
   *     "wrapAround" (boolean): Whether the grid is toroidal, NOTE that REGARDLESS of `value`,
   *     the edge type is set to wrappable.
   *   </li>
   * </ul>
   *
   * @param name  Name of the configuration, such as "gameSpeed".
   * @param value Value of the configuration, can be integer, double, etc. The specific type of the
   *              variable depends on the type of simulation and the configuration variable that is
   *              set.
   * @param <T>   Type of the value.
   */
  public <T> void setConfig(String name, T value) {
    switch (name) {
      case "finite" -> grid.setEdgeType(EdgeType.FINITE);
      case "wrapAround" -> grid.setEdgeType(EdgeType.WRAP);
      case "infinite" -> grid.setEdgeType(EdgeType.INFINITE);
    }
  }

  /**
   * @see Grid#setNeighborhood(Neighborhood)
   */
  public void setNeighborhood(Neighborhood neighborhood) {
    grid.setNeighborhood(neighborhood);
  }

  /**
   * Get statistics of the simulation.
   *
   * @return A map, mapping the name of a metric to its value
   */
  public abstract Map<String, Object> getStatsMap();

  /**
   * Get simulation type name, such as "Fire", "Game of Life", "Wa-Tor", etc.
   */
  public abstract String getSimType();

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

  protected void updateStats() {
  }

  /**
   * Update simulation to the next generation
   */
  public void update() {
    updateNextStates();
    grid.update();
    updateStats();
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
  public List<List<Integer>> getGrid() {
    int nRows = grid.getNumRows();
    int nCols = grid.getNumCols();
    ArrayList<List<Integer>> ret = new ArrayList<>(nRows);

    for (int r = 0; r < nRows; ++r) {
      ArrayList<Integer> row = new ArrayList<>(nCols);
      for (int c = 0; c < nCols; ++c) {
        row.add(grid.getState(r, c).toInteger());
      }
      ret.add(row);
    }
    return ret;
  }

  /**
   * Get a string representation of the grid and statistics of the simulation.
   */
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append(grid.toString());

    // also print statistics
    Map<String, Object> stats = getStatsMap();
    for (Map.Entry<String, Object> entry : stats.entrySet()) {
      ret.append(entry.getKey())
          .append(":")
          .append(entry.getValue().toString())
          .append("\n");
    }
    return ret.toString();
  }
}
