package view;

import java.util.ResourceBundle;

public class LabelResource {

  private ResourceBundle labelsBundle;

  public LabelResource(String langName) {
    labelsBundle = ResourceBundle.getBundle(langName);
  }

  public String getString(String key) {
    return labelsBundle.getString(key);
  }

}
