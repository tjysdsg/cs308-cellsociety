package model;

import java.util.Objects;

/**
 * 2D Vector
 */
public class Vec2D {

  private int x = 0;
  private int y = 0;

  public Vec2D() {
  }

  public Vec2D(int x, int y) {
    this.set(x, y);
  }

  /**
   * Copy constructor
   */
  public Vec2D(Vec2D other) {
    this.x = other.x;
    this.y = other.y;
  }

  /**
   * Get x coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * Get y coordinate
   */
  public int getY() {
    return y;
  }

  /**
   * Set x coordinate
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Set y coordinate
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Set x and y coordinate
   */
  public void set(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Element-wise addition
   */
  public Vec2D add(Vec2D v) {
    Vec2D ret = new Vec2D(this);
    ret.x += v.x;
    ret.y += v.y;
    return ret;
  }

  /**
   * Element-wise subtraction
   */
  public Vec2D minus(Vec2D v) {
    Vec2D ret = new Vec2D(this);
    ret.x -= v.x;
    ret.y -= v.y;
    return ret;
  }

  public double dot(Vec2D other) {
    return (double) x * other.x + (double) y * other.y;
  }

  public double magnitude() {
    return Math.sqrt(dot(this));
  }

  public double cosAngle(Vec2D other) {
    return dot(other) / (magnitude() * other.magnitude());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vec2D vec2D = (Vec2D) o;
    return x == vec2D.x && y == vec2D.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  /**
   * Convert to string representation for easier printing and debugging
   */
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
