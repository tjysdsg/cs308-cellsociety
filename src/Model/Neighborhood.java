package Model;

import java.util.Arrays;
import java.util.List;

public class Neighborhood {

  enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTH_EAST,
    NORTH_WEST,
    SOUTH_EAST,
    SOUTH_WEST,
  }

  private List<Direction> validDirections;

  public Neighborhood(Direction[] directions) {
    this.validDirections = Arrays.asList(directions);
  }

  /**
   * Cells from north, south, east, or west are valid neighbors.
   */
  public static Neighborhood Preset4() {
    return new Neighborhood(
        new Direction[]{
            Direction.NORTH,
            Direction.SOUTH,
            Direction.EAST,
            Direction.WEST,
        });
  }

  /**
   * Cells from all directions are valid neighbors.
   */
  public static Neighborhood Preset8() {
    return new Neighborhood(
        new Direction[]{
            Direction.NORTH,
            Direction.SOUTH,
            Direction.EAST,
            Direction.WEST,
            Direction.NORTH_EAST,
            Direction.NORTH_WEST,
            Direction.SOUTH_EAST,
            Direction.SOUTH_WEST,
        });
  }

  public boolean isValidNeighbor(Direction direction) {
    return validDirections.contains(direction);
  }
}
