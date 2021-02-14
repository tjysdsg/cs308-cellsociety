package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.paint.Color;

public class sample_controller {
    public static int getnumRow(String file){
      return 10;
    }
    public static int getnumCol(String file){
      return 10;
    }

  /**
   *
   * @param file
   * @return assuming only 2 states.
   */
  public static int getnumStates(String file){
      return 2;
    }

  /**
   *
   * @param file, the config file
   * @return key value pairs are statistics and their names, for example, alive: 30, dead:25
   */
    public static HashMap<String, Integer> getStatisticsMap(String file){
      HashMap<String,Integer> temp = new HashMap<>();
      return temp;
    }

  /**
   *
   * @param file, config file
   * @return, double arrays of grid cells. Best use integer value to indicate dead or alive or otherwise.
   */

  public static ArrayList<ArrayList<Integer>> getGrid(String file){
    Random rand = new Random();
      ArrayList<ArrayList<Integer>> doublearray = new ArrayList<>();
      for (int i=0; i<getnumRow(file); i++){
        ArrayList<Integer> singlearray = new ArrayList<>();
        for (int j=0; j<getnumCol(file); j++){
          int n = rand.nextInt(getnumStates(file));
          singlearray.add(n);
        }
        doublearray.add(singlearray);
      }
      return doublearray;
    }
}
