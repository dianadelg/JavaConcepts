package afterOptimizing;

import java.util.*;

public class MaximumSubarray {

//	Given an integer array nums, find the subarray with the largest sum, and return its sum.
	
	//SOLUTION: Kadane's algo
	/*
	 * track currentSum and maxSum
	 * if you ever get a currentSum <0, just set it to be 0 because it won't help our final sum (it's not helping)
	 * 	think of like [-10 4] --> 10 + 4 = -6, having just 4 is actually better
	 * if you have a currentSum >0, keep it. It helps
	 */
	
	/*
	 * Don't think of Kadane as "any contiguous problem." Think of it as:
	   "Maximum (or minimum) sum over a contiguous subarray where there is no natural sliding-window rule."
	*/
	
	
    public static int maxSubArray(int[] nums) {
    	int maxSum = Integer.MIN_VALUE; //start with the smallest value for an int (negative)
    	int currentSum = 0;
    	
    	for(int i=0; i<nums.length; i++) { //O(n) time
    		currentSum += nums[i]; //current sum increases by value we're looking at, even if negative
    		maxSum = Integer.max(currentSum, maxSum);
    		
    		if(currentSum < 0) {
    			//you do not want to keep it moving forward (think of backpack idea, carrying it does not help)
    			currentSum=0; //reset
    		}
    	}
    	//Runtime: O(n)
    	//Space: O(1)
    	
    	return maxSum;
    }
    
    public static void main (String[] args) {
    	// Test Case 1: Standard Mixed Numbers (Classic LeetCode example)
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test 1 Result: " + maxSubArray(nums1) + " (Expected: 6)");
        // Explanation: [4, -1, 2, 1] has the largest sum = 6.

        // Test Case 2: Single Element Array
        int[] nums2 = {1};
        System.out.println("Test 2 Result: " + maxSubArray(nums2) + " (Expected: 1)");
        // Explanation: Only one element, so the max sum is itself.

        // Test Case 3: Strictly Positive Numbers
        int[] nums3 = {5, 4, 1, 7, 8};
        System.out.println("Test 3 Result: " + maxSubArray(nums3) + " (Expected: 25)");
        // Explanation: Every number is positive, so the entire array gives the largest sum = 25.

        // Test Case 4: All Negative Numbers (Crucial edge case)
        int[] nums4 = {-2, -3, -1, -5};
        System.out.println("Test 4 Result: " + maxSubArray(nums4) + " (Expected: -1)");
        // Explanation: The single element -1 is the largest possible contiguous subsegment.

        // Test Case 5: Large Positive Peak Surrounded by Negatives
        int[] nums5 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test 5 Result: " + maxSubArray(nums5) + " (Expected: 6)");
        
    }
    
