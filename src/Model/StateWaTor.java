package Model;

/**
 *
 */
public class StateWaTor extends State {

  public static final StateWaTor EMPTY = new StateWaTor(0);
  public static final StateWaTor SHARK = new StateWaTor(1);
  public static final StateWaTor FISH = new StateWaTor(2);
  public static final StateWaTor MOVED_FISH = new StateWaTor(2);

  StateWaTor(int val) {
    super(val);
  }

  public int nDaysAlive = 0;

  @Override
  public String toString() {
    if (this == SHARK) {
      return "\uD83E\uDD88";
    } else if (this == FISH || this == MOVED_FISH) {
      return "\uD83D\uDC1F";
    } else {
      return "\u3000";
    }
  }
}
