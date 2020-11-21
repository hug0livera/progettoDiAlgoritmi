package test;

import org.junit.*;
import static org.junit.Assert.*;
import distance.EditDistance;
import distance.EditDistanceDyn;

/**
 *  
 * @author hugo
 * 
 * Testing methods for edit distance
 * 
 */
public class TestEditDistance {

  @Test
  public void testEditDistanceNullS1() {
    EditDistance.edit_distance(null, "prova", 0, 5);
  }

  @Test
  public void testEditDistanceNullS2() {
    EditDistance.edit_distance("prova", null, 5, 0);
  }

  @Test
  public void testEditDistanceEmptyStrings() {
    assertEquals(0, EditDistance.edit_distance("", "", 0, 0));
  }

  @Test
  public void testEditDistanceEmptyS1() {
    assertEquals(5, EditDistance.edit_distance("", "prova", 0, 5));
  }

  @Test
  public void testEditDistanceEmptyS2() {
    assertEquals(5, EditDistance.edit_distance("prova", "", 5, 0));
  }

  @Test
  public void testEditDistanceLength1() {
    assertEquals(1, EditDistance.edit_distance("a", "b", 1, 1));
  }

  @Test
  public void testEditDistanceLength1Same() {
    assertEquals(0, EditDistance.edit_distance("a", "a", 1, 1));
  }

  @Test
  public void testEditDistanceDeletionsOnly() {
    assertEquals(2, EditDistance.edit_distance("passa", "passato", 5, 7));
  }

  @Test
  public void testEditDistanceInsertionsOnly() {
    assertEquals(3, EditDistance.edit_distance("aaabbbccc", "aaaccc", 9, 6));
  }

  @Test
  public void testEditDistanceCasaCassa() {
    assertEquals(1, EditDistance.edit_distance("casa", "cassa", 4, 5));
  }

  @Test
  public void testEditDistanceGeneralCase() {
    assertEquals(3, EditDistance.edit_distance("tassa", "passato", 5, 7));
  }

  /**
   * 
   * @author Nadim
   * 
   * Testing methods for dynamic edit distance
   * 
   */

  EditDistanceDyn e1 = new EditDistanceDyn();

  @Test(expected = IllegalArgumentException.class)
  public void testEditDistanceDynNullS1() {
    e1.min_distance_dyn(null, "prova");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditDistanceDynNullS2() {
    e1.min_distance_dyn("prova", null);
  }

  @Test
  public void testEditDistanceDynEmptyStrings() {
    assertEquals(0, e1.min_distance_dyn("", ""));
  }

  @Test
  public void testEditDistanceDynEmptyS1() {
    assertEquals(5, e1.min_distance_dyn("", "prova"));
  }

  @Test
  public void testEditDistanceDynEmptyS2() {
    assertEquals(5, e1.min_distance_dyn("prova", ""));
  }

  @Test
  public void testEditDistanceDynLength1() {
    assertEquals(1, e1.min_distance_dyn("a", "b"));
  }

  @Test
  public void testEditDistanceDynLength1Same() {
    assertEquals(0, e1.min_distance_dyn("a", "a"));
  }

  @Test
  public void testEditDistanceDynDeletionsOnly() {
    assertEquals(2, e1.min_distance_dyn("passa", "passato"));
  }

  @Test
  public void testEditDistanceDynInsertionsOnly() {
    assertEquals(3, e1.min_distance_dyn("aaabbbccc", "aaaccc"));
  }

  @Test
  public void testEditDistanceDynCasaCassa() {
    assertEquals(1, e1.min_distance_dyn("casa", "cassa"));
  }

  @Test
  public void testEditDistanceDynGeneralCase() {
    assertEquals(3, e1.min_distance_dyn("tassa", "passato"));
  }

  @Test
  public void testEditDistanceDynCinqueCinqve() {
    assertEquals(1, e1.min_distance_dyn("cinque", "cinqve"));
  }
}
