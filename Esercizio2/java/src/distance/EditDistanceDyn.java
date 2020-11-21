package distance;

import java.util.Arrays;

public class EditDistanceDyn {
  /**
   * @author Nadim
   *@author victor
   * @param insertion   is the operation where to insert characters
   * @param cancelation is the operation where to cancel characters
   * @param replace     is the operation where to replace/substitute characters
   * @return the minimum between the 3 operations done to calculate the edit
   *         distance
   */

  private int min(int insertion, int cancelation, int replace) {
    if (insertion <= cancelation && insertion <= replace)
      return insertion;
    if (cancelation <= insertion && cancelation <= replace)
      return cancelation;
    else
      return replace;
  }

  /**
   * 
   * @param s1 is the first string
   * @param s2 is the second string
   * @return the minimum edit distance it takes the second string to be like the
   *         first one
   */
  public int min_distance_dyn(String s1, String s2) {
    if (s1 == null || s2 == null)
      throw new IllegalArgumentException();
    int len1 = s1.length();
    int len2 = s2.length();
    int[][] temp = new int[len1][len2];
    for (int[] arr : temp)
      Arrays.fill(arr, -1);

    return calc_distance_dyn(s1, s2, temp, len1 - 1, len2 - 1);
  }

  /**
   * 
   * @param s1   is the first string
   * @param s2   is the second string
   * @param temp is a matrix to compare the 2 strings
   * @param i    is the length of the first string (s1)
   * @param j    is the length of the second string (s2)
   * @return and calculates the minimum edit distance it takes the second string
   *         to be like the first one using the temp matrix
   */
  private int calc_distance_dyn(String s1, String s2, int[][] temp, int i, int j) {
    if (i < 0)
      return j + 1;
    if (j < 0)
      return i + 1;
    if (temp[i][j] != -1)
      return temp[i][j];
    if (s1.charAt(i) == s2.charAt(j)) {
      temp[i][j] = calc_distance_dyn(s1, s2, temp, i - 1, j - 1);
    } else {
      temp[i][j] = 1 + min(calc_distance_dyn(s1, s2, temp, i, j - 1), // insertion
          calc_distance_dyn(s1, s2, temp, i - 1, j), // cancelation
          calc_distance_dyn(s1, s2, temp, i - 1, j - 1)); // replace
    }
    return temp[i][j];
  }
}
