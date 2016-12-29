package code.partitionset;

import com.google.common.base.Preconditions;

/**
 * A Union find datastructure based implemention of the partiton set. @see <a
 * href="https://visualgo.net/ufds">Union Find datasets</a>
 */
public class UnionFindNode<T> implements PartitionElement<T> {

  private T elementData;
  private UnionFindNode<T> parentNode;
  private int rank;

  public UnionFindNode(T elementData) {
    this.elementData = Preconditions.checkNotNull(elementData, "elementData");
    this.rank = 0;
    this.parentNode = this;
  }

  @Override
  public UnionFindNode<T> findRepresentative() {
    // For each component, the representative or the root node points to itself. To find
    // representative of any child element we recurse till we reach the root node. Once the
    // recursion breaks, we return the root node and at each level the parent node is reset to the
    // root node. This is basically "Path compression" and all nodes in a particular path after the
    // completion of this step point to the root node.
    if (this.parentNode != this) {
      this.parentNode = parentNode.findRepresentative();
    }
    return this.parentNode;
  }

  @Override
  public T get() {
    return elementData;
  }

  @Override
  public boolean sameComponent(PartitionElement<T> elementToSearch) {
    return elementToSearch != null
        && elementToSearch.findRepresentative() == this.findRepresentative();
  }

  @Override
  public void set(T elementData) {
    this.elementData = Preconditions.checkNotNull(elementData, "elementData");
  }

  @Override
  public PartitionElement<T> union(PartitionElement<T> elementToMerge) {
    Preconditions.checkState((elementToMerge instanceof UnionFindNode),
        "Only elements of UnionFindNode type supported");

    if (this.sameComponent(elementToMerge)) {
      return this.findRepresentative();
    }

    UnionFindNode<T> currentEleRepresentative = this.findRepresentative();
    UnionFindNode<T> otherEleRepresentative =
        (UnionFindNode<T>) elementToMerge.findRepresentative();

    if (currentEleRepresentative.rank < otherEleRepresentative.rank) {
      currentEleRepresentative.parentNode = otherEleRepresentative;
      return otherEleRepresentative;
    } else {
      otherEleRepresentative.parentNode = currentEleRepresentative;
      if (currentEleRepresentative.rank == otherEleRepresentative.rank) {
        currentEleRepresentative.rank++;
      }
      return currentEleRepresentative;
    }
  }
}
