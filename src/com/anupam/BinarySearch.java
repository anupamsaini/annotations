package com.anupam;

/**
 * Binary search operations on a sorted array.
 * @see <a href="Binary Search">http://en.wikipedia.org/wiki/Binary_search_algorithm</a>
 *
 * @author Anupam Saini
 */
public class BinarySearch {

  private static final int KEY_NOT_FOUND = -1;

  /***
   * Searches a sorted array for a value, In case of successful match returns the index -1
   * otherwise.
   *
   * @param arr the array to be searched
   * @param value the value to be searched
   * @return the index at which value is found or -1
   */
  public static int search(int arr[], int value) {
    if (arr.length == 0) {
      return -1;
    }
    return search(arr, value, 0, arr.length - 1);
  }

  private static int search(int arr[], int value, int left, int right) {
    //TODO(anupam): Definately not the ideal implementation. Clean up.
    int mid = left + (right - left) / 2;
    if (value == arr[mid]) {
      return mid;
    } else if (left >= right) {
      return KEY_NOT_FOUND;
    } else if (value > arr[mid]) {
      left = mid + 1;
      return search(arr, value, left, right);
    } else if (value < arr[mid]) {
      right = mid - 1;
      return search(arr, value, left, right);
    }
    return KEY_NOT_FOUND;
  }
}
