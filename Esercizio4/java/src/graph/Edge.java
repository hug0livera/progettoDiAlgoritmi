package graph;

public class Edge<V, T extends Comparable> implements Comparable<Edge> {
  private T weight;
  private V src;
  private final V destination;

  /**
   * @author nadim, Victor
   * @param destination destination vertex
   */
  public Edge(V destination) {
    this.destination = destination;
  }

  /**
   *
   * @param destination destination vertex
   * @param weight      weight of the edge
   */
  public Edge(V src, V destination, T weight) {
    this.src = src;
    this.destination = destination;
    this.weight = weight;
  }

  /**
   * 
   * @return source vertex
   */
  public V getSrc() {
    return this.src;
  }

  /**
   *
   * @return destination vertex
   */
  public V getDestination() {
    return this.destination;
  }

  /**
   *
   * @return the weight of the edge
   */
  public T getWeight() {
    return weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Edge<?, ?> edge = (Edge<?, ?>) o;
    return destination.equals(edge.destination);
  }

  @Override
  public int hashCode() {
    return this.destination.hashCode();
  }

  @Override
  public String toString() {
    return "\n\n{" + " \n   source: " + src + "\n   destination: " + destination + "\n   weight:  " + weight + "\n\n"
        + '}';
  }

  @Override
  public int compareTo(Edge o) {
    return weight.compareTo(o.getWeight());
  }
}
