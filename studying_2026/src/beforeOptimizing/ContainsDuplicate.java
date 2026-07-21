package beforeOptimizing;

import java.util.HashSet;

public class ContainsDuplicate {
	
	//Given an integer array nums, return true if any value appears more than once in the 
	//array, otherwise return false.
	
	//Example:
//	Input: nums = [1, 2, 3, 3]
//	Output: true

    public static boolean hasDuplicate(int[] nums) {
        //store in hashset as you pass. If element exists already, return true
    	//and end algo immediately
    	//worst case O(n) -- no elements have duplicates
    	//Hashset -- O(n) space, worst case all items in set
    	HashSet<Integer> set = new HashSet<>();
    	for(int i=0; i<nums.length;i++) {
    		if(set.contains(nums[i])) {
    			return true;
    		}else {
    			set.add(nums[i]);
    		}
    	}
    	//if out here, means made it through loop. Return false
    	return false;
    }
	
	public static void main(String[] args) {
		int[] test = {1, 2, 3, 3};
		System.out.println(hasDuplicate(test));
		
	}
	
}
