package tests.sorting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.sorting.Helper;
import code.sorting.QuickSort;

/**
 * Unit tests for {@link QuickSort}.
 *
 * @author Anupam Saini
 */
public class QuickSortTest {

  @Test
  public void emptyInput_doesNothing() {
    int arr[] = {};
    QuickSort.quickSort(arr);
    assertEquals(0, arr.length);
    assertTrue(Helper.isInAscendingOrder(arr));
  }

  @Test
  public void singleInput_doesNothing() {
    int arr[] = {5};
    QuickSort.quickSort(arr);
    assertEquals(1, arr.length);
    assertEquals(5, arr[0]);
    assertTrue(Helper.isInAscendingOrder(arr));
  }

  @Test
  public void sortedInput_sorts() {
    int arr[] = {5, 4, 3, 2, 1, 0};
    QuickSort.quickSort(arr);
    assertTrue(Helper.isInAscendingOrder(arr));
  }

  @Test
  public void sortedInput_allNegatives_sorts() {
    int arr[] = {-5, -4, -3, -2, -1, 0};
    QuickSort.quickSort(arr);
    assertTrue(Helper.isInAscendingOrder(arr));
  }

  @Test
  public void unsortedInput_sorts() {
    int arr[] = {9, 4, 1, 8, 2, 6, 10, 3};
    QuickSort.quickSort(arr);
    assertTrue(Helper.isInAscendingOrder(arr));
  }

  @Test
  public void partiallySortedInput_sorts() {
    int arr[] = {4, 5, 6, 7, 3, 2, 1};
    QuickSort.quickSort(arr);
    assertTrue(Helper.isInAscendingOrder(arr));
  }

  @Test
  public void allValuesEqual_sorts() {
    int arr[] = {5, 5, 5, 5, 5, 5, 5};
    QuickSort.quickSort(arr);
    assertTrue(Helper.isInAscendingOrder(arr));
  }
}
