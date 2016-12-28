package code.partitionset;

import com.google.common.base.Preconditions;

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
    // For each component, the representative or the root node points to itself, we recurse till
    // we reach the root node. Once the recursion breaks we return the root node and at each level
    // the parent node is reset to the root node. This is basically "Path compression" and all nodes
    // in a particular path after the completion of this step point to the root node.
    if (this.parentNode != this) {
      this.parentNode = (UnionFindNode<T>) parentNode.findRepresentative();
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
    Preconditions.checkState(!(elementToMerge instanceof UnionFindNode),
        "Only elements of UnionFindNode type supported");

    UnionFindNode<T> representativeElementForThis = this.findRepresentative();
    UnionFindNode<T> representativeEleForOther =
        (UnionFindNode<T>) elementToMerge.findRepresentative();

    if (representativeElementForThis.rank < representativeEleForOther.rank) {
      representativeElementForThis.parentNode = representativeEleForOther;
      return representativeEleForOther;
    } else {
      representativeEleForOther.parentNode = representativeElementForThis;
      if (representativeElementForThis.rank == representativeEleForOther.rank) {
        representativeElementForThis.rank++;
      }
      return representativeElementForThis;
    }
  }

}
