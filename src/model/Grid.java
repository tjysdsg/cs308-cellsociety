package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class of grid.
 */
public abstract class Grid {

  protected List<List<Cell>> grid;
  protected int nRows;
  protected int nCols;
  protected Neighborhood neighborhood;
  protected EdgeType edgeType = EdgeType.FINITE;

  public Grid(int nRows, int nCols, State defaultState) {
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
  }

  /**
   * @param immediate Lazy write to the cell state at (r, c) if true, call update() to actually
   *                  change the value of all cells
   */
  public void setState(int r, int c, State state, boolean immediate) {
    Vec2D coord = fixCoord(new Vec2D(r, c));
    grid.get(coord.getX()).get(coord.getY()).setState(state, immediate);
  }

  /**
   * Lazy write to a cell state
   *
   * @see GridSq4#setState(int, int, State, boolean)
   */
  public void setState(int r, int c, State state) {
    Vec2D coord = fixCoord(new Vec2D(r, c));
    grid.get(coord.getX()).get(coord.getY()).setState(state, false);
  }

  /**
   * Get state of cell located at (r, c)
   */
  public State getState(int r, int c) {
    return grid.get(r).get(c).getState();
  }

  /**
   * Get cell at (r, c)
   */
  public Cell getCell(int r, int c) {
    return grid.get(r).get(c);
  }

  /**
   * Update all states in the grid to the next generation
   */
  public void update() {
    for (int r = 0; r < getNumRows(); ++r) {
      for (int c = 0; c < getNumCols(); ++c) {
        grid.get(r).get(c).update();
      }
    }
  }

  /**
   * Set the edge type of the grid.
   *
   * @see EdgeType
   */
  public void setEdgeType(EdgeType edgeType) {
    this.edgeType = edgeType;
  }

  protected Vec2D wrapAroundCoord(Vec2D coord) {
    return new Vec2D(
        Utils.wrapInt(coord.getX(), 0, nRows - 1),
        Utils.wrapInt(coord.getY(), 0, nCols - 1)
    );
  }

  /**
   * Convert a coordinate to a valid one.
   * <p>
   * Returns as is if not possible.
   */
  public Vec2D fixCoord(Vec2D coord) {
    if (edgeType == EdgeType.WRAP) {
      wrapAroundCoord(coord);
    } else if (edgeType == EdgeType.INFINITE) {
      // TODO: expand grid
    }
    return coord;
  }

  /**
   * Get a list of coordinates that contains forward neighbors of the cell at (r, c)
   */
  public List<Vec2D> getForwardNeighborsCoord(int r, int c, Vec2D forwardDirection) {
    ArrayList<Vec2D> ret = new ArrayList<>();
    Vec2D coord = new Vec2D(r, c);

    // TODO: infinite edge type
    for (Vec2D delta : Neighborhood.ALL_NEIGHBOR_DIRECTIONS) {
      // check if forward using cosine
      if (delta.cosAngle(forwardDirection) < 0) {
        continue;
      }

      Vec2D newCoord = coord.add(delta);
      if (edgeType == EdgeType.WRAP) { // toroidal
        newCoord = wrapAroundCoord(newCoord);
        if (neighborhood.isValidNeighborDirection(delta)) {
          ret.add(newCoord);
        }
      } else { // finite
        if (isInside(newCoord.getX(), newCoord.getY())
            && neighborhood.isValidNeighborDirection(delta)) {
          ret.add(newCoord);
        }
      }
    }
    return ret;
  }

  /**
   * Get a list of coordinates that contains neighbors of the cell at (r, c)
   */
  public List<Vec2D> getNeighborsCoord(int r, int c) {
    ArrayList<Vec2D> ret = new ArrayList<>();
    Vec2D coord = new Vec2D(r, c);

    // TODO: infinite edge type
    for (Vec2D delta : Neighborhood.ALL_NEIGHBOR_DIRECTIONS) {
      Vec2D newCoord = coord.add(delta);
      if (edgeType == EdgeType.WRAP) { // toroidal
        newCoord = wrapAroundCoord(newCoord);
        if (neighborhood.isValidNeighborDirection(delta)) {
          ret.add(newCoord);
        }
      } else { // finite
        if (isInside(newCoord.getX(), newCoord.getY())
            && neighborhood.isValidNeighborDirection(delta)) {
          ret.add(newCoord);
        }
      }
    }
    return ret;
  }

  /**
   * Get a list of cells that are neighbors of the cell at (r, c)
   */
  public List<Cell> getNeighborsOf(int r, int c) {
    ArrayList<Cell> ret = new ArrayList<>();
    Vec2D coord = new Vec2D(r, c);

    // TODO: infinite edge type
    for (Vec2D delta : Neighborhood.ALL_NEIGHBOR_DIRECTIONS) {
      Vec2D newCoord = coord.add(delta);
      if (edgeType == EdgeType.WRAP) { // toroidal
        newCoord = wrapAroundCoord(newCoord);
        if (neighborhood.isValidNeighborDirection(delta)) {
          ret.add(getCell(newCoord.getX(), newCoord.getY()));
        }
      } else { // finite
        if (isInside(newCoord.getX(), newCoord.getY())
            && neighborhood.isValidNeighborDirection(delta)) {
          ret.add(getCell(newCoord.getX(), newCoord.getY()));
        }
      }
    }
    return ret;
  }

  /**
   * Set which neighborhood ruleset to use
   *
   * @see Neighborhood
   */
  public void setNeighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }

  /**
   * Get a string representation of the grid
   */
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

  /**
   * Get the total number of rows
   */
  public int getNumRows() {
    return nRows;
  }

  /**
   * Set the total number of rows
   */
  public void setNumRows(int nRows) {
    this.nRows = nRows;
  }

  /**
   * Get the total number of columns
   */
  public int getNumCols() {
    return nCols;
  }

  /**
   * Set the total number of columns
   */
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
