package model;

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
    Vec2D coord = new Vec2D(r, c);

    for (Vec2D delta : Neighborhood.ALL_NEIGHBOR_DIRECTIONS) {
      Vec2D newCoord = coord.add(delta);
      if (isInside(newCoord.getX(), newCoord.getY()) && neighborhood.isValidNeighbor(delta)) {
        ret.add(getCell(newCoord.getX(), newCoord.getY()));
      }
    }
    return ret;
  }
}
