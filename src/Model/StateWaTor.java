package Model;

/**
 * DEAD, ALIVE
 */
public enum StateWaTor implements State {

  SHARK, FISH, MOVED_FISH, EMPTY;

  public int nDaysAlive = 0;

  @Override
  public String toString() {
    return switch (this) {
      case EMPTY -> "\u3000";
      case SHARK -> "\uD83E\uDD88";
      case FISH -> "\uD83D\uDC1F";
      case MOVED_FISH -> "\uD83D\uDC1F";
    };
  }
}
