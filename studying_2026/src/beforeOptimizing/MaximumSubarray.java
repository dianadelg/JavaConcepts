package beforeOptimizing;

import java.util.*;

public class MaximumSubarray {

//	Given an integer array nums, find the subarray with the largest sum, and return its sum.

	
	//initial thoughts -- subarray is contiguous. Sliding window
	//input: int[] nums
	//output: largest sum
	//sliding window variable length
	//criteria: if sum+r < maxSum, remove l and + nums[r]
	//else, + nums[r]
	//we don't want to keep the elements, we just need the average
	
	//list will be at least 1 element
	

	//Okay so, the issue is that we can't actually use sliding window because removing L doesn't actually always help
	
	
	//[1 -2 4]
	
    public static int maxSubArray(int[] nums) {
    	int maxSum= Integer.MIN_VALUE;
    	
    	if(nums.length == 1) {
    		return nums[0];
    	}

    	//int[] nums3 = {5, 4, 1, 7, 8};
    	
    	int sum = 0;
    	List<Integer> list = new ArrayList<>();
    	for(int i=0; i<nums.length; i++) {
    		sum = 0;
    		sum += nums[i];
    		list.add(sum);
    		for(int j=i+1; j<nums.length; j++) {
    			sum += nums[j];
    			list.add(sum);
    		}
    	}
    	
    	for(int num:list) {
    		if(num > maxSum) {
    			maxSum = num;
    		}
    	}
    	
    	return maxSum;
    	//runtime O(n^2)
    	//space O(n^2) for list of numbers -- we actually don't need to keep the sums, can just track the max as we get a new one each time, dropping this to O(1)
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
    	
    	
    	//        int l=0;
//        int maxSum = Integer.MIN_VALUE;
//    	int totalSum=0;
//        
//        for(int r=0; r< nums.length; r++) {
//        	//I keep struggling with the first case, like does it start with an empty window or a window of one element
//        	//because empty, I'd start with add one element. But if one, I'd start with checking
//        	//[-2,1,-3,4,-1,2,1,-5,4]
//        	
//        	if(nums[r]+totalSum < maxSum) {
//        		//take out l
//        		//increase l
//        		totalSum=totalSum-nums[l];
//        		l++;
//        	}
//        	
//        	if(nums[r]+totalSum > maxSum) {
//        		
//        	}
       
	
	
	
	
}
