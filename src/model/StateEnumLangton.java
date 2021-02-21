package model;

/**
 * State 0 to 7
 */
public class StateEnumLangton implements StateEnum {


  public static final int[] ALL_VALS = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

  private int val = 0;

  public StateEnumLangton() {
  }

  public static StateEnumLangton fromInt(int val) {
    StateEnumLangton ret = new StateEnumLangton();
    ret.val = val;
    return ret;
  }

  @Override
  public int toInteger() {
    return val;
  }

  @Override
  public String toString() {
    return Integer.toString(val);
  }
}
