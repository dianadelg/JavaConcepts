package shared.search;

public class Sequential {
    // For arrays
    public static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return index if found
            }
        }
        return -1; // Not found
    }

    // Generic version (works with any type)
    public static <T> int search(T[] arr, T target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 7, 1, 9, 3};

        System.out.println(search(nums, 9));  // → 4
        System.out.println(search(nums, 5));  // → -1

        String[] words = {"apple", "banana", "cherry"};
        System.out.println(search(words, "banana")); // → 1
    }
    
//    Runtime Complexity Analysis
//	  Best  O(1) - Target is the first element
//	  Average  O(n/2) → O(n) - Target is somewhere in the middle
//    Worst  O(n) - Target is last or not present
//    Space Complexity: O(1) — only a loop counter is used; no extra data structures.
//
//    Key Characteristics
//    Works on unsorted data — unlike binary search, no sorting required
//    Simple to implement — no preconditions on the collection
//    Inefficient at scale — for large sorted datasets, prefer binary search O(log n)
//    Best use case — small or unsorted arrays, or linked lists (where random access isn't possible)
    
}
