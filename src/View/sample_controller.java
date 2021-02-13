package View;

import java.util.ArrayList;
import java.util.HashMap;

public class sample_controller {
    public static int getnumRow(String file){
      return 10;
    }
    public static int getnumCol(String file){
      return 10;
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
}
