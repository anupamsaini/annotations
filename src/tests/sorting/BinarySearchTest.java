package tests.sorting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import code.sorting.BinarySearch;

/**
 * Unit tests for {@link BinarySearch}.
 * @author Anupam Saini
 */
public class BinarySearchTest {

  @Test
  public void emptyInput_doesNotFindValue_passes() {
    int arr[] = {};
    assertEquals(-1, BinarySearch.search(arr, 1));
  }

  @Test
  public void singleValueInput_searchesSuccessfully() {
    int arr[] = {11};
    assertEquals(0, BinarySearch.search(arr, 11));
  }

  @Test
  public void singleValueInput_doesNotFindValue() {
    int arr[] = {10101010};
    assertEquals(-1, BinarySearch.search(arr, 1));
  }

  @Test
  public void arraySizedTwoInput_searchesSuccessfully() {
    int arr[] = {99, 1101};
    assertEquals(0, BinarySearch.search(arr, 99));
  }

  @Test
  public void arraySizedTwoInput_doesNotFindValue() {
    int arr[] = {99, 1101};
    assertEquals(-1, BinarySearch.search(arr, 100));
  }

  @Test
  public void arraySizedThreeInput_searchesSuccessfully() {
    int arr[] = {99, 1101, 34987};
    assertEquals(1, BinarySearch.search(arr, 1101));
  }

  @Test
  public void arraySizedThreeInput_doesNotFindValue() {
    int arr[] = {99, 1101, 34987};
    assertEquals(-1, BinarySearch.search(arr, 19));
  }

  @Test
  public void valueLessThanSmallestValueInArray_doesNotFindValue() {
    int arr[] = {4, 5, 6, 7, 78, 89, 90, 1091, 3458, 54545, 478236, 287236};
    assertEquals(-1, BinarySearch.search(arr, 1));
  }

  @Test
  public void valueGreaterThanLargestValueInArray_doesNotFindValue() {
    int arr[] = {4, 5, 6, 7, 78, 89, 90, 1091, 3458, 54545, 478236, 287236};
    assertEquals(-1, BinarySearch.search(arr, 99999999));
  }

  @Test
  public void allNegativeValuesInArray_findsValue() {
    int arr[] = {-8, -7, -6, -5, -4, -3, -2, -1, 0};
    assertEquals(7, BinarySearch.search(arr, -1));
  }

  @Test
  public void allValuesEqualInArray_returnsIndexOfFirstMatchedValue() {
    int arr[] = {4, 4, 4, 4, 4, 4};
    assertEquals(2, BinarySearch.search(arr, 4));
  }

  @Test
  public void iteratesAllValuesInArray_returnsIndexOfAllValuesInArray() {
    int[] testData =
      {23,47,52,129,141,171,186,205,264,295,320,343,353,484,496,785,793,826,833,981};
    for(int i =0 ; i< testData.length;  i++) {
      assertEquals(i, BinarySearch.search(testData, testData[i]));
    }
  }
}
