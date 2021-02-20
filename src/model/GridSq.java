package model;

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
}
