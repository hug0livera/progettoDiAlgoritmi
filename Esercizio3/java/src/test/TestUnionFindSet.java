
package test;

import org.junit.Test;
import static org.junit.Assert.*;
import unionFindSet.*;

/**
 *
 * @author Victor
 */
public class TestUnionFindSet {

  @Test
  public void testMakeSet() {
    UnionFindSet<String> u = new UnionFindSet<>();
    u.makeSet("a");
    assertEquals("a", u.findSet("a"));
    u.makeSet("b");
    assertEquals("b", u.findSet("b"));
    u.makeSet("c");
    assertEquals("c", u.findSet("c"));
    u.makeSet("d");
    assertEquals("d", u.findSet("d"));
  }

  @Test
  public void testUnion() {
    UnionFindSet<String> u = new UnionFindSet<>();

    u.makeSet("a");
    u.makeSet("b");
    u.makeSet("c");
    u.makeSet("d");
    u.union("a", "b");
    u.union("a", "c");
    u.union("a", "d");
    u.union("b", "d");
    assertEquals("a", u.findSet("d"));
    assertEquals("a", u.findSet("c"));
    assertEquals("a", u.findSet("d"));

    u.makeSet("e");
    u.makeSet("f");
    u.makeSet("g");
    u.makeSet("h");
    u.union("e", "f");
    u.union("e", "g");
    u.union("e", "h");
    u.union("a", "e");
    assertEquals("a", u.findSet("e"));

  }

  @Test
  public void testFind() {
    UnionFindSet<String> u = new UnionFindSet<>();
    u.makeSet("a");
    u.makeSet("b");
    u.makeSet("c");
    u.makeSet("d");

    assertEquals("a", u.findSet("a"));
    assertEquals("b", u.findSet("b"));
    assertEquals("c", u.findSet("c"));
    assertEquals("d", u.findSet("d"));
  }

}
