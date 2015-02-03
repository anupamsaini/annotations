package code.sorting;

import java.util.List;

/**
 * Generic implementation of Binary search operations on a sorted array.
 * @see <a href="Binary Search">http://en.wikipedia.org/wiki/Binary_search_algorithm</a>
 *
 * @author Anupam Saini
 */
public class GenericBinarySearch<T extends Comparable<T>> {

  private static final int KEY_NOT_FOUND = -1;

  /***
   * Searches a sorted array for a value, In case of successful match returns the index -1
   * otherwise.
   *
   * @param sortedElements the array to be searched
   * @param value the value to be searched
   * @return the index at which value is found or -1
   */
  public int search(List<T> sortedElements, T value) {
    if (sortedElements.isEmpty()) {
      return -1;
    }

    return search(sortedElements, value, 0, sortedElements.size() - 1);
  }

  private int search(List<T> sortedEle, T value, int left, int right) {
    int mid = left + (right - left) / 2;
    if (value.compareTo(sortedEle.get(mid)) == 0) {
      return mid;
    } else if (left >= right) {
      return KEY_NOT_FOUND;
    } else if (value.compareTo(sortedEle.get(mid)) > 0) {
      left = mid + 1;
      return search(sortedEle, value, left, right);
    } else if (value.compareTo(sortedEle.get(mid)) < 0) {
      right = mid - 1;
      return search(sortedEle, value, left, right);
    }
    return KEY_NOT_FOUND;
  }
}
