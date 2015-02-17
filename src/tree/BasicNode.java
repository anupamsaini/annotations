package tree;

import java.util.Objects;


/**
 * A Node in a binary tree.
 */
public class BasicNode<T extends Comparable<? super T>> {

  /**
   * The left child of the node.
   */
  private BasicNode<T> left;
  /**
   * The right child of the node.
   */
  private BasicNode<T> right;
  /**
   * The value associated with the node.
   */
  private final T value;

  public BasicNode(T value) {
    this.value = value;
  }

  public BasicNode<T> getLeft() {
    return left;
  }

  public void setLeft(BasicNode<T> left) {
    this.left = left;
  }

  public BasicNode<T> getRight() {
    return right;
  }

  public void setRight(BasicNode<T> right) {
    this.right = right;
  }

  public T getValue() {
    return value;
  }

  public boolean hasLeft() {
    return null != left;
  }

  public boolean hasRight() {
    return null != right;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(value);
    return value.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right, value);
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BasicNode<T> otherNode = (BasicNode<T>) o;
    return this.getValue().compareTo(otherNode.getValue()) == 0 ? true : false;
  }
}
