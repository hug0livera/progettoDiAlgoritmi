package usageDistance;

import distance.*;
import java.util.*;
import java.io.*;
import java.lang.*;

public class UsageDistance {

  /**
   * read the file correctMe.txt and copy it in an ArrayList
   * 
   * @param inputFile the path of the file to be read (correctMe.txt)
   * @param array     the arrayList where i copy the file correctMe.txt
   */
  public static void loadFile(String inputFile, ArrayList<String> array) {
    String line = "";
    String[] words;
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      System.out.println("\nFile found");
      System.out.println("Loading file... \"CorrectMe\"");
      while ((line = reader.readLine()) != null) {
        line = line.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        words = line.split(" ");
        for (int i = 0; i < words.length; i++)
          array.add(words[i]);
      }
      reader.close();
    } catch (IOException ex) {
      System.out.println("File not found");
    }
  }

  /**
   * Read the file dictionary.txt and copy it in an ArrayList
   * 
   * @param inputFile the path of the file to be read (dictionary.txt)
   * @param array     the arrayList where i copy the file dictionary.txt
   */
  public static void loadFile(ArrayList<String> array, String inputFile) {
    String line = "";
    String[] words;
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      System.out.println("Loading file... \"Dictionary\"");
      while ((line = reader.readLine()) != null) {
        words = line.split("\n");
        for (int i = 0; i < words.length; i++)
          array.add(words[i]);
      }
      reader.close();
    } catch (Exception ex) {
      System.out.println("File not found");
    }
  }

  /**
   * There are two arrayLists, one that contains the dictionary and the other one
   * contains the words that must be corrected. For every word of the string
   * arrayCorrectMe i find the minimum distance with all the words of the
   * arrayDictionary, using the EditDistanceDyn class
   * 
   * @param arrayCorrectMe  dictionary, list the italian words
   * @param arrayDictionary list the words with quote of Joinh Lennon
   */
  public static void editDistanceApplication(ArrayList<String> arrayCorrectMe, ArrayList<String> arrayDictionary) {
    long startTime = System.nanoTime();
    EditDistanceDyn e;
    // EditDistance e2;
    int result = 0, min = Integer.MAX_VALUE;
    ArrayList<String> aux = new ArrayList<String>();

    for (int j = 0; j < arrayCorrectMe.size(); j++) {
      for (int i = 0; i < arrayDictionary.size(); i++) {
        e = new EditDistanceDyn();
        result = e.min_distance_dyn(arrayCorrectMe.get(j), arrayDictionary.get(i));
        // e2 = new EditDistance();
        // result = e2.edit_distance(arrayCorrectMe.get(j), arrayDictionary.get(i),
        // arrayCorrectMe.get(j).length(), arrayDictionary.get(i).length());

        if (result < min) {
          min = result;
          aux.clear();
          aux.add(arrayDictionary.get(i));
        } else if (result == min)
          aux.add(arrayDictionary.get(i));
      }
      System.out.println("\nThe word is  :  " + arrayCorrectMe.get(j) + " \nMinimum distance : " + min);
      printArray(aux);
      aux.clear();
      min = Integer.MAX_VALUE;
    }

    long endTime = System.nanoTime();
    long totalTime = endTime - startTime;
    System.out.println("\nTotal time is:" + totalTime / 1000000000 + " seconds");
  }

  public static void printArray(ArrayList<String> array) {
    for (String arr : array)
      System.out.println("Words with minimal distance :  " + arr);
  }
}