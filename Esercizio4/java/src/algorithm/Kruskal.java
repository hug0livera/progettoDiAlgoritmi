package algorithm;

import graph.*;
import java.util.*;
import unionFindSet.*;

/**
 * @author Nadim
 * @param <V> vertex generic type
 * @param <T> weight generic type extends Comparable since in kruskal i must
 *            compare all the weights of the edges and start to take the minimum
 *            weight and repeat this step again and again but if an edge makes a
 *            cycle i exclude it and search for the next minimum weight
 */
public class Kruskal<V, T extends Comparable> {
  ArrayList<Edge<V, T>> result = new ArrayList<>();

  public ArrayList<Edge<V, T>> mst(UnDirectedGraph<V, T> graph) {
    UnionFindSet<V> u = new UnionFindSet<>();
    ArrayList<V> vertices = new ArrayList<>();
    for (int i = 0; i < graph.numOfVertices(); i++) {
      vertices = graph.getVertices();
      u.makeSet(vertices.get(i));
    }

    ArrayList<Edge<V, T>> sortedEdges = sortEdges(graph);

    for (Edge<V, T> e : sortedEdges) {
      if (u.findSet(e.getSrc()) != u.findSet(e.getDestination())) {
        result.add(new Edge<V, T>(e.getSrc(), e.getDestination(), e.getWeight()));
        u.union(e.getSrc(), e.getDestination());
      }
    }
    return result;
  }

  /**
   * 
   * @return number of edges after running Kruskal on the graph
   */
  public int numOfEdgesAfterKruskal() {
    return result.size();
  }

  /**
   * 
   * @return the total weight calculated after running kruskal algorithm; d is
   *         divided by 1000 to convert it into kilometers
   */
  public Double getTotalDistance() {
    Double d = 0.0;
    for (Edge<V, T> e : result) {
      d += (Double) e.getWeight();
    }
    return d / 1000;
  }

  /**
   * 
   * @param graph undirected graph
   * @return an arraylist with all the edges sorted in increasing order in order
   *         to run kruskal algorithm
   */
  public ArrayList<Edge<V, T>> sortEdges(UnDirectedGraph<V, T> graph) {
    ArrayList<Edge<V, T>> sortedEdges = new ArrayList<Edge<V, T>>();
    for (ArrayList<Edge<V, T>> connectedEdges : graph.getEdges()) {
      sortedEdges.addAll(connectedEdges);
    }
    Collections.sort(sortedEdges);
    return sortedEdges;
  }
}
