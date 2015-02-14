package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.Helper;


/**
 * A trivial implementation of Binary Search Tree.
 */
public class BinarySearchTree<T extends Comparable<T>> {

  public static void main(String args[]) {
    // {9, 4, 1, 8, 2, 6, 10, 3};
    Integer arr[] = {20, 31, 17, 28, 23, 34, 30, 36, 46, 28};
     arr = Helper.generateRandomIntObj(500000, 1000);
    BasicNode<Integer> node = generateBs(Arrays.asList(arr));
    System.out.println("Generated " + Helper.prettyPrintCollection(Arrays.asList(arr)));
    System.out.println("Breadth   " + breadth(node));
    System.out.println("Inorder   " + inOrder(node));
    System.out.println("PreDepth  " + inPreDepth(node));
    System.out.println("PostDepth " + inPostDepth(node));
    System.out.println("LeafNodes " + getAllLeafNodes(node));
  }

  public static <T extends Comparable<T>> BasicNode<T> generateBs(List<T> list) {
    if (null == list || list.size() < 1) {
      throw new IllegalArgumentException("Input is empty or null");
    }
    BasicNode<T> node = new BasicNode<T>(list.get(0));
    for (int i = 1; i < list.size(); i++) {
      generateBST(node, list.get(i));
    }
    return node;
  }

  public static <T extends Comparable<T>> List<BasicNode<T>> inOrder(BasicNode<T> root) {
    if (null == root) {
      throw new IllegalArgumentException("Input is empty or null");
    }
    List<BasicNode<T>> inOrderView = new ArrayList<BasicNode<T>>();
    traverseInOrder(root, inOrderView);
    return inOrderView;
  }

  public static <T extends Comparable<T>> List<BasicNode<T>> inPreDepth(BasicNode<T> root) {
    if (null == root) {
      throw new IllegalArgumentException("Input is empty or null");
    }
    List<BasicNode<T>> inOrderView = new ArrayList<BasicNode<T>>();
    traversePreDepth(root, inOrderView);
    return inOrderView;
  }

  public static <T extends Comparable<T>> List<BasicNode<T>> inPostDepth(BasicNode<T> root) {
    if (null == root) {
      throw new IllegalArgumentException("Input is empty or null");
    }
    List<BasicNode<T>> inOrderView = new ArrayList<BasicNode<T>>();
    traversePostDepth(root, inOrderView);
    return inOrderView;
  }

  public static <T extends Comparable<T>> List<BasicNode<T>> breadth(BasicNode<T> root) {
    if (null == root) {
      throw new IllegalArgumentException("Input is empty or null");
    }
    List<BasicNode<T>> inOrderView = new ArrayList<BasicNode<T>>();
    Queue<BasicNode<T>> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      BasicNode<T> tmpNode = queue.poll();
      inOrderView.add(tmpNode);
      if (tmpNode.hasLeft()) {
        queue.add(tmpNode.getLeft());
      }
      if (tmpNode.hasRight()) {
        queue.add(tmpNode.getRight());
      }
    }
    return inOrderView;
  }

  public static <T extends Comparable<T>> List<BasicNode<T>> getAllLeafNodes(BasicNode<T> root) {
    if (null == root) {
      throw new IllegalArgumentException("Input is empty or null");
    }
    List<BasicNode<T>> leafNodes = new ArrayList<BasicNode<T>>();
    getAllLeafNodes(root, leafNodes);
    return leafNodes;
  }

  private static <T extends Comparable<T>> void generateBST(BasicNode<T> root, T value) {
    BasicNode<T> node = root;
    while (true) {
      if (node.getValue().compareTo(value) == 1) {
        if (!node.hasLeft()) {
          BasicNode<T> newNode = new BasicNode<T>(value);
          node.setLeft(newNode);
          break;
        } else {
          node = node.getLeft();
        }
      } else if (node.getValue().compareTo(value) == -1) {
        if (!node.hasRight()) {
          BasicNode<T> newNode = new BasicNode<T>(value);
          node.setRight(newNode);
          break;
        } else {
          node = node.getRight();
        }
      } else {
        break;
      }
    }
  }

  private static <T extends Comparable<T>> void traverseInOrder(BasicNode<T> node,
      List<BasicNode<T>> inOrderView) {
    if (node == null) {
      return;
    }
    traverseInOrder(node.getLeft(), inOrderView);
    inOrderView.add(node);
    traverseInOrder(node.getRight(), inOrderView);
  }

  private static <T extends Comparable<T>> void traversePreDepth(BasicNode<T> node,
      List<BasicNode<T>> preOrderView) {
    if (node == null) {
      return;
    }
    preOrderView.add(node);
    traversePreDepth(node.getLeft(), preOrderView);
    traversePreDepth(node.getRight(), preOrderView);
  }

  private static <T extends Comparable<T>> void traversePostDepth(BasicNode<T> node,
      List<BasicNode<T>> postOrderView) {
    if (node == null) {
      return;
    }
    traversePostDepth(node.getLeft(), postOrderView);
    traversePostDepth(node.getRight(), postOrderView);
    postOrderView.add(node);
  }

  private static <T extends Comparable<T>> void getAllLeafNodes(BasicNode<T> node,
      List<BasicNode<T>> leafNodes) {
    List<BasicNode<T>> inOrderView = new ArrayList<>();
    traverseInOrder(node, inOrderView);
    for (BasicNode<T> possibleLeaf : inOrderView) {
      if (!possibleLeaf.hasLeft() && !possibleLeaf.hasRight()) {
        leafNodes.add(possibleLeaf);
      }
    }
  }
}
