package model;

/**
 * Grid that has cells with triangle shape
 * <p>
 * Cells are accessed by (r, c) coordinate, where r is the row index and c is the column index
 * axis.
 */
public class GridTriangle extends Grid {

  /**
   * @see Grid#Grid(int, int, State)
   */
  public GridTriangle(int nRows, int nCols, State defaultState) {
    super(nRows, nCols, defaultState);
    setNeighborhood(Neighborhood.Triangle());
  }
}
