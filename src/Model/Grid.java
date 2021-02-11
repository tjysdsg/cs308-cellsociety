package Model;

import Model.Neighborhood.Direction;
import java.util.ArrayList;
import java.util.List;

/**
 * Cells are accessed by (r, c) coordinate, where r is the row index and c is the column index
 * axis.
 */
public class Grid {

  public int nRows;
  public int nCols;
  private List<List<Cell>> grid;
  private Neighborhood neighborhood;

  public Grid(int nRows, int nCols, State defaultState, Neighborhood neighborhood) {
    this.nRows = nRows;
    this.nCols = nCols;
    grid = new ArrayList<>(nRows);
    for (int i = 0; i < nRows; ++i) {
      ArrayList<Cell> row = new ArrayList<>(nCols);
      for (int j = 0; j < nCols; ++j) {
        row.add(new Cell(defaultState));
      }
      grid.add(row);
    }
    this.neighborhood = neighborhood;
  }

  /**
   * @param immediate Lazy write to the cell state at (r, c) if true, call update() to actually
   *                  change the value of all cells
   */
  public void setState(int r, int c, State state, boolean immediate) {
    grid.get(r).get(c).setState(state, immediate);
  }

  /**
   * Lazy write to a cell state
   *
   * @see Grid#setState(int, int, State, boolean)
   */
  public void setState(int r, int c, State state) {
    grid.get(r).get(c).setState(state, false);
  }

  public State getState(int r, int c) {
    return grid.get(r).get(c).getState();
  }

  public Cell getCell(int r, int c) {
    return grid.get(r).get(c);
  }

  public boolean update() {
    boolean ret = false;
    for (int r = 0; r < nRows; ++r) {
      for (int c = 0; c < nRows; ++c) {
        ret |= grid.get(r).get(c).update();
      }
    }
    return ret;
  }

  public List<Cell> getNeighborsOf(int r, int c) {
    ArrayList<Cell> ret = new ArrayList<>();
    boolean n = r > 0;
    boolean s = r < nRows - 1;
    boolean e = c < nCols - 1;
    boolean w = c > 0;

    if (neighborhood.isValidNeighbor(Direction.NORTH) && n) {
      ret.add(getCell(r - 1, c));
    }
    if (neighborhood.isValidNeighbor(Direction.SOUTH) && s) {
      ret.add(getCell(r + 1, c));
    }
    if (neighborhood.isValidNeighbor(Direction.EAST) && e) {
      ret.add(getCell(r, c + 1));
    }
    if (neighborhood.isValidNeighbor(Direction.WEST) && w) {
      ret.add(getCell(r, c - 1));
    }

    if (neighborhood.isValidNeighbor(Direction.NORTH_EAST) && n && e) {
      ret.add(getCell(r - 1, c + 1));
    }
    if (neighborhood.isValidNeighbor(Direction.NORTH_WEST) && n && w) {
      ret.add(getCell(r - 1, c - 1));
    }
    if (neighborhood.isValidNeighbor(Direction.SOUTH_EAST) && s && e) {
      ret.add(getCell(r + 1, c + 1));
    }
    if (neighborhood.isValidNeighbor(Direction.SOUTH_WEST) && s && w) {
      ret.add(getCell(r + 1, c - 1));
    }
    return ret;
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append("|");
    for (var row : grid) {
      int col = 0;
      for (var cell : row) {
        ret.append(cell.toString()).append("|");
        ++col;
      }
      ret.append("\n");
      if (col < nCols - 1) {
        ret.append("|");
      }
    }
    return ret.toString();
  }
}
