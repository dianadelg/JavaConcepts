package shared.sorting;

public class BubbleSort {

	public static void sortOptimized(int[] arr) {
		int n = arr.length;
        boolean swapped;
        
        // Outer loop runs up to n-1 times
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            // Inner loop compares adjacent elements, ignoring already sorted ones
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap array[j] and array[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    
                    // Mark that a swap occurred
                    swapped = true;
                }
            }
            
            // Optimization: If no elements were swapped, the array is already sorted
            if (!swapped) {
                break;
            }
        }
	}
	
	public static void main(String [] args) {
		int [] arr = {1, 5, 2, 9, 2};
		sortOptimized(arr);
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+"  ");
		}
	}
	
	/*
	 * Bubble Sort Complexity
	 * ----------------------
	 * Time:
	 *   Best:    O(n)   - array is already sorted, no swaps needed
	 *   Average: O(n²)  - each pass bubbles one element into place
	 *   Worst:   O(n²)  - array is reverse sorted, maximum swaps every pass
	 *
	 * Space:     O(1)   - sorts in-place, only a temp variable used
	 */
	
	/*
	 * When to Use Bubble Sort
	 * -----------------------
	 * Best times to use:
	 *   - Learning/teaching           - simple to understand and visualize
	 *   - Nearly sorted data          - optimized version exits early, approaches O(n)
	 *   - Memory is tight             - sorts in-place, only O(1) extra space
	 *
	 * Worst times to use:
	 *   - Large datasets              - O(n²) becomes very slow as n grows
	 *   - Production code             - insertion sort is faster with less overhead
	 *   - Reverse sorted data         - every pass does maximum swaps, true worst case
	 */
	
//	Insertion Sort — picks up one element and finds its correct spot by sliding it left through the sorted portion. It's like picking up a playing card and inserting it in the right place in your hand.
//	Bubble Sort — walks through the whole array comparing neighbors and swapping them if they're in the wrong order. It never "places" an element directly — it just keeps nudging things in the right direction until everything settles.
//
//	A simple way to think about it:
//
//	Insertion sort asks "where does this element belong?" and puts it there directly
//	Bubble sort asks "are these two neighbors in the wrong order?" and swaps them — over and over until done
	
	
	
//	In practice they're both O(n²) and both work well on small/nearly sorted data, but insertion sort is generally faster because:
//
//	It does fewer comparisons on average
//	It moves elements by shifting, not swapping (swapping takes 3 operations, shifting takes 1)
//	Most people consider bubble sort strictly worse — it's mainly taught because it's easy to visualize, not because it's useful
	
}
