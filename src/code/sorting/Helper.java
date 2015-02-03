package code.sorting;

import java.util.Random;

/**
 * Common helper utilities.
 */
public final class Helper {
  private static final String COMMA = ",";

  public static int[] generateRandomInts(int range, int noOfElements) {
    Random randomGenerator = new Random();
    int generatedInts[] = new int[noOfElements];
    for (int i = 0; i < generatedInts.length; i++) {
      generatedInts[i] = randomGenerator.nextInt(range);
    }
    return generatedInts;
  }

  public static String prettyPrintArray(int arr[]) {
    StringBuilder sb = new StringBuilder(arr.length);
    for (int i : arr) {
      sb.append(i).append(COMMA);
    }
    return sb.toString();
  }

  public static boolean isInAscendingOrder(int arr[]) {
    if (arr.length < 2) {
      return true;
    }
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] > arr[i + 1]) {
        return false;
      }
    }
    return true;
  }

  public static void swap(int arr[], int left, int right) {
    int temp = arr[left];
    arr[left] = arr[right];
    arr[right] = temp;
  }
}
