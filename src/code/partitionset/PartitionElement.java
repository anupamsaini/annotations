package code.partitionset;

/**
 * Interface representing the element belonging to a partition set.
 * 
 * @author so
 * 
 */
public interface PartitionElement<T> {

  /**
   * Returns the representative element of the component. It is the root node of the component tree
   * to which this partition element belongs.
   * 
   * @return the representative element
   */
  public PartitionElement<T> findRepresentative();

  /**
   * Returns the data associated with the partition element.
   * 
   * @return the associated data
   */
  T get();

  /**
   * Returns true if the passed {@code PartitionElement} belongs to the same component as the
   * element to which it is compared, false otherwise.
   * 
   * @param elementToSearch the element to be searched
   * @return boolean
   */
  boolean sameComponent(PartitionElement<T> elementToSearch);

  /**
   * Sets the data of the element.
   * 
   * @param elementData
   */
  void set(T elementData);

  /**
   * Merges the passed {@code PartitionElement} to the component to which this element belongs.
   * 
   * @param elementToMerge
   * @return
   */
  PartitionElement<T> union(PartitionElement<T> elementToMerge);
}
