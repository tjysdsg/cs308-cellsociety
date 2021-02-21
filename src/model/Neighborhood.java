package model;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the ruleset of determining whether a group of cells are neighbors.
 * <p>
 * Whether a cell B is the neighbor of a cell A is determined by the direction of B relative to A.
 * There are several presets that defines valid directions between neighbors.
 */
public class Neighborhood {

  /**
   * Possible neighbor directions of a square grid with 4 neighbors
   * <p>
   * The values are delta(r, c) = neighbor - self
   */
  private static final Vec2D[] SQUARE_4_NEIGHBOR_DIRECTIONS = new Vec2D[]{
      new Vec2D(1, 0),
      new Vec2D(-1, 0),
      new Vec2D(0, 1),
      new Vec2D(0, -1),
  };

  /**
   * Possible neighbor directions of a square grid with 8 neighbors
   * <p>
   * The values are delta(r, c) = neighbor - self
   */
  private static final Vec2D[] SQUARE_8_NEIGHBOR_DIRECTIONS = new Vec2D[]{
      new Vec2D(1, 0),
      new Vec2D(-1, 0),
      new Vec2D(0, 1),
      new Vec2D(0, -1),
      new Vec2D(1, 1),
      new Vec2D(1, -1),
      new Vec2D(-1, 1),
      new Vec2D(-1, -1),
  };

  /**
   * Possible neighbor directions of a hex grid
   * <p>
   * The values are delta(r, c) = neighbor - self
   */
  private static final Vec2D[] HEX_NEIGHBOR_DIRECTIONS = new Vec2D[]{
      new Vec2D(1, 0),
      new Vec2D(-1, 0),
      new Vec2D(0, 1),
      new Vec2D(0, -1),
      new Vec2D(1, 1),
      new Vec2D(1, -1),
  };

  /**
   * Possible neighbor directions of a triangle grid
   * <p>
   * The values are delta(r, c) = neighbor - self
   */
  private static final Vec2D[] TRI_NEIGHBOR_DIRECTIONS = new Vec2D[]{
      new Vec2D(1, 0),
      new Vec2D(-1, 0),
      new Vec2D(0, 1),
      new Vec2D(0, -1),
      new Vec2D(2, 0),
      new Vec2D(-2, 0),
      new Vec2D(0, 2),
      new Vec2D(0, -2),
      new Vec2D(1, 1),
      new Vec2D(1, -1),
      new Vec2D(-1, 1),
      new Vec2D(-1, -1),
  };

  /**
   * All possible neighbor directions
   * <p>
   * The values are delta(r, c) = neighbor - self
   */
  public static final Vec2D[] ALL_NEIGHBOR_DIRECTIONS = new Vec2D[]{
      new Vec2D(1, 0),
      new Vec2D(-1, 0),
      new Vec2D(0, 1),
      new Vec2D(0, -1),
      new Vec2D(2, 0),
      new Vec2D(-2, 0),
      new Vec2D(0, 2),
      new Vec2D(0, -2),
      new Vec2D(1, 1),
      new Vec2D(1, -1),
      new Vec2D(-1, 1),
      new Vec2D(-1, -1),
  };

  private List<Vec2D> validDirections;

  public Neighborhood(Vec2D[] directions) {
    this.validDirections = Arrays.asList(directions);
  }

  /**
   * Creates a neighborhood of a grid with hexagon cells.
   * <p>
   * TODO: make graphs in doc/
   */
  public static Neighborhood Hex() {
    return new Neighborhood(HEX_NEIGHBOR_DIRECTIONS);
  }

  /**
   * Creates a neighborhood of a grid with triangle cells.
   * <p>
   * TODO: make graphs in doc/
   */
  public static Neighborhood Triangle() {
    return new Neighborhood(TRI_NEIGHBOR_DIRECTIONS);
  }

  /**
   * Creates a neighborhood of a grid with square cells, and cells from north, south, east, or west
   * are valid neighbors.
   */
  public static Neighborhood Square4() {
    return new Neighborhood(SQUARE_4_NEIGHBOR_DIRECTIONS);
  }

  /**
   * Creates a neighborhood of a grid with square cells, and cells from north, south, east, west,
   * northeast, northwest, southeast, or southwest directions are valid neighbors.
   */
  public static Neighborhood Square8() {
    return new Neighborhood(SQUARE_8_NEIGHBOR_DIRECTIONS);
  }

  /**
   * Check if a direction from B relative to A is valid for B to be A's neighbor.
   */
  public boolean isValidNeighborDirection(Vec2D direction) {
    return validDirections.contains(direction);
  }
}
