package afterOptimizing;

import java.util.*;

public class TwoSum {
	//optimized solution -- Hashmaps!
	
	public static int[] twoSum(int[] nums, int target) {
		//convert array to Hashmap
		HashMap<Integer, Integer> map = new HashMap<>(); // O(n) space (worst case we store all elements), O(1) average insertion/lookup
		int[] result = new int[2]; //O(1) space and runtime
		for(int i=0; i< nums.length; i++) { //O(n) where n is nums length
			//check for complement (or target - current ind) existing in array
			//store number and index as you go through array
			int complement = target - nums[i]; //O(1) space and runtime
			if(map.containsKey(complement)) { //O(1) average runtime
				result[0] = map.get(complement); //smaller index first //O(1) runtime
				result[1] = i;
				return result;
			}else {
				map.put(nums[i],i); //O(1) runtime
			}
		}
	
		//runtime: O(n)
		//space: O(n)
		throw new IllegalArgumentException("No solution found"); //should not get here based on prompt
	}
	
	public static void main(String[] args) {
		int[] nums = {7, 0, 2, 4, 5};
        int target = 6;

        int[] result = twoSum(nums, target);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
	}

}