    /*
    --------------------------Kadane's Algorithm----------------------------------

    Problem Pattern:
    Find the MAXIMUM (or MINIMUM) SUM of a CONTIGUOUS (continuous) subarray.

    Examples:
    - Maximum Subarray
    - Largest Contiguous Sum
    - Best Contiguous Segment
    - Maximum Profit over a continuous range (sometimes)

    --------------------------------------------------------------------------------
    HOW TO RECOGNIZE KADANE'S
    --------------------------------------------------------------------------------

    Step 1:
    Is the problem CONTIGUOUS?

    Look for words like:
    - contiguous
    - continuous
    - consecutive
    - subarray

    If NO -> Not Kadane.

    If YES -> Continue.

    --------------------------------------------------------------------------------

    Step 2:
    Am I maximizing or minimizing the SUM?

    Examples:
    - Maximum Sum
    - Largest Sum
    - Highest Total
    - Minimum Sum

    If NO -> Probably another algorithm.

    If YES -> Continue.

    --------------------------------------------------------------------------------

    Step 3:
    Is the window size FIXED?

    Examples:
    - Exactly k elements
    - Length k

    If YES:

    => Fixed Sliding Window

    NOT Kadane.

    --------------------------------------------------------------------------------

    Step 4:
    Ask yourself:

    "What would make me move the LEFT pointer?"

    Examples:

    - Duplicate found
    - Window larger than k
    - Too many distinct characters
    - Sum exceeds a target

    If you can answer this:

    => Sliding Window

    If you CANNOT answer this...

    and the problem is asking for the maximum sum of a contiguous subarray...

    => Think Kadane.

    --------------------------------------------------------------------------------
    THE BIGGEST DIFFERENCE
    --------------------------------------------------------------------------------

    Sliding Window asks:

    "Is my window still VALID?"

    Kadane asks:

    "Does my previous running sum HELP this number,
    or does it HURT this number?"

    That is the entire algorithm.

    --------------------------------------------------------------------------------
    THE MAIN IDEA
    --------------------------------------------------------------------------------

    At every element, ask ONE question:

    Should I...

    1. Continue my current subarray?

    OR

    2. Throw it away and start a brand-new subarray here?

    Whichever gives the larger sum wins.

    --------------------------------------------------------------------------------
    WHAT THE VARIABLES MEAN
    --------------------------------------------------------------------------------

    currentSum

    The running total of the CURRENT subarray.

    It is NOT the final answer.

    Think:

    "My current momentum."

    --------------------------------------------------------------------------------

    maxSum

    The BEST subarray sum found ANYWHERE so far.

    Think:

    "My best answer."

    Do NOT confuse these.

    A currentSum smaller than maxSum is NOT "invalid."

    It only means:

    "This current candidate isn't the best answer YET."

    Future numbers may still make it the best.

    --------------------------------------------------------------------------------
    WHY currentSum STARTS AT 0
    --------------------------------------------------------------------------------

    Before we've processed any numbers:

    Running Total = 0

    0 means:

    "I haven't accumulated anything yet."

    NOT

    "My answer is 0."

    Do NOT initialize currentSum to Integer.MIN_VALUE.

    Why?

    Because:

    Integer.MIN_VALUE + nums[i]

    would still be an enormous negative number.

    We want the first running sum to naturally become:

    nums[0]

    Remember:

    0 + x = x

    --------------------------------------------------------------------------------
    WHY WE ADD nums[i] FIRST
    --------------------------------------------------------------------------------

    currentSum += nums[i];

    Every subarray ending at index i MUST include nums[i].

    So we always include the current element first.

    --------------------------------------------------------------------------------
    WHY WE RESET TO 0
    --------------------------------------------------------------------------------

    If currentSum becomes negative...

    It can NEVER help future numbers.

    Example:

    currentSum = -8

    Next number = 10

    Keep it:

    -8 + 10 = 2

    Start fresh:

    10

    Obviously:

    10 is better.

    A negative running total only hurts future sums.

    Think:

    Negative currentSum = carrying a backpack full of rocks.

    Drop it.

    Reset:

    currentSum = 0

    which means:

    "I am starting fresh on the NEXT iteration."

    --------------------------------------------------------------------------------
    IMPORTANT ORDER
    --------------------------------------------------------------------------------

    currentSum += nums[i];

    maxSum = Math.max(maxSum, currentSum);

    if(currentSum < 0)
        currentSum = 0;

    DO NOT reset before updating maxSum.

    Otherwise:

    [-3, -2, -5]

    would incorrectly return 0 instead of -2.

    --------------------------------------------------------------------------------
    RUNTIME
    --------------------------------------------------------------------------------

    Time: O(n)

    One pass through the array.

    Inside each iteration we perform several operations:

    - addition
    - comparison
    - if statement
    - assignment

    These are ALL constant-time operations.

    Example:

    4 operations × n elements

    = 4n

    Big-O ignores constants.

    O(4n)

    = O(n)

    --------------------------------------------------------------------------------
    SPACE
    --------------------------------------------------------------------------------

    O(1)

    Only storing:

    - currentSum
    - maxSum
    - loop variable

    No extra data structures.

    --------------------------------------------------------------------------------
    INTERVIEW CHECKLIST
    --------------------------------------------------------------------------------

    ✓ Contiguous / Continuous
    ✓ Maximum (or Minimum) SUM
    ✓ Variable-length subarray
    ✓ Positive and negative numbers
    ✓ No obvious Sliding Window rule
    ✓ Can't explain when LEFT should move
    ✓ Decision at every element is:

        Continue current subarray?

               OR

        Start a new one here?

    => Kadane's Algorithm

    --------------------------------------------------------------------------------
    MEMORY TRICKS
    --------------------------------------------------------------------------------

    Sliding Window

    "I manage a WINDOW."

    Question:

    "Is my window still valid?"

    ------------------------------------------------------

    Kadane

    "I manage MOMENTUM."

    Question:

    "Does my previous running sum help me,
    or is it just dragging me down?"

    Think:

    Positive currentSum = momentum

    Negative currentSum = backpack full of rocks

    Throw away the rocks.
    */
    	
    	 
	
	
	
	
}

