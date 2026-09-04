package shared.sorting;

import java.util.Arrays;

public class SelectionSort {
	public static void sort(int[] arr) {
	    int n = arr.length;

	    // Step 1: Move boundary of unsorted subarray one element at a time
	    for (int i = 0; i < n - 1; i++) {
	        int minIndex = i;  // Assume current position holds the minimum

	        // Step 2: Find the minimum element in the remaining unsorted array
	        for (int j = i + 1; j < n; j++) {
	            if (arr[j] < arr[minIndex]) {
	                minIndex = j;
	            }
	        }

	        // Step 3: Swap the found minimum with the first unsorted element
	        if (minIndex != i) {
	            int temp = arr[i];
	            arr[i] = arr[minIndex];
	            arr[minIndex] = temp;
	        }
	    }
	}

	public static void main(String[] args) {
	    int[] arr = {9, 3, 6, 1, 8, 5, 5, 2};
	    sort(arr);
	    System.out.println(Arrays.toString(arr)); // → [1, 2, 3, 5, 5, 6, 8, 9]
	}

	/*
	 * Selection Sort Complexity
	 * --------------------------
	 * Time:
	 *   Best:    O(n^2)  - still scans remaining array every time
	 *   Average: O(n^2)  - no shortcuts based on input
	 *   Worst:   O(n^2)  - consistent across all inputs
	 *
	 * Space:     O(1)    - sorts in-place, no extra array needed
	 */


	/*
	 * Selection Sort - Worst Case Input
	 * -----------------------------------
	 * Like heap sort, selection sort has no "bad input" — it's always
	 * O(n^2) regardless of the order of elements because:
	 *   - It always scans the entire remaining unsorted portion to find
	 *     the minimum, no matter how the elements are arranged
	 *   - It always does n-1 passes regardless of input order
	 *   - Each pass always does a full linear scan regardless of input order
	 *
	 * So all of these take roughly the same amount of work:
	 *   - Already sorted:   [1, 2, 3, 4, 5]
	 *   - Reverse sorted:   [5, 4, 3, 2, 1]
	 *   - Nearly sorted:    [1, 2, 3, 5, 4]
	 *   - Random order:     [3, 1, 5, 2, 4]
	 *
	 * One practical upside this buys you: selection sort does at most
	 * n-1 swaps total (one per pass), unlike heap sort or quicksort which
	 * can swap much more. That makes it occasionally worth it when writes
	 * are expensive (e.g. flash memory) even though comparisons aren't.
	 */
}
