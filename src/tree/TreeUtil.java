package tree;

/**
 * A simple utility class.
 */
public class TreeUtil {

  public static <T extends Comparable<T>> BasicNode<T> getRightMostNode(BasicNode<T> root) {
    if (root == null) {
      throw new IllegalArgumentException("Root node can not be null.");
    }
    BasicNode<T> rightNode = root;
    while (true) {
      if (!rightNode.hasRight()) {
        return rightNode;
      }
      rightNode = rightNode.getRight();
    }
  }

  public static <T extends Comparable<T>> BasicNode<T> getLeftMostNode(BasicNode<T> root) {
    if (root == null) {
      throw new IllegalArgumentException("Root node can not be null.");
    }
    BasicNode<T> leftNode = root;
    while (true) {
      if (!leftNode.hasLeft()) {
        return leftNode;
      }
      leftNode = leftNode.getLeft();
    }
  }
}
