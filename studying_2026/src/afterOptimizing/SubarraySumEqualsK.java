package afterOptimizing;

import java.util.HashMap;

public class SubarraySumEqualsK {

//	Given an array of integers nums and an integer k, return the total number of 
//	subarrays whose sum equals to k.
//
//	A subarray is a contiguous non-empty sequence of elements within an array.
	
	//prefix sum algo: sum of everything from index 0 to here
    public static int subarraySum(int[] nums, int k) {
    	
    	//key is cumulative sum
    	//value is number of times we've reached that sum
        HashMap<Integer, Integer> prefixCounts = new HashMap<>();

        prefixCounts.put(0, 1); //before processing, our running sum is 0
        //allows us to detect a valid subarray that begins at index 0
        //THINK: before array starts: sum total is 0

        int currentSum = 0;
        int numberOfArraysK = 0;

        for (int num : nums) {//visit each number once
            currentSum += num; //add the current sum to the sums we've seen so far

            int neededPreviousSum = currentSum - k; //use equation
//“What earlier running total would leave exactly k between then and now?”
            
            if (prefixCounts.containsKey(neededPreviousSum)) {
                //check whether that earlier total exists
            	/*
            	 * ask: have we previously reached the running total I need?
            	 * If yes: at least one valid sub array ends here (visualization)
            	 * IF no: then no valid sub array ends at this position
            	 */
            	numberOfArraysK += prefixCounts.get(neededPreviousSum);
            	//add however many times we saw it. This is why we need the hashmap, this is the value
            }
            //we must check first so we compare the current position against earlier checkpoints only
            

            prefixCounts.put(
                currentSum,
                prefixCounts.getOrDefault(currentSum, 0) + 1
                //store the current prefix sum so we can use it later
                //if it never appeared, use 0
                //and then increase count + 1
                //store the updated count
            );
            //Time O(n) -- traverse elements only once, hashmap lookup is O(1)
            //Space O(n) -- in the worst case, every prefix sum is different so hashmap stores up to n+1 (+1 is for the zero sum in beginning)
        }

        return numberOfArraysK;
    	
    	
    }
    
    public static void main (String [] args) {
    	int [] nums = {1, 2, -1, 1, -3}; //3
    	System.out.println(subarraySum(nums, 2));
    	
    	int [] nums2 = {1, 2, -1, 1}; //3
    	System.out.println(subarraySum(nums2, 2));
    	
    	int [] nums3 = {2}; //1
    	System.out.println(subarraySum(nums3, 2));
    }
    
    /*
     * =======================================================
     * PREFIX SUM + HASHMAP
     * RECOGNITION GUIDE
     * =======================================================
     *
     * If the problem asks for:
     *
     * - exact subarray sum = k
     * - count the number of subarrays
     * - contiguous / continuous / consecutive
     * - subarray
     *
     * STOP and ask:
     *
     * "Can I make a SAFE local decision?"
     *
     * -------------------------------------------------------
     * SLIDING WINDOW?
     * -------------------------------------------------------
     *
     * Ask:
     *
     * "When this condition happens,
     * does moving LEFT definitely bring me
     * closer to a valid answer?"
     *
     * If YES:
     *
     *     Sliding Window
     *
     * Example:
     *
     * Positive numbers only
     *
     * sum > target
     *
     * Moving LEFT will ALWAYS decrease the sum.
     *
     * Safe.
     *
     * -------------------------------------------------------
     * PREFIX SUM?
     * -------------------------------------------------------
     *
     * If moving LEFT is NOT always safe...
     *
     * Example:
     *
     * nums = [3, -1]
     * k = 2
     *
     * At 3:
     *
     * Should I shrink?
     *
     * Maybe...
     *
     * But if I do,
     * I'll miss:
     *
     * [3, -1] = 2
     *
     * Because future numbers can change everything.
     *
     * There is NO safe local decision.
     *
     * Don't move pointers.
     *
     * Remember HISTORY instead.
     *
     * Think:
     *
     * Prefix Sum + HashMap
     */
	
}
