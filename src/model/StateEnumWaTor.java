package model;

/**
 * EMPTY = 0, SHARK = 1, FISH = 2
 * <p>
 * Please ignore MOVED_SHARK and MOVED_FISH as they are only used internally by Simulation.
 */
public enum StateEnumWaTor implements StateEnum {

  EMPTY, SHARK, FISH;

  public static final int EMPTY_VAL = 0;
  public static final int SHARK_VAL = 1;
  public static final int FISH_VAL = 2;
  public static final int[] ALL_VALS = new int[]{0, 1, 2};

  public static StateEnumWaTor fromInt(int val) {
    return switch (val) {
      case SHARK_VAL -> SHARK;
      case FISH_VAL -> FISH;
      default -> EMPTY;
    };
  }

  @Override
  public int toInteger() {
    if (this == EMPTY) {
      return EMPTY_VAL;
    } else if (this == SHARK) {
      return SHARK_VAL;
    } else {
      return FISH_VAL;
    }
  }

  @Override
  public String toString() {
    if (this == SHARK) {
      return "\uD83E\uDD88"; // shark emoji
    } else if (this == FISH) {
      return "\uD83D\uDC1F"; // fish emoji
    } else {
      return "\u3000"; // empty space with same width as an emoji
    }
  }
}
