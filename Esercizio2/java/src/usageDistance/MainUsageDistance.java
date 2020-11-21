package usageDistance;

import usageDistance.UsageDistance;
import java.io.IOException;
import java.util.ArrayList;

public class MainUsageDistance{
  public static void main(String[] args)throws Exception, IOException{
    if(args.length < 1)
      throw new Exception("UsageDistance: <file_name1> <file_name2>");
    ArrayList<String> arrayCorrectMe = new ArrayList<String>();
    ArrayList<String> arrayDictionary = new ArrayList<String>();
    UsageDistance.loadFile(args[0], arrayCorrectMe);
    UsageDistance.loadFile(arrayDictionary, args[1]);
    UsageDistance.editDistanceApplication(arrayCorrectMe, arrayDictionary);
  }
}

