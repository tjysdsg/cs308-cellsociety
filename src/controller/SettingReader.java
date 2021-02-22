package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SettingReader {
  public String language;
  public String gridType;
  public String sizeOfGrid; // for scrolling
  public String gridOutline;
  public String colorTheme;
  public String shapeOfCell;

  public SettingReader(String filename) throws IOException {
    FileReader reader= new FileReader("data/"+filename);
    BufferedReader bf =new BufferedReader(reader);
    String allConfig= bf.readLine();
    String[] configs= allConfig.split(",");
    language=configs[0];
    gridType=configs[1];
    sizeOfGrid=configs[2];
    gridOutline=configs[3];
    colorTheme=configs[4];
    shapeOfCell=configs[5];
  }
}
