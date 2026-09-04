package shared.sorting;

import java.util.Arrays;

public class QuickSort {
	public static void sort(int[] arr) {
	    quicksort(arr, 0, arr.length - 1);
	}

	private static void quicksort(int[] arr, int low, int high) {
	    if (low < high) {
	        // Partition the array and get the pivot's final index
	        int pivotIndex = partition(arr, low, high);

	        // Recursively sort the left and right subarrays
	        quicksort(arr, low, pivotIndex - 1);
	        quicksort(arr, pivotIndex + 1, high);
	    }
	}

	private static int partition(int[] arr, int low, int high) {
	    int pivot = arr[high];  // Choose last element as pivot
	    int i = low - 1;        // Index of smaller element boundary

	    for (int j = low; j < high; j++) {
	        if (arr[j] < pivot) {
	            i++;
	            int temp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = temp;
	        }
	    }

	    // Place pivot in its correct sorted position
	    int temp = arr[i + 1];
	    arr[i + 1] = arr[high];
	    arr[high] = temp;

	    return i + 1;
	}

	public static void main(String[] args) {
	    int[] arr = {9, 3, 6, 1, 8, 5, 5, 2};
	    sort(arr);
	    System.out.println(Arrays.toString(arr)); // → [1, 2, 3, 5, 5, 6, 8, 9]
	}

	/*
	 * Quicksort Complexity
	 * ---------------------
	 * Time:
	 *   Best:    O(n log n)  - pivot splits array evenly
	 *   Average: O(n log n)  - random pivots average out well
	 *   Worst:   O(n^2)      - pivot is always smallest/largest element
	 *
	 * Space:     O(log n)    - recursion stack (in-place partitioning)
	 */


	/*
	 * Quicksort - Worst Case Input
	 * -----------------------------
	 * Unlike heap sort, quicksort's performance depends heavily on pivot choice
	 * and input order, because:
	 *   - This implementation always picks the last element as pivot (see RandomizedQuickSort.java for randomized one)
	 *   - If the array is already sorted (or reverse sorted), the pivot is
	 *     always the min or max element, splitting n elements into 0 and n-1
	 *   - That means n recursive calls deep, each doing O(n) partition work
	 *
	 * So these inputs behave very differently:
	 *   - Already sorted:   [1, 2, 3, 4, 5]        → O(n^2), worst case
	 *   - Reverse sorted:   [5, 4, 3, 2, 1]        → O(n^2), worst case
	 *   - Nearly sorted:    [1, 2, 3, 5, 4]        → close to worst case
	 *   - Random order:     [3, 1, 5, 2, 4]        → close to O(n log n)
	 *
	 * Fix: use a randomized or median-of-three pivot to avoid worst-case
	 * behavior on sorted/adversarial input.
	 */
}
