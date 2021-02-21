package model;

/**
 * Grid that has cells with square shape and 8 neighbors.
 * <p>
 * Cells are accessed by (r, c) coordinate, where r is the row index and c is the column index
 * axis.
 */
public class GridSq8 extends Grid {

  public GridSq8(int nRows, int nCols, State defaultState) {
    super(nRows, nCols, defaultState);
    setNeighborhood(Neighborhood.Square8());
  }
}
