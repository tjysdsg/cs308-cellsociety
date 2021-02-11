package Model;

/**
 * DEAD, ALIVE
 */
public enum StateGOL implements State {

  DEAD, ALIVE;

  @Override
  public String toString() {
    return switch (this) {
      case DEAD -> " ";
      case ALIVE -> "\u25A0";
    };
  }
}
