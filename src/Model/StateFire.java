package Model;

/**
 * EMPTY, TREE, BURNING
 */
public enum StateFire implements State {

  EMPTY, TREE, BURNING;

  @Override
  public String toString() {
    return switch (this) {
      case EMPTY -> "E";
      case TREE -> "T";
      case BURNING -> "B";
    };
  }
}
