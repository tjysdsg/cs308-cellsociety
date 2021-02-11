package Model;

/**
 * BLOCKED, OPEN, PERCOLATED
 */
public enum StatePercolation implements State {

  BLOCKED, OPEN, PERCOLATED;

  @Override
  public String toString() {
    return switch (this) {
      case BLOCKED -> "\u25A0";
      case OPEN -> " ";
      case PERCOLATED -> "~";
    };
  }
}
