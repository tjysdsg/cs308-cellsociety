package view;

public class ControllableParam {
    private Object min_val;
    private Object max_val;
    private Object current_val;
    private Object amount_to_step_by;
    private String name;
    private String type;

  public ControllableParam(Object min_val, Object max_val, Object current_val, Object amount_to_step_by,
      String name, String type) {
    this.min_val = min_val;
    this.max_val = max_val;
    this.current_val = current_val;
    this.amount_to_step_by = amount_to_step_by;
    this.name = name;
    this.type = type;
  }

  public Object getAmount_to_step_by(){
    return this.amount_to_step_by;
  }

  public String getType(){
    return this.type;
  }

  public String getName(){
    return this.name;
  }

  public Object getMinVal(){
    return this.min_val;
  }

  public Object getMaxVal(){
    return this.max_val;
  }

  public Object getCurrVal(){
    return this.current_val;
  }

  public void setCurrent_val(Object o){
    this.current_val = o;
  }


}
