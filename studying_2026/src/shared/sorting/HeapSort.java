package shared.sorting;

import java.util.Arrays;

public class HeapSort {

	public static void sort(int[] arr) {
        int n = arr.length;

        // Step 1: Build a max heap from the array
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Step 2: Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root (largest) to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;        // Assume root is largest
        int left  = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child

        if (left < n && arr[left] > arr[largest])   largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        // If largest is not root, swap and continue heapifying
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 3, 6, 1, 8, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr)); // → [1, 2, 3, 6, 8, 9]
    }
    
    /*
     * Heap Sort Complexity
     * --------------------
     * Time:
     *   Best:    O(n log n)  - always builds and extracts from heap
     *   Average: O(n log n)  - no shortcuts based on input
     *   Worst:   O(n log n)  - consistent across all inputs
     *
     * Space:     O(1)        - sorts in-place, no extra array needed
     */
    
    /*
     * When to Use Heap Sort
     * ---------------------
     * Best times to use:
     *   - Memory is tight             - O(1) space unlike merge sort's O(n)
     *   - Need guaranteed performance - always O(n log n), no worst case surprises
     *   - Large datasets              - holds up well as n grows
     *
     * Worst times to use:
     *   - Stability is required       - does not preserve order of equal elements
     *   - Nearly sorted data          - no adaptive advantage, insertion sort wins
     *   - Cache performance matters   - heap's jumping around in memory is cache-unfriendly
     */
    
    /*
     * Heap Sort - Worst Case Input
     * ----------------------------
     * Heap sort has no "bad input" — it is always O(n log n) regardless
     * of the order of elements because:
     *   - It always builds the full heap first regardless of input order
     *   - It always does n extractions regardless of input order
     *   - Each extraction always does log n work regardless of input order
     *
     * So all of these take roughly the same amount of work:
     *   - Already sorted:   [1, 2, 3, 4, 5]
     *   - Reverse sorted:   [5, 4, 3, 2, 1]
     *   - Nearly sorted:    [1, 2, 3, 5, 4]
     *   - Random order:     [3, 1, 5, 2, 4]
     */
    
	
}
