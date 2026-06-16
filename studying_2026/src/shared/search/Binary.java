package shared.search;

public class Binary {
    
	// Iterative
    public static int searchIterative(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoids int overflow vs (left+right)/2

            if (arr[mid] == target)  return mid;
            else if (arr[mid] < target) left = mid + 1;
            else                        right = mid - 1;
        }
        return -1;
    }

    // Recursive
    public static int searchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;

        if (arr[mid] == target)       return mid;
        else if (arr[mid] < target)   return searchRecursive(arr, target, mid + 1, right);
        else                          return searchRecursive(arr, target, left, mid - 1);
    }

    public static void main(String[] args) {
        int[] sorted = {1, 3, 5, 7, 9, 11, 13};

        System.out.println(searchIterative(sorted, 7));   // → 3
        System.out.println(searchIterative(sorted, 6));   // → -1

        System.out.println(searchRecursive(sorted, 11, 0, sorted.length - 1)); // → 5
    }
    
    
    /*
     * Binary Search Complexity
     * ------------------------
     * Time:
     *   Best:    O(1)      - target is the midpoint
     *   Average: O(log n)  - halves search space each step
     *   Worst:   O(log n)  - target not found after all halvings
     *
     * Space:
     *   Iterative: O(1)      - only a few pointers
     *   Recursive: O(log n)  - call stack grows with each halving
     */
    
    
    
//    Key Characteristics
//
//    Requires sorted data — will produce wrong results otherwise
//    Divide and conquer — eliminates half the remaining elements each iteration
//    Overflow-safe midpoint — left + (right - left) / 2 instead of (left + right) / 2
//    Iterative preferred in practice — same time complexity as recursive but O(1) vs O(log n) space
}
