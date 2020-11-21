
package unionFindSet;

import java.util.HashMap;

/**
 * 
 * This class contains a collection of disjoint sets of ditinct elements
 * 
 * 
 * @author Victor
 * @author Nadim
 * @param <T> type of the element
 */
public class UnionFindSet<T> {

  public final HashMap<T, Node> map = new HashMap<>();

  /**
   * Creates a new set
   * 
   * @param o the element that will be the new set
   * 
   */
  public void makeSet(T o) {
    Node n = new Node(o, null, 0);
    n.setParent(n);
    map.put(o, n);
  }

  /**
   * Union the sets x and y in an unique set
   * 
   * @param x first set of elements
   * @param y second set of elements
   */
  public void union(T x, T y) {
    T parentX = findSet(x);
    T parentY = findSet(y);

    if (parentX == null || parentY == null || parentX == parentY)
      return;

    Node nodeX = map.get(parentX);
    Node nodeY = map.get(parentY);

    if (nodeX.getElement() == nodeY.getElement())
      return;

    if (nodeX.getRank() > nodeY.getRank())
      nodeY.setParent(map.get(x));
    else if (nodeY.getRank() > nodeX.getRank())
      nodeX.setParent(map.get(y));
    else {
      nodeY.setParent(map.get(x));
      nodeX.setRank(+1);
    }
  }

  /**
   * returns the name of the set containing the element o search it in the hashmap
   * and save it in a node to search for it later in the private method findSet
   * the root node
   * 
   * 
   * @param o element to be searched in a set
   * 
   * @return returns a node to the private class FindSet, to see if the node is in
   *         root
   */
  public T findSet(T o) {
    Node node = map.get(o);
    Node res = findSet(node);
    return (T) res.getElement();
  }

  /**
   * 
   * @param node node to be searched for
   * 
   * @return Returns the root node
   */
  private Node findSet(Node node) {
    if (node == null)
      return null;
    if (node != node.getParent())
      node.setParent(findSet(node.getParent()));
    return node.getParent();
  }
}