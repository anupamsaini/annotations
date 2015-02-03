package code.sorting;

import static code.sorting.Helper.swap;

/**
 * Sorts an array using the QuickSort algorithm.
 * @see <a href="QuickSort">http://en.wikipedia.org/wiki/Quicksort</a>
 */
public class QuickSort {

  public static void main(String args[]) {
    int arr[] = {9, 4, 1, 8, 2, 6, 10, 3};
    arr = Helper.generateRandomInts(10000000, 678000);
    long time = System.currentTimeMillis();
    quickSort(arr);
    System.out.println((System.currentTimeMillis() - time));
    // System.out.println(Helper.prettyPrintArray(arr));
    System.out.println(Helper.isInAscendingOrder(arr));

  }

  public static void quickSort(int arr[]) {
    if (arr.length > 0) {
      quickSort(arr, 0, arr.length - 1);
    }
  }

  private static void quickSort(int[] arr, int lIndex, int rIndex) {
    int pivot = partiton(arr, lIndex, rIndex);
    if (lIndex < pivot) {
      quickSort(arr, lIndex, pivot - 1);
    }
    if (rIndex > pivot) {
      quickSort(arr, pivot + 1, rIndex);
    }
  }

  private static int partiton(int[] arr, int lIndex, int rIndex) {
    int pivotIndex = rIndex;
    int firstLargerIndex = lIndex;

    for (int i = lIndex; i < rIndex; i++) {
      if (arr[i] < arr[pivotIndex]) {
        swap(arr, firstLargerIndex, i);
        ++firstLargerIndex;
      }
    }
    swap(arr, firstLargerIndex, pivotIndex);
    return firstLargerIndex;
  }
}
