package test;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import graph.*;

/**
 * @author Nadim Testing for the directed graph
 */
public class TestDirectedGraph {
  @Test(expected = IllegalArgumentException.class)
  public void test_add_edge_no_loops() {
    DirectGraph<String, Double> g = getGraph();
    g.addEdge("A", "A", (double) 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_add_edge_no_multigraph() {
    DirectGraph<String, Double> g = getGraph();
    g.addEdge("A", "B", (double) 5);
  }

  @Test(expected = NullPointerException.class)
  public void test_add_edge_null_vertex() {
    DirectGraph<String, Double> g = getGraph();
    g.addEdge(null, "A", (double) 5);
  }

  @Test
  public void test_add_edge() {
    DirectGraph<String, Double> g = getGraph();
    g.addEdge("B", "C", (double) 5);
    int index = g.getAdjVertices("B").indexOf("C");
    String adj = g.getAdjVertices("B").get(index);
    assertTrue(g.containsEdge("B", "C"));
    assertEquals(new Double(5), g.getWeightOfAnEdge("B", adj));
  }

  @Test(expected = NullPointerException.class)
  public void test_add_null_vertex() {
    DirectGraph<String, Double> g = getGraph();
    g.addVertex(null);
  }

  @Test
  public void test_add_vertex() {
    DirectGraph<String, Double> g = getGraph();
    assertFalse(g.containsVertex("E"));
    g.addVertex("E");
    assertTrue(g.containsVertex("E"));
  }

  @Test
  public void test_contains_edge() {
    DirectGraph<String, Double> g = getGraph();
    assertTrue(g.containsEdge("A", "B"));
  }

  @Test
  public void test_contains_edge_false() {
    DirectGraph<String, Double> g = getGraph();
    assertFalse(g.containsEdge("F", "G"));
  }

  @Test
  public void test_contains_vertex() {
    DirectGraph<String, Double> g = getGraph();
    assertTrue(g.containsVertex("A"));
    assertTrue(g.containsVertex("B"));
    assertTrue(g.containsVertex("C"));
  }

  @Test
  public void test_contains_vertex_false() {
    DirectGraph<String, Double> g = getGraph();
    assertFalse(g.containsVertex("P"));
    assertFalse(g.containsVertex("L"));
    assertFalse(g.containsVertex("Q"));
  }

  @Test
  public void test_contains_null_vertex() {
    DirectGraph<String, Double> g = getGraph();
    assertFalse(g.containsVertex(null));
  }

  @Test
  public void test_get_adj_list() {
    DirectGraph<String, Double> g = getGraph();
    ArrayList<String> adj = g.getAdjVertices("A");
    assertTrue(adj.contains("B"));
    assertTrue(adj.contains("C"));
    assertTrue(adj.contains("D"));
    assertEquals(3, adj.size());
  }

  @Test
  public void test_edge_count() {
    DirectGraph<String, Double> g = getGraph();
    assertEquals(3, g.numOfEdges());
  }

  @Test
  public void test_edge_count_empty() {
    DirectGraph<String, Double> g = new DirectGraph<>();
    assertEquals(0, g.numOfEdges());
  }

  @Test
  public void test_vertex_count() {
    DirectGraph<String, Double> g = getGraph();
    assertEquals(4, g.numOfVertices());
  }

  @Test
  public void test_vertex_count_empty() {
    DirectGraph<String, Double> g = new DirectGraph<>();
    assertEquals(0, g.numOfVertices());
  }

  @Test(expected = NullPointerException.class)
  public void test_get_edge_weight_vertex_null() {
    DirectGraph<String, Double> g = getGraph();
    g.getWeightOfAnEdge(null, "B");
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_get_edge_weight_edge_not_exists() {
    DirectGraph<String, Double> g = getGraph();
    g.getWeightOfAnEdge("B", "C");
  }

  @Test
  public void test_get_edge_weight() {
    DirectGraph<String, Double> g = getGraph();
    assertEquals(new Double(5), g.getWeightOfAnEdge("A", "B"));
    assertEquals(new Double(7), g.getWeightOfAnEdge("A", "C"));
  }

  @Test
  public void test_get_vertex_list() {
    DirectGraph<String, Double> g = getGraph();
    ArrayList<String> list = g.getVertices();
    assertTrue(list.contains("A"));
    assertTrue(list.contains("B"));
    assertTrue(list.contains("C"));
    assertTrue(list.contains("D"));
    assertEquals(4, list.size());
  }

  @Test
  public void test_remove_edge() {
    DirectGraph<String, Double> g = getGraph();
    assertTrue(g.containsEdge("A", "B"));
    g.removeEdge("A", "B");
    assertEquals(4, g.numOfVertices());
    assertEquals(2, g.numOfEdges());
    assertFalse(g.containsEdge("A", "B"));
  }

  @Test
  public void test_remove_edge_not_exists() {
    DirectGraph<String, Double> g = getGraph();
    g.removeEdge("C", "D");
    assertEquals(4, g.numOfVertices());
    assertEquals(3, g.numOfEdges());
  }

  @Test
  public void test_remove_vertex() {
    DirectGraph<String, Double> g = getGraph();
    assertTrue(g.containsVertex("A"));
    g.removeVertex("A");
    assertEquals(3, g.numOfVertices());
    assertEquals(0, g.numOfEdges());
    assertFalse(g.containsVertex("A"));
  }

  @Test
  public void test_remove_vertex_not_exists() {
    DirectGraph<String, Double> g = getGraph();
    g.removeVertex("P");
    assertEquals(4, g.numOfVertices());
    assertEquals(3, g.numOfEdges());
  }

  protected DirectGraph<String, Double> getGraph() {
    DirectGraph<String, Double> g = new DirectGraph<>();
    g.addEdge("A", "B", (double) 5);
    g.addEdge("A", "C", (double) 7);
    g.addEdge("A", "D", (double) 6);
    return g;
  }
}