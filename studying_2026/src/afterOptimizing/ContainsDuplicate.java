package afterOptimizing;

import java.util.HashSet;

public class ContainsDuplicate {

    public static boolean hasDuplicate(int[] nums) {
    	HashSet<Integer> set = new HashSet<>();
    	for(int i=0; i<nums.length;i++) {
    		if(set.contains(nums[i])) {
    			return true;
    		}
    			set.add(nums[i]);
    	}
    	return false;
    }
	
	public static void main(String[] args) {
		int[] test = {1, 2, 3, 3};
		System.out.println(hasDuplicate(test));
	}
}
