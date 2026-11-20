package afterOptimizing;

import java.util.Arrays;

public class MergeTwoSortedArrays {
	
//	You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m 
	//and n, representing the number of elements in nums1 and nums2 respectively.
//
//	Merge nums1 and nums2 into a single array sorted in non-decreasing order.
//
//	The final sorted array should not be returned by the function, but instead be stored inside
	//the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements 
	//denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

	//[1, 2, 4, 7, 0, 0, 0, 0];
	//[2, 3, 6, 8];
	
	//sorted input is a major clue that we can exploit the ordering instead of sorting again
	//The trick with Merge Sorted Array is that we have to modify nums1 without overwriting values we haven't processed yet.
	//So we will merge from right left
	
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
    	
    	int p = nums1.length-1; //pointer starting at the end of the 0s in nums1 array
    	int p1 = m-1; //at the last number of nums1
    	int p2 = n-1; //at the last number of nums2
    	
    	//idea -- we want to compare the values backwards
    	//largest values will be stored at the end of the 0s and then so on
    	
    	while(p1>=0&&p2>=0) {
    		//compare values
    		if(nums1[p1]<nums2[p2]) {
    			//putp2 in last spot at index p
    			//decrease p2, decrease p
    			nums1[p] = nums2[p2];
    			p--;
    			p2--;
    		}else {
    			//either p1> or equal. We can do the same thing
    			//put p1 in last spot at index p
    			//decrease p1, decrease p
    			nums1[p] = nums1[p1];
    			p--;
    			p1--;
    		}
    	}
    	
    	//if we get something like [11, 12, 13, 0, 0, 0] and [1,2,3]
    	//index becomes -1 for one of the arrays but the other is not merged correctly
    	//[10, 11, 12, 10, 11, 12] and the pointer looking at nums2 [1,2,3] did not move
    	//Here's the trick: we only care whether nums2 has leftovers
    	
    	/*
    	 * nums1 is both an input array AND our final destination.
    	 * nums2 is an input array only. Its values must all be transferred into nums1.
    	 * We aren't ignoring p1 because it's unimportant. We're ignoring it because, once 
    	 * the main loop ends, any remaining original nums1 values are already where they belong
    	 */
    	
    	while (p2 >= 0) {
    	    nums1[p] = nums2[p2];
    	    p--;
    	    p2--;
    	}
    	
    }
    
    //Runtime: O(n+m)
    //Space: O(1)
    
    
    public static void main(String[] args) {
    	int[] nums1 = {1, 2, 4, 7, 0, 0, 0, 0};
    	int[] nums2 = {2, 3, 6, 8};
    	
    	merge(nums1, 4, nums2, 4);
    	for(int num: nums1) {
    		System.out.print(num + " ");
    	}
    }
	
}

