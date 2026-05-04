package afterOptimizing;

import java.util.*;

public class TwoSum {
	//optimized solution -- Hashmaps!
	
	public static int[] twoSum(int[] nums, int target) {
		//convert array to Hashmap
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] result = new int[2];
		for(int i=0; i< nums.length; i++) {
			//check for complement (or target - current ind) existing in array
			//store number and index as you go through array
			int complement = target - nums[i];
			if(map.containsKey(complement)) {
				result[0] = map.get(complement); //smaller index first
				result[1] = i;
				return result;
			}else {
				map.put(nums[i],i);
			}
		}
		
		throw new IllegalArgumentException("No solution found"); //should not get here based on prompt
	}
	
	public static void main(String[] args) {
		int[] nums = {7, 0, 2, 4, 5};
        int target = 6;

        int[] result = twoSum(nums, target);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
	}

}
