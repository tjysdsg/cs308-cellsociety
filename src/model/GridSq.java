package model;

import model.Neighborhood.Direction;
import java.util.ArrayList;
import java.util.List;

/**
 * Grid that has cells with square shape
 * <p>
 * Cells are accessed by (r, c) coordinate, where r is the row index and c is the column index
 * axis.
 */
public class GridSq extends Grid {

  public GridSq(int nRows, int nCols, State defaultState, Neighborhood neighborhood) {
    super(nRows, nCols, defaultState, neighborhood);
  }

  @Override
  public void update() {
    for (int r = 0; r < getNumRows(); ++r) {
      for (int c = 0; c < getNumCols(); ++c) {
        grid.get(r).get(c).update();
      }
    }
  }

  public List<Cell> getNeighborsOf(int r, int c) {
    ArrayList<Cell> ret = new ArrayList<>();
    boolean n = r > 0;
    boolean s = r < getNumRows() - 1;
    boolean e = c < getNumCols() - 1;
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
}
