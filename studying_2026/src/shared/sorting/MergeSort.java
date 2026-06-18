package shared.sorting;

import java.util.Arrays;

public class MergeSort {
	
	public static void sort(int[] arr, int left, int right) {//recursive! Calls itself twice, once for the left and once for the right
        if (left < right) {
            int mid = left + (right - left) / 2;

            sort(arr, left, mid);       // Sort left half
            sort(arr, mid + 1, right);  // Sort right half
            merge(arr, left, mid, right); // Merge both halves
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        // Sizes of the two halves
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        // Merge the temp arrays back
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else               arr[k++] = R[j++];
        }

        // Copy any remaining elements
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void main(String[] args) {
        int[] arr = {9, 3, 6, 1, 8, 2};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr)); // → [1, 2, 3, 6, 8, 9]
    }
    
    //Visual representation: https://www.youtube.com/watch?v=4VqmGXwpLqc
    //usually done recursively -- divide and conquer, break problem into smaller problems
    //to solve it. Split in half until left with individual items
    
    /*
     * Merge Sort Complexity
     * ---------------------
     * Time:
     *   Best:    O(n log n)  - always splits and merges regardless of input
     *   Average: O(n log n)  - no best case shortcut
     *   Worst:   O(n log n)  - consistent across all inputs
     *
     * Space:     O(n)        - temp arrays needed to hold halves during merging
     */

}
