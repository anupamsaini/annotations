package tests.trees;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import tree.BasicNode;
import tree.BinarySearchTree;

/**
 * Unit tests for {@link BinarySearchTree}.
 */
public class BinarySearchTreeTest {

  @Test
  public void getLeafNodes_perfectlyBalancedTree_passes() {
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat(BinarySearchTree.getAllLeafNodes(root)).containsAllIn(
        getExpectedNodes(4, 12, 27, 40, 55, 65, 80, 99));
  }

  @Test
  public void getLeafNodes_rightSkewedTree_passes() {
    Integer arr[] = {1, 2, 3, 4, 5, 6, 7};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat(BinarySearchTree.getAllLeafNodes(root)).containsAllIn(getExpectedNodes(7));
  }

  @Test
  public void getLeafNodes_leftSkewedTree_passes() {
    Integer arr[] = {7, 6, 5, 4, 3, 2, 1};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat(BinarySearchTree.getAllLeafNodes(root)).containsAllIn(getExpectedNodes(1));
  }

  @Test
  public void treeHeightBalanced_returnsTrue() {
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertTrue(BinarySearchTree.isHeigthBalanced(root));
  }

  @Test
  public void treeHeightBalanced_returnsFalse() {
    Integer arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertFalse(BinarySearchTree.isHeigthBalanced(root));
  }

  @SafeVarargs
  private final <T extends Comparable<T>> Set<BasicNode<T>> getExpectedNodes(T... values) {
    Set<BasicNode<T>> expectedNodes = new HashSet<>();
    for (T value : values) {
      expectedNodes.add(new BasicNode<T>(value));
    }
    return expectedNodes;
  }
}
