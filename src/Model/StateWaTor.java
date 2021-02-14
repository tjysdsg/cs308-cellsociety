package Model;

/**
 *
 */
public class StateWaTor extends State {

  public static final int EMPTY_VAL = 0;
  public static final int SHARK_VAL = 1;
  public static final int FISH_VAL = 2;
  public static final int MOVED_SHARK_VAL = 3;
  public static final int MOVED_FISH_VAL = 4;

  public static StateWaTor EMPTY() {
    return new StateWaTor(EMPTY_VAL);
  }

  public static StateWaTor SHARK() {
    return new StateWaTor(SHARK_VAL);
  }

  public static StateWaTor FISH() {
    return new StateWaTor(FISH_VAL);
  }

  public static StateWaTor MOVED_SHARK() {
    return new StateWaTor(MOVED_SHARK_VAL);
  }

  public static StateWaTor MOVED_FISH() {
    return new StateWaTor(MOVED_FISH_VAL);
  }

  public int nDaysBreed = 0;
  public int nDaysStarve = 0;

  StateWaTor(int val) {
    super(val);
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
    if (this.val == SHARK_VAL || this.val == MOVED_SHARK_VAL) {
      return "\uD83E\uDD88";
    } else if (this.val == FISH_VAL || this.val == MOVED_FISH_VAL) {
      return "\uD83D\uDC1F";
    } else {
      return "\u3000";
    }
  }
}
