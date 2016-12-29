package code.partitionset;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class UnionFindNodeTest {

  private UnionFindNode<String> rootNode;

  @Before
  public void setup() {
    rootNode = new UnionFindNode<String>("root");
  }

  @Test
  public void
      findRepresentative_componentWithOnlyOneElement_returnsTheOnlyElementAsRepresentative() {
    assertThat(rootNode.findRepresentative()).isEqualTo(rootNode);
  }

  @Test
  public void findRepresentative_returnsComponentRoot() {
    UnionFindNode<String> lChild = new UnionFindNode<String>("lChild");
    rootNode.union(lChild);

    assertThat(lChild.findRepresentative()).isEqualTo(rootNode);
  }

  @Test
  public void findRepresentative_multipleLevels_returnsComponentRoot() {
    // child at height 1.
    UnionFindNode<String> l1Child = new UnionFindNode<String>("lChild");
    // child at height 2.
    UnionFindNode<String> l2Child = new UnionFindNode<String>("lChild");

    rootNode.union(l1Child);
    assertThat(l1Child.findRepresentative()).isEqualTo(rootNode);

    l1Child.union(l2Child);
    assertThat(l2Child.findRepresentative()).isEqualTo(rootNode);
  }

  @Test
  public void updateNodeData_nullData_throwsException() {
    try {
      rootNode.set(null);
      fail("NullPointerException IllegalArgumentException");
    } catch (NullPointerException expected) {
    }
  }

  @Test
  public void updateNodeData_resetsDataSuccessfully() {
    rootNode.set("");
    assertThat(rootNode.get()).isEqualTo("");
  }

  @Test
  public void checkSameComponent_returnsTrue() {
    UnionFindNode<String> e1 = new UnionFindNode<String>("lChild");
    rootNode.union(e1);
    UnionFindNode<String> e2 = new UnionFindNode<String>("lChild");
    rootNode.union(e2);

    assertThat(e1.sameComponent(e2)).isTrue();
  }

  @Test
  public void checkSameComponent_returnsFalse() {
    UnionFindNode<String> e1 = new UnionFindNode<String>("E1");
    UnionFindNode<String> e2 = new UnionFindNode<String>("E2");

    assertThat(e1.sameComponent(e2)).isFalse();
  }

  @Test
  public void checkSameComponent_nullElement_returnsFalse() {
    assertThat(rootNode.sameComponent(null)).isFalse();
  }

  @Test
  public void unionElement_elementToMergeIsOfDifferentType_throwsException() {
    try {
      rootNode.union(new UnionFindNodeTest.Dummy<String>());
      fail("IllegalStateException expected");
    } catch (IllegalStateException expected) {
    }
  }

  @Test
  public void
      union_elementToMergeHasGreaterParentRank_mergesThisElementToComponentOfElementToMerge() {
    UnionFindNode<String> root1 = new UnionFindNode<String>("root1");

    UnionFindNode<String> root2 = new UnionFindNode<String>("root2");
    UnionFindNode<String> e2 = new UnionFindNode<String>("E2");
    UnionFindNode<String> e3 = new UnionFindNode<String>("E3");
    root2.union(e3);
    root2.union(e2);

    // Parent rank of e3 is greater than the parent rank of root1 element. Root1 is then merged with
    // the component of e3 and through path compression its parent is set to the root1 i.e. the
    // representative element of the other component.
    assertThat(root1.union(e3)).isEqualTo(root2);
  }

  @Test
  public void union_elementToMergeHasLesserParentRank_mergesOtherElementToComponentOfThisElement() {

    UnionFindNode<String> root1 = new UnionFindNode<String>("root1");
    UnionFindNode<String> e2 = new UnionFindNode<String>("E2");
    UnionFindNode<String> e3 = new UnionFindNode<String>("E3");
    root1.union(e3);
    root1.union(e2);

    UnionFindNode<String> root2 = new UnionFindNode<String>("root2");

    assertThat(e3.union(root2)).isEqualTo(root1);
  }

  @Test
  public void union_elementToMergeHasEqualParentRank_mergesOtherElementToComponentOfThisElement() {
    UnionFindNode<String> root1 =
        new UnionFindNode<String>("root1"); // Belongs to Different component.
    UnionFindNode<String> root2 =
        new UnionFindNode<String>("root2"); // Belongs to Different component.

    assertThat(root1.union(root2)).isEqualTo(root1);
  }

  @Test
  public void union_elementToMergeHaveSameRepresentativeElement_returnsRepresentativeElement() {
    UnionFindNode<String> root1 = new UnionFindNode<String>("root1");
    rootNode.union(root1);
    UnionFindNode<String> root2 = new UnionFindNode<String>("root2");
    rootNode.union(root2);

    assertThat(root1.union(root2)).isEqualTo(rootNode);
  }

  /**
   * A dummy class that does nothing, it only provides stubbed implementation of {@code
   * PartitionElement}.
   */
  private class Dummy<T> implements PartitionElement<T> {
    @Override
    public PartitionElement<T> findRepresentative() {return null;}

    @Override
    public T get() {return null;}

    @Override
    public boolean sameComponent(PartitionElement<T> elementToSearch) {return false;}

    @Override
    public void set(T elementData) {}

    @Override
    public PartitionElement<T> union(PartitionElement<T> elementToMerge) {return null;}
  }
}
