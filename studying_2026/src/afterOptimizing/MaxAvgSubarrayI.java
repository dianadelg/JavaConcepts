package afterOptimizing;

import java.util.HashSet;

public class MaxAvgSubarrayI {

	/*
	 * You are given an integer array nums consisting of n elements, and an integer k.
	 * Find a contiguous subarray whose length is equal to k that has the maximum average 
	 * value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
	 */
	
	/*
	 * Input: nums = [1,12,-5,-6,50,3], k = 4
		Output: 12.75000
		Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
	 */
	
	 /*     CONSTRAINTS
	        n == nums.length
			1 <= k <= n <= 105
			-104 <= nums[i] <= 104
	 */
	
	public static double findMaxAverage(int[] nums, int k) {
	    int l = 0;
	    double currentSum = 0;
	    double maxAvg = Double.NEGATIVE_INFINITY;

	    for (int r = 0; r < nums.length; r++) {
	        // Add the new right-side element
	        currentSum += nums[r];

	        int length = r - l + 1;

	        // If the window is too large, remove the left-side element
	        if (length > k) {
	            currentSum -= nums[l];
	            l++;
	        }

	        // Once the window has exactly k elements, calculate its average
	        if (r - l + 1 == k) {
	            double avg = currentSum / k;
	            maxAvg = Math.max(maxAvg, avg);
	        }
	    }

	    return maxAvg;
	}
    
