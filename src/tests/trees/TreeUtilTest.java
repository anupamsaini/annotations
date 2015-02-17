package tests.trees;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import tree.BasicNode;
import tree.BinarySearchTree;
import tree.TreeUtil;

/**
 * Unit tests for {@link TreeUtil}.
 */
public class TreeUtilTest {

  @Test
  public void getRightMostNode_returnsNodeSucessfully() {
    // Perfectly balanced.
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertEquals(new BasicNode<Integer>(99), TreeUtil.getRightMostNode(root));

    // Left skewed.
    arr = new Integer[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
    root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertEquals(new BasicNode<Integer>(9), TreeUtil.getRightMostNode(root));

    // Right skewed.
    arr = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertEquals(new BasicNode<Integer>(9), TreeUtil.getRightMostNode(root));

    // Randomly dispersed.
    arr = new Integer[] {5, 3, 7, 1, 6, 8};
    root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertEquals(new BasicNode<Integer>(8), TreeUtil.getRightMostNode(root));
  }

  @Test
  public void getLeftMostNode_returnsNodeSucessfully() {
    // Perfectly balanced.
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertEquals(new BasicNode<Integer>(4), TreeUtil.getLeftMostNode(root));

    // Left skewed.
    arr = new Integer[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
    root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertEquals(new BasicNode<Integer>(1), TreeUtil.getLeftMostNode(root));

    // Right skewed.
    arr = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertEquals(new BasicNode<Integer>(1), TreeUtil.getLeftMostNode(root));

    // Randomly dispersed.
    arr = new Integer[] {5, 3, 7, 1, 6, 8};
    root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertEquals(new BasicNode<Integer>(1), TreeUtil.getLeftMostNode(root));
  }
}
