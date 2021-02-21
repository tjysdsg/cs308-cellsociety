package model;

/**
 * Grid that has cells with square shape and 4 neighbors.
 * <p>
 * Cells are accessed by (r, c) coordinate, where r is the row index and c is the column index
 * axis.
 */
public class GridSq4 extends Grid {

  public GridSq4(int nRows, int nCols, State defaultState) {
    super(nRows, nCols, defaultState);
    setNeighborhood(Neighborhood.Square4());
  }
}