    public static void main(String[] args) {
    	// Test Case 1: Standard Mixed Numbers
        int[] nums1 = {1, 12, -5, -6, 50, 3};
        int k1 = 4;
        System.out.println("Test 1 Result: " + findMaxAverage(nums1, k1) + " (Expected: 12.75)");

        // Test Case 2: Single Element Array
        int[] nums2 = {5};
        int k2 = 1;
        System.out.println("Test 2 Result: " + findMaxAverage(nums2, k2) + " (Expected: 5.0)");

        // Test Case 3: All Negative Numbers
        int[] nums3 = {-1, -12, -5, -6, -50, -3};
        int k3 = 2;
        System.out.println("Test 3 Result: " + findMaxAverage(nums3, k3) + " (Expected: -5.5)");

        // Test Case 4: All Positive Increasing Sequence
        int[] nums4 = {0, 1, 1, 3, 3};
        int k4 = 4;
        System.out.println("Test 4 Result: " + findMaxAverage(nums4, k4) + " (Expected: 2.0)");

        // Test Case 5: Window Size Equals Array Length
        int[] nums5 = {4, 0, 4, 3, 3};
        int k5 = 5;
        System.out.println("Test 5 Result: " + findMaxAverage(nums5, k5) + " (Expected: 2.8)");
    }
    }
    
    
    /*
     * MAXIMUM AVERAGE SUBARRAY I
     * FIXED SLIDING WINDOW PATTERNS / THINGS TO REMEMBER
     *
     * -------------------------------------------------
     * 1. SUBARRAY VS SUBSEQUENCE
     * -------------------------------------------------
     * SUBARRAY:
     * - elements must be continuous
     * - cannot skip elements
     *
     * Example:
     *
     *     [1, 2, 3, 4]
     *
     *     [2,3]   -> valid subarray
     *     [1,3]   -> NOT a subarray
     *
     * This problem specifically asks for:
     *
     *     EXACTLY k CONTIGUOUS ELEMENTS
     *
     * -------------------------------------------------
     *
     * 2. FIXED SLIDING WINDOW PATTERN
     * -------------------------------------------------
     * The window is:
     *
     *     nums[l...r]
     *
     * where:
     *
     *     l = left side of window
     *     r = right side of window
     *
     * Unlike variable sliding window,
     * this window ALWAYS stays the same size.
     *
     * -------------------------------------------------
     *
     * 3. MAIN FIXED WINDOW RULE
     * -------------------------------------------------
     *
     * ALWAYS:
     *
     *     move R
     *
     * IF WINDOW BECOMES TOO BIG:
     *
     *     remove nums[l]
     *     move l++
     *
     * IF WINDOW SIZE == k:
     *
     *     evaluate the answer
     *
     * IMPORTANT:
     *
     * We DO NOT shrink because of a condition.
     *
     * We ONLY shrink because the window
     * became larger than k.
     *
     * -------------------------------------------------
     *
     * 4. WHAT INFORMATION DOES THE WINDOW NEED?
     * -------------------------------------------------
     * Before choosing a data structure ask:
     *
     *     "What information do I actually need
     *      about this window?"
     *
     * For this problem:
     *
     * We do NOT care about:
     *
     *     order
     *     duplicates
     *     every individual value
     *
     * We ONLY care about:
     *
     *     the SUM
     *
     * Therefore:
     *
     *     store currentSum
     *
     * instead of the whole window.
     *
     * -------------------------------------------------
     *
     * 5. WHY NOT A HASHSET?
     * -------------------------------------------------
     * HashSet removes duplicates.
     *
     * Example:
     *
     *     Window:
     *
     *         [1,1]
     *
     * HashSet becomes:
     *
     *         {1}
     *
     * which no longer represents the window.
     *
     * HashSet only works when the problem
     * cares about UNIQUE values.
     *
     * -------------------------------------------------
     *
     * 6. WHY NOT AN ARRAYLIST?
     * -------------------------------------------------
     * An ArrayList WOULD work correctly.
     *
     * It preserves:
     *
     *     duplicates
     *     order
     *
     * But every window requires:
     *
     *     recomputing the sum
     *
     * Example:
     *
     *     sum = 0;
     *
     *     for(each number)
     *         sum += number;
     *
     * This makes every window O(k).
     *
     * Also:
     *
     *     window.remove(0)
     *
     * is O(k) because all remaining elements
     * shift left.
     *
     * Correct...
     *
     * but inefficient.
     *
     * -------------------------------------------------
     *
     * 7. RUNNING SUM TRICK
     * -------------------------------------------------
     * Instead of storing:
     *
     *     [12, -5, -6, 50]
     *
     * store:
     *
     *     currentSum = 51
     *
     * When R enters:
     *
     *     currentSum += nums[r]
     *
     * When L leaves:
     *
     *     currentSum -= nums[l]
     *
     * Never recompute the sum.
     *
     * Simply update it.
     *
     * -------------------------------------------------
     *
     * 8. WINDOW SIZE FORMULA
     * -------------------------------------------------
     * Current window size:
     *
     *     (r - l) + 1
     *
     * Example:
     *
     *     l = 3
     *     r = 6
     *
     * window size:
     *
     *     (6 - 3) + 1 = 4
     *
     * If:
     *
     *     size > k
     *
     * remove nums[l]
     * move l++
     *
     * -------------------------------------------------
     *
     * 9. BIG-O PATTERN
     * -------------------------------------------------
     * Every element:
     *
     * enters the window once
     * leaves the window once
     *
     * Every operation:
     *
     * add to currentSum
     * subtract from currentSum
     * update max
     *
     * is O(1).
     *
     * Therefore:
     *
     * Time:
     *
     *     O(n)
     *
     * Space:
     *
     *     O(1)
     *
     * because we only store:
     *
     *     l
     *     r
     *     currentSum
     *     maxAverage
     *
     * -------------------------------------------------
     *
     * 10. COMMON FIXED WINDOW SIGNALS
     * -------------------------------------------------
     * If the problem asks for:
     *
     * - exactly k elements
     * - exactly k consecutive numbers
     * - k-length subarray
     * - k-length substring
     * - maximum/minimum/average of every k elements
     *
     * THINK:
     *
     *     FIXED SLIDING WINDOW
     *
     * -------------------------------------------------
     *
     * 11. FIXED vs VARIABLE SLIDING WINDOW
     * -------------------------------------------------
     * FIXED WINDOW
     *
     * Window size:
     *
     *     ALWAYS k
     *
     * Shrink because:
     *
     *     window became too large
     *
     * Uses:
     *
     *     if(windowSize > k)
     *
     *
     * VARIABLE WINDOW
     *
     * Window size:
     *
     *     changes constantly
     *
     * Shrink because:
     *
     *     window became INVALID
     *
     * Uses:
     *
     *     while(window is invalid)
     *
     * -------------------------------------------------
     *
     * 12. IMPORTANT INTERVIEW TAKEAWAY
     * -------------------------------------------------
     * The sliding window itself never changes.
     *
     * What changes is:
     *
     *     the information maintained
     *     about that window.
     *
     * Ask yourself BEFORE coding:
     *
     *     "What single piece of information
     *      do I need to know about my current
     *      window?"
     *
     * Examples:
     *
     * Longest Substring Without Repeating:
     *
     *     HashSet<Character>
     *
     * Maximum Average Subarray:
     *
     *     currentSum
     *
     * Character Frequency Problems:
     *
     *     HashMap<Character,Integer>
     *
     * Sliding Window Maximum:
     *
     *     Deque
     *
     * The data structure is chosen based on
     * the QUESTION, not because it's a
     * sliding window problem.
     *
     */
    
