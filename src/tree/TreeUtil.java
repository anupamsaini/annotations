package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A simple utility class.
 */
public class TreeUtil {

  /**
   * Returns the right most or the node with maximum value in a tree.
   * <p> Note that it works under the assumption that the tree is BST
   *
   * @param root the root node of the tree
   * @return the right most node of the BST
   */
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

  /**
   * Returns the left most or the node with minimum value in a tree.
   * <p> Note that it works under the assumption that the tree is BST.
   *
   * @param root the root node of the tree
   * @return the left most node of the BST
   */
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

  /**
   * Prints all the nodes in a tree at a particular level.
   *
   * @param root the root node of the tree
   * @return the string containing level wise representation of the tree
   */
  public static <T extends Comparable<T>> String printTreeLevelWise(BasicNode<T> root) {
    if (root == null) {
      throw new IllegalArgumentException("Root node can not be null.");
    }
    int height = getNodeHeight(root);
    Queue<BasicNode<T>> nodesAtALevel = new LinkedList<>();
    nodesAtALevel.add(root);
    StringBuilder sb = new StringBuilder();
    printTreeLevelWise(nodesAtALevel, sb, height);
    return sb.toString();
  }

  private static <T extends Comparable<T>> void printTreeLevelWise(
      Queue<BasicNode<T>> queue, StringBuilder sb , int height) {
    Queue<BasicNode<T>> nodesAtNextLevel = new LinkedList<>();
    while(!queue.isEmpty()) {
      BasicNode<T> node = queue.poll();
      sb.append(node.getValue()).append(",");
      if (node.hasLeft()) {
        nodesAtNextLevel.add(node.getLeft());
      }
      if (node.hasRight()) {
        nodesAtNextLevel.add(node.getRight());
      }
    }
    sb.append("\n");
    if (!nodesAtNextLevel.isEmpty()) {
      printTreeLevelWise(nodesAtNextLevel, sb, height -1);
    }
  }

  /**
   * Returns the height of the node.
   *
   * @param node the node at which the height is to be calculated
   * @return the height of the node
   */
  public static <T extends Comparable<T>> int getNodeHeight(BasicNode<T> node) {
    if (node == null) {
      return 0;
    }
    return Math.max(getNodeHeight(node.getLeft()), getNodeHeight(node.getRight())) + 1;
  }

  /**
   * Pretty prints a tree to {@link System#out}.
   * <p> For instance the following values in a tree would be printed as:
   *    [50,25,75,10,30,60,90,4,12,27,40,55,65,80,99]
   * </b>
   * <pre>
   *                      /----- 99
   *              /----- 90
   *              |       \----- 80
   *      /----- 75
   *      |       |       /----- 65
   *      |       \----- 60
   *      |               \----- 55
   *     50
   *      |               /----- 40
   *      |       /----- 30
   *      |       |       \----- 27
   *      \----- 25
   *              |       /----- 12
   *             \----- 10
   *                      \----- 4
   * </pre>
   * @see <a href="StackOverflow">http://stackoverflow.com/a/19484210/380161</a>
   *
   * @param root the root node of the tree
   */
  public static <T extends Comparable<T>> void printTree(BasicNode<T> root) {
    if(null == root) {
      throw new IllegalArgumentException("Root can not be null.");
    }
    printTreeInternal(root);
  }

  private static <T extends Comparable<T>> void printTreeInternal(BasicNode<T> node) {
    if (node.hasRight()) {
      printTree(node.getRight(), true, "");
    }
    System.out.print(node.getValue());
    System.out.print('\n');
    if (node.hasLeft()) {
      printTree(node.getLeft(), false, "");
    }
  }

  private static <T extends Comparable<T>> void printTree(BasicNode<T> node, boolean isRight,
      String indent) {
    if (node.hasRight()) {
      printTree(node.getRight(), true, indent + (isRight ? "        " : " |      "));
    }
    System.out.print(indent);
    if (isRight) {
      System.out.print(" /");
    } else {
      System.out.print(" \\");
    }
    System.out.print("----- ");
    System.out.print(node.getValue());
    System.out.print('\n');
    if (node.hasLeft()) {
      printTree(node.getLeft(), false, indent + (isRight ? " |      " : "        "));
    }
  }

  public static void main(String args[]) {
    Integer arr[] = {50,25,75,10,30,60,90,4,12,27,40,55,65,80,99};
    arr = new Integer[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
    arr = new Integer[] {20, 31, 17, 23, 34, 30, 36, 46, 28};
    BasicNode<Integer> node = BinarySearchTree.generateBST(Arrays.asList(arr));
    printTree(node);
  }
}
