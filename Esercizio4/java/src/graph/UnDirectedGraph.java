package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

public class UnDirectedGraph<V, T extends Comparable> extends DirectGraph<V, T> {

  /**
   * @author nadim
   * @author victor
   * @creates an Undirected graph
   */
  public UnDirectedGraph() {
    super();
  }

  @Override
  public void addEdge(V u, V v, T weight) {
    super.addEdge(u, v, weight);
    super.addEdge(v, u, weight);
    // adjList.get(v).add(new Edge<V>(u, (weight != null) ? weight : null));
  }

  @Override
  public void removeVertex(V u) {
    if (!adjList.containsKey(u))
      return;
    for (Edge<V, T> edge : adjList.get(u)) {
      adjList.get(edge.getDestination()).remove(new Edge<V, T>(u));
    }
    adjList.remove(u);
  }

  @Override
  public void removeEdge(V u, V v) {
    super.removeEdge(u, v);
    super.removeEdge(v, u);
  }

  @Override
  public int numOfEdges() {
    return super.numOfEdges() / 2;
  }

  @Override
  public boolean isDirect() {
    return false;
  }

}