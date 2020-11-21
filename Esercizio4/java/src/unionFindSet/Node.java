package unionFindSet;

/**
 *
*@author victor
 */
public class Node<T> {
  private T element;
  private Node parent;
  private int rank;

  /**
   * 
   * Creates a new node for every MakeSet (creation of a new set)
   *
   * 
   * @param element element
   * @param parent  Father of the nodes, if there's only one element then it
   *                points at itself
   * @param rank    Height of the tree
   */
  public Node(T element, Node parent, int rank) {
    this.element = element;
    this.parent = parent;
    this.rank = rank;
  }

  /**
   * 
   * 
   * @return Returns the element of the node
   */
  public T getElement() {
    return element;
  }

  /**
   * 
   * @return Returns the father of the node, if there's only one element it
   *         returns itself
   */
  public Node getParent() {
    return parent;
  }

  /**
   * 
   * @return returns the height of the tree
   */
  public int getRank() {
    return rank;
  }

  /**
   * 
   * @param parent Modify which will be the new father
   */
  public void setParent(Node parent) {
    this.parent = parent;
  }

  /**
   * 
   * @param rank Increase the rank if the two sets have the same rank
   */
  public void setRank(int rank) {
    this.rank = rank;
  }

  @Override
  public String toString() {
    return "Node{" + "element=" + element + ", parent=" + parent + ", rank=" + rank + '}';
  }
}
