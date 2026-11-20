package beforeOptimizing;

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
	
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //we know nums1 length is m
    	//and nums 2 length is n
    	
    	//kind of a hack but couldn't we combine the array as one and then sort?
    	for(int i=0; i<n; i++) { //O(n) runtime
    		nums1[m+i] = nums2[i]; //O(1) done n times at max
    	}
    	
    	//once out here, we have [1, 2, 4, 6, 2, 3, 6, 8]
    	Arrays.sort(nums1); //O(((m+n) log(m+n))
    }
    
    //Runtime: ((m+n) log(m+n)) + O(n) --> ((m+n) log(m+n)) (we sort all m+n elements)
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
