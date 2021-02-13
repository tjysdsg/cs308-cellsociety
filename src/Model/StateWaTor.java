package Model;

/**
 *
 */
public class StateWaTor extends State {

  public static final StateWaTor EMPTY = new StateWaTor(0);
  public static final StateWaTor SHARK = new StateWaTor(1);
  public static final StateWaTor FISH = new StateWaTor(2);
  public static final StateWaTor MOVED_SHARK = new StateWaTor(3);
  public static final StateWaTor MOVED_FISH = new StateWaTor(4);
  public int nDaysBreed = 0;
  public int nDaysStarve = 0;

  StateWaTor(int val) {
    super(val);
  }

  public void setMoved(boolean moved) {
    if (val == FISH.val && moved) {
      val = MOVED_FISH.val;
    } else if (val == SHARK.val && moved) {
      val = MOVED_SHARK.val;
    } else if (val == MOVED_FISH.val && !moved) {
      val = FISH.val;
    } else if (val == MOVED_SHARK.val && !moved) {
      val = SHARK.val;
    }
  }

  @Override
  public String toString() {
    if (this.equals(SHARK) || this.equals(MOVED_SHARK)) {
      return "\uD83E\uDD88";
    } else if (this.equals(FISH) || this.equals(MOVED_FISH)) {
      return "\uD83D\uDC1F";
    } else {
      return "\u3000";
    }
  }
}
