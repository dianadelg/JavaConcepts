package shared.sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Shift elements greater than key one position to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 3, 6, 1, 8, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr)); // → [1, 2, 3, 6, 8, 9]
    }
    
//Visual representation: https://www.youtube.com/watch?v=JU767SDMDvA
    
//      Insertion sort just:
//
//    	Picks up the next unsorted element
//    	Slides it left until it finds its correct spot
//    	Repeats until everything is sorted
    
    /*
     * Insertion Sort Complexity
     * -------------------------
     * Time:
     *   Best:    O(n)   - array is already sorted
     *   Average: O(n²)  - each element shifts partway through sorted portion
     *   Worst:   O(n²)  - array is reverse sorted, every element shifts fully
     *
     * Space:     O(1)   - sorts in-place, only a few variables used
     */
    
//    Key Characteristics
//
//    Stable — equal elements maintain their original order
//    In-place — no extra array needed
//    Adaptive — faster on nearly-sorted data (approaches O(n))
//    Best use case — small or nearly-sorted arrays; often used as a finishing step in hybrid sorts like Timsort
}