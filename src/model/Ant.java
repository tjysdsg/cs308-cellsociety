package model;

/**
 * Represents an ant in Foraging Ant
 */
public class Ant {

  /**
   * Does the ant have food
   */
  private boolean hasFood = false;

  /**
   * The orientation of the ant
   */
  private Vec2D orientation = new Vec2D();

  public boolean hasFood() {
    return hasFood;
  }

  public void setHasFood(boolean hasFood) {
    this.hasFood = hasFood;
  }

  public Vec2D getOrientation() {
    return orientation;
  }

  public void setOrientation(Vec2D orientation) {
    this.orientation = orientation;
  }
}
