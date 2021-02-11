package Model;

/**
 * EMPTY, TREE, BURNING
 */
public enum StateFire implements State {

  EMPTY, TREE, BURNING;

  @Override
  public String toString() {
    return switch (this) {
      case EMPTY -> "\u3000"; // space with the same width as emoji
      case TREE -> "\uD83C\uDF32";
      case BURNING -> "\uD83D\uDD25";
    };
  }
}
