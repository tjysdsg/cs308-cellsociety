package model;

public class StateSugar extends State {

  private int sugar = 0;
  private int sugarGrowTime = 0;
  private SugarAgent agent;

  public StateSugar(StateEnumSugar stateType, int sugar) {
    super(stateType);

    if (stateType == StateEnumSugar.AGENT) {
      agent = new SugarAgent();
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
