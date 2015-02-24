package tests.trees;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

import tree.TreeUtil;

import tree.BasicNode;
import tree.BinarySearchTree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Unit tests for {@link BinarySearchTree}.
 */
public class BinarySearchTreeTest {

  @Test
  public void getLeafNodes_perfectlyBalancedTree_passes() {
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat(BinarySearchTree.getLeafNodes(root)).containsAllIn(
        getExpectedNodes(4, 12, 27, 40, 55, 65, 80, 99));
  }

  @Test
  public void getLeafNodes_singleNodeTree_passes() {
    Integer arr[] = {1};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat(BinarySearchTree.getLeafNodes(root)).containsAllIn(getExpectedNodes(1));
  }

  @Test
  public void getLeafNodes_rightSkewedTree_passes() {
    Integer arr[] = {1, 2, 3, 4, 5, 6, 7};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat(BinarySearchTree.getLeafNodes(root)).containsAllIn(getExpectedNodes(7));
  }

  @Test
  public void getLeafNodes_leftSkewedTree_passes() {
    Integer arr[] = {7, 6, 5, 4, 3, 2, 1};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat(BinarySearchTree.getLeafNodes(root)).containsAllIn(getExpectedNodes(1));
  }

  @Test
  public void treeHeightBalanced_returnsTrue() {
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat(BinarySearchTree.isHeigthBalanced(root)).isTrue();
  }

  @Test
  public void treeHeightBalanced_returnsFalse() {
    Integer arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat(BinarySearchTree.isHeigthBalanced(root)).isFalse();
  }

  @Test
  public void searchNodeInTree_balancedTree_nodeFound_returnsNodeObject() {
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    BinarySearchTree.insertToBST(root, 6);
    assertThat((BinarySearchTree.searchNodeWithValue(root, 6)))
        .isEqualTo(new BasicNode<Integer>(6));
  }

  @Test
  public void searchNodeInTree_unbalancedTree_nodeFound_returnsNodeObject() {
    Integer arr[] = {20, 31, 17, 23, 34, 30, 36, 46, 28};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    BinarySearchTree.insertToBST(root, 33);
    assertThat((BinarySearchTree.searchNodeWithValue(root, 33)))
        .isEqualTo(new BasicNode<Integer>(33));
  }

  @Test
  public void searchNodeInTree_nodeNotFound_returnsNullValue() {
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    BinarySearchTree.insertToBST(root, 6);
    assertThat((BinarySearchTree.searchNodeWithValue(root, -1))).isNull();
  }

  @Test
  public void searchNodeInTree_rootNodeContainsValueToSearch_returnsNode() {
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat((BinarySearchTree.searchNodeWithValue(root, 50)))
        .isEqualTo(new BasicNode<Integer>(50));
  }

  @Test
  public void getParentNode_rootNodeIsEqualToValueToSearch_returnsNull() {
    Integer arr[] = {7};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat((BinarySearchTree.getParentNodeOrNull(root, 7))).isNull();
  }

  @Test
  public void getParentNode_valueNotFound_returnsNull() {
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat((BinarySearchTree.getParentNodeOrNull(root, -1))).isNull();
  }

  @Test
  public void getParentNode_valueFound_returnsParentNode() {
    Integer arr[] = {50, 25, 75, 10, 30, 60, 90, 4, 12, 27, 40, 55, 65, 80, 99};
    BasicNode<Integer> root = BinarySearchTree.generateBST(Arrays.asList(arr));
    assertThat((BinarySearchTree.getParentNodeOrNull(root, 99)))
        .isEqualTo(new BasicNode<Integer>(90));
    assertThat((BinarySearchTree.getParentNodeOrNull(root, 10)))
        .isEqualTo(new BasicNode<Integer>(25));
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
