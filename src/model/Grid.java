package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Grid {

  protected List<List<Cell>> grid;
  protected int nRows;
  protected int nCols;
  protected Neighborhood neighborhood;

  public Grid(int nRows, int nCols, State defaultState, Neighborhood neighborhood) {
    this.setNumRows(nRows);
    this.setNumCols(nCols);
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
   * @see GridSq#setState(int, int, State, boolean)
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

  public void update() {
    for (int r = 0; r < getNumRows(); ++r) {
      for (int c = 0; c < getNumCols(); ++c) {
        grid.get(r).get(c).update();
      }
    }
  }

  public List<Cell> getNeighborsOf(int r, int c) {
    ArrayList<Cell> ret = new ArrayList<>();
    Vec2D coord = new Vec2D(r, c);

    for (Vec2D delta : Neighborhood.ALL_NEIGHBOR_DIRECTIONS) {
      Vec2D newCoord = coord.add(delta);
      if (isInside(newCoord.getX(), newCoord.getY()) && neighborhood.isValidNeighbor(delta)) {
        ret.add(getCell(newCoord.getX(), newCoord.getY()));
      }
    }
    return ret;
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append("|");
    int r = 0;
    for (var row : grid) {
      for (var cell : row) {
        ret.append(cell.toString()).append("|");
      }
      ++r;
      ret.append("\n");
      if (r <= getNumRows() - 1) {
        ret.append("|");
      }
    }
    return ret.toString();
  }

  public int getNumRows() {
    return nRows;
  }

  public void setNumRows(int nRows) {
    this.nRows = nRows;
  }

  public int getNumCols() {
    return nCols;
  }

  public void setNumCols(int nCols) {
    this.nCols = nCols;
  }

  /**
   * Check if (r, c) is inside the grid
   */
  public boolean isInside(int r, int c) {
    return r >= 0
        && r < nRows
        && c < nCols
        && c >= 0;
  }
}
