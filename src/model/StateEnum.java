package model;

public interface StateEnum {

  /**
   * Factory function for creating a StateEnum from an integer
   */
  static StateEnum fromInt(int val) {
    return null;
  }

  /**
   * Convert a StateEnum to an integer
   */
  int toInteger();
}
