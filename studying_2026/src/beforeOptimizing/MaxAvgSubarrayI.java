package beforeOptimizing;

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
	
	//inputs: int [] nums, int k
	//output: maximum avg of k integers
	//error conditions/considerations: if a list is length 1, we can just return that number
    public static double findMaxAverage(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        int l=0;
        double maxAvg = Double.NEGATIVE_INFINITY;
        int length=0; //size of window
         
        if(nums.length == 1 && k == 1) {
        	return nums[0];
        }
        
        //1 [2 3] 4       k=3
        
        
        for(int r=0; r<nums.length; r++) {
        	set.add(nums[r]);
        	length=r-l+1; //length of the window
        	
        	
        	if(length>k) {
        		set.remove(nums[l]);
            	l++;
            	length=r-l+1; //length of the window
        	}
        	//[1] 2 3 4 5, k = 2
        	if(length==k) {
        		double avg = 0;
        		for(int s: set) {
        			avg+=s;
        		}
        		avg=avg/k;
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
     * Sliding window does not always require a Set or Map.

		Ask:
		"What information do I need to know about the current window?"
		
		No duplicates problem → HashSet
		Maximum average problem → running sum
     */
    
    //Issue: using hashset disallows duplicates, and in this problem, it's okay to have duplicates
    //so instead of using a set, we can actually just track sums
    //BUT core logic was good, just wrong data structure
    //Goal: parse out:
//    	What variables do I need?
//    	What represents my current window?
//    	What extra information do I need to keep about the window?
//    	When do I remove something from the left?
//    	When do I update my answer?
    
