package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import view.ControllableParam;


/**
 * Reader of the setting.
 */
public class SettingReader {
  private List<ControllableParam> settings;


  public SettingReader(String filename) throws IOException {
    settings= new ArrayList<>();
    FileReader reader= new FileReader("data/simulationControl/"+filename);
    BufferedReader bf =new BufferedReader(reader);
    String allConfig= bf.readLine();
    String[] configs= allConfig.split(",");
    for(int i=0;i<configs.length/5;i++){
      String name=configs[i*5];
      Object low;
      Object high;
      Object stepSize;
      Object curVal;
      String type=configs[i*5+4];
      if(type.equals("Integer")) {
        low = Integer.parseInt(configs[i *5+ 1]);
        high = Integer.parseInt(configs[i*5+2]);
        stepSize= Integer.parseInt(configs[i*5+3]);
        curVal = 0;
      }
      else{
        low = Double.parseDouble(configs[i*5 + 1]);
        high = Double.parseDouble(configs[i*5+2]);
        stepSize= Double.parseDouble(configs[i*5+3]);
        curVal = 0.0;
      }
      ControllableParam param= new ControllableParam(low,high,curVal,stepSize,name,type);
      settings.add(param);
    }
  }

  public List<ControllableParam> getSettings() {
    return settings;
  }

  public static void main(String[] args) throws IOException {
    SettingReader a = new SettingReader("GOL");

    return;

  }
}
