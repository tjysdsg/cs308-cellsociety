package model;

/**
 * EMPTY = 0, SHARK = 1, FISH = 2
 * <p>
 * Please ignore MOVED_SHARK and MOVED_FISH as they are only used internally by Simulation.
 */
public enum StateWaTor implements StateEnum {

  EMPTY, SHARK, FISH,
  MOVED_SHARK, MOVED_FISH;

  public static final int EMPTY_VAL = 0;
  public static final int SHARK_VAL = 1;
  public static final int FISH_VAL = 2;
  private static final int MOVED_SHARK_VAL = 3;
  private static final int MOVED_FISH_VAL = 4;
  public static final int[] ALL_VALS = new int[]{0, 1, 2};

  public int nDaysBreed = 0;
  public int nDaysStarve = 0;

  public static StateWaTor fromInt(int val) {
    return switch (val) {
      case SHARK_VAL -> SHARK;
      case FISH_VAL -> FISH;
      default -> EMPTY;
    };
  }

  public void setMoved(boolean moved) {
    if (val == FISH_VAL && moved) {
      val = MOVED_FISH_VAL;
    } else if (val == SHARK_VAL && moved) {
      val = MOVED_SHARK_VAL;
    } else if (val == MOVED_FISH_VAL && !moved) {
      val = FISH_VAL;
    } else if (val == MOVED_SHARK_VAL && !moved) {
      val = SHARK_VAL;
    }
  }

  @Override
  public String toString() {
    if (this == SHARK || this == MOVED_SHARK) {
      return "\uD83E\uDD88"; // shark emoji
    } else if (this == FISH || this == MOVED_FISH) {
      return "\uD83D\uDC1F"; // fish emoji
    } else {
      return "\u3000"; // empty space with same width as an emoji
    }
  }
}
