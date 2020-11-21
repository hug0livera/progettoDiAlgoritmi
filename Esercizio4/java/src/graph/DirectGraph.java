package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

public class DirectGraph<V, T extends Comparable> {

  protected Map<V, ArrayList<Edge<V, T>>> adjList;

  /**
   * @author Nadim
   *@author victor
   * @create a new empty graph
   */
  public DirectGraph() {
    this.adjList = new HashMap<V, ArrayList<Edge<V, T>>>();
  }

  /**
   * add a new vertex to the graph if it doesn't already exist
   *
   * @param u vertex to be added to the graph; if u == null throws a
   *          NullPointerException
   */
  public void addVertex(V u) {
    if (u == null) {
      throw new NullPointerException("The new vertex must not be null.");
    }
    adjList.put(u, new ArrayList<>());
  }

  /**
   * adds an edge to a directed graph (from u to v)
   *
   * @param u      the source vertex
   * @param v      the destination vertex
   * @param weight the weight of the edge from u(the source) to v(the destination)
   *               if the weight inserted is null it will be set to null as if
   *               it's considered the fault of the user
   * @throws a  NullPointerException if one of the vertices is null
   * @throws an IllegalArgumentException if u.equals(v) thus it must not contain
   *            loops
   * @adds the source/destination vertex if they didn't already exist in the graph
   * @throws an IllegalArgumentException if the graph contains already an edge
   *            from u to v
   */
  public void addEdge(V u, V v, T weight) {
    if (u == null || v == null) {
      throw new NullPointerException("The vertices must not be null.");
    }
    if (u.equals(v))
      throw new IllegalArgumentException("The graph can't contain loops.");
    if (!adjList.containsKey(u))
      addVertex(u);
    if (containsEdge(u, v)) {
      throw new IllegalArgumentException("This edge already exists from u to v and it's a directed graph;");
    }
    if (!containsVertex(v))
      addVertex(v);
    adjList.get(u).add(new Edge<V, T>(u, v, (weight != null) ? weight : null));
  }

  /**
   * it removes a vertex from a directed graph
   *
   * @param u is the vertex to be deleted from the graph if the graph doesn't
   *          contain this vertex simply return(nothing to delete); else delete
   */
  public void removeVertex(V u) {
    if (!adjList.containsKey(u))
      return;
    adjList.remove(u);
    for (Map.Entry<V, ArrayList<Edge<V, T>>> entry : adjList.entrySet()) {
      entry.getValue().remove(new Edge<V, T>(u));
    }
  }

  /**
   * it removes an edge from a directed graph
   *
   * @param u is the source vertex
   * @param v is the destination vertex if the graph doesn't contain one of the
   *          vertices then simply return; else remove edge
   */
  public void removeEdge(V u, V v) {
    if (!adjList.containsKey(u) || !adjList.containsKey(v))
      return;
    adjList.get(u).remove(new Edge<V, T>(v));
  }

  /**
   * checks if the graph contains a vertex
   *
   * @param u vertex to be checked if it exists or not
   * @return true if u exists; else false
   */
  public boolean containsVertex(V u) {
    return adjList.containsKey(u);
  }

  /**
   * checks if the graph contains an edge
   *
   * @param u is the source vertex
   * @param v is the destination vertex
   * @return true if the edge from u to v exists; else false
   */
  public boolean containsEdge(V u, V v) {
    if (!adjList.containsKey(u))
      return false;
    return adjList.get(u).contains(new Edge<V, T>(v));
  }

  /**
   *
   * @return true since it's a directed graph
   */
  public boolean isDirect() {
    return true;
  }

  /**
   *
   * @return the number of vertices in the directed graph
   */
  public int numOfVertices() {
    return adjList.size();
  }

  /**
   * @return the number of edges that a directed graph contains
   */
  public int numOfEdges() {
    int count = 0;
    for (V tmp : adjList.keySet()) {
      ArrayList<Edge<V, T>> list = adjList.get(tmp);
      count += list.size();
    }
    return count;
  }

  /**
   *
   * @return a list of the vertices of the graph
   */
  public ArrayList<V> getVertices() {
    return new ArrayList<V>(adjList.keySet());
  }

  /**
   *
   * @param u target vertex
   * @return the adjacent list of vertices of the target vertex
   */
  public ArrayList<V> getAdjVertices(V u) {
    if (!adjList.containsKey(u))
      throw new IllegalArgumentException("Key isn't found.");
    ArrayList<V> list = new ArrayList<V>();
    for (Edge<V, T> tmp : adjList.get(u)) {
      list.add(tmp.getDestination());
    }
    return list;
  }

  public Collection<ArrayList<Edge<V, T>>> getEdges() {
    return adjList.values();
  }

  /**
   * @param u source vertex
   * @param v destination vertex
   * @returns the weight of the edge connecting u with v
   * @throws exception if the graph doesn't contain one of the vertices
   * @throws exception if the graph doesn't contain an edge between u and v even
   *                   if u and v exist
   */
  public T getWeightOfAnEdge(V u, V v) {
    if (u == null || v == null)
      throw new NullPointerException("The vertices must not be null");
    if (!adjList.keySet().contains(u))
      throw new IllegalArgumentException("Source vertex doesn't exist");
    if (!containsVertex(v))
      throw new IllegalArgumentException("Destination vertex doesn't exist");
    if (!containsEdge(u, v))
      throw new IllegalArgumentException("This edge doesn't exist");
    ArrayList<Edge<V, T>> list = adjList.get(u);
    int index = list.indexOf(new Edge<V, T>(v));
    adjList.get(u).get(0);
    return list.get(index).getWeight();
  }
}
