package model;

/**
 * Grid that has cells with hexagon shape
 * <p>
 * Cells are accessed by (r, c) coordinate, where r is the row index and c is the column index
 * axis.
 */
public class GridHex extends Grid {

  public GridHex(int nRows, int nCols, State defaultState) {
    super(nRows, nCols, defaultState);
    setNeighborhood(Neighborhood.Hex());
  }
}
