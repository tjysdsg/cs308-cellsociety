package model;

public class StateSugar extends State {

  private int sugar;
  private int sugarGrowTime = 0;
  private SugarAgent agent;

  /**
   * Copy constructor
   */
  public StateSugar(StateSugar other) {
    super(other.stateType);
    this.sugar = other.sugar;
    this.sugarGrowTime = other.sugarGrowTime;
    this.agent = other.agent;
  }

  /**
   * Constructor
   *
   * @param stateType see {@link State#State(StateEnum)}
   * @param sugar     current available sugar in a cell
   * @param agent     {@link SugarAgent}
   */
  public StateSugar(StateEnumSugar stateType, int sugar, SugarAgent agent) {
    super(stateType);

    if (stateType == StateEnumSugar.AGENT) {
      this.agent = agent;
    }

    this.sugar = sugar;
  }

  public int getSugar() {
    return sugar;
  }

  public void setSugar(int sugar) {
    this.sugar = sugar;
  }

  public int getSugarGrowTime() {
    return sugarGrowTime;
  }

  public void setSugarGrowTime(int sugarGrowTime) {
    this.sugarGrowTime = sugarGrowTime;
  }

  public SugarAgent getAgent() {
    if (stateType == StateEnumSugar.AGENT) {
      return agent;
    }
    return null;
  }

  public void setAgent(SugarAgent agent) {
    this.agent = agent;
    stateType = StateEnumSugar.AGENT;
  }

  public void removeAgent() {
    stateType = StateEnumSugar.EMPTY;
    this.agent = null;
  }

  @Override
  public String toString() {
    if (stateType == StateEnumSugar.AGENT) {
      return "" + stateType;
    }
    return "" + sugar;
  }
}
