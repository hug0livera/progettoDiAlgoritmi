package distance;

public class EditDistance {
  /**
   * @author Nadim
   *@author victor
   * @param insertion   is the operation where to insert characters
   * @param cancelation is the operation where to cancel characters
   * @param replace     is the operation where to replace/substitute characters
   * @return the minimum between the 3 operations done to calculate the edit
   *         distance
   */
  public static int min(int insertion, int cancelation, int replace) {
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
   * @param i  is the length of the first string (s1)
   * @param j  is the length of the second string (s2)
   * @return the minimum edit distance it takes the second string to be like the
   *         first one
   */
  public static int edit_distance(String s1, String s2, int i, int j) {
    if (j == 0)
      return i;
    if (i == 0)
      return j;
    if (s1.charAt(i - 1) == s2.charAt(j - 1))
      return edit_distance(s1, s2, i - 1, j - 1);

    return 1 + min(edit_distance(s1, s2, i, j - 1), // insertion
        edit_distance(s1, s2, i - 1, j), // cancelation
        edit_distance(s1, s2, i - 1, j - 1)); // replace
  }
}
