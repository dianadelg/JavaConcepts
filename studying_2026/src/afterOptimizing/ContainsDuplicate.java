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
	
	/*
	 * CONTAINS DUPLICATE PATTERN / THINGS TO REMEMBER
	 *
	 * A/N: WOOO I GOT OPTIMIZED FIRST TRY!!!  
	 * -------------------------------------------------
	 * 1. CORE IDEA
	 * -------------------------------------------------
	 * If I need to know whether I have seen something before,
	 * use a HashSet.
	 *
	 * HashSet answers:
	 *
	 *     "Have I seen this value already?"
	 *
	 * in O(1) average time.
	 *
	 * -------------------------------------------------
	 *
	 * 2. WHY THIS WORKS
	 * -------------------------------------------------
	 * Loop through nums once.
	 *
	 * For each number:
	 *
	 *     if set already contains number:
	 *         duplicate found -> return true
	 *
	 *     else:
	 *         add number to set
	 *
	 * If loop finishes:
	 *
	 *     no duplicates -> return false
	 *
	 * -------------------------------------------------
	 *
	 * 3. BIG-O
	 * -------------------------------------------------
	 * Time:
	 *
	 *     O(n)
	 *
	 * because each number is checked once.
	 *
	 * Space:
	 *
	 *     O(n)
	 *
	 * because worst case all numbers are unique,
	 * so the set stores all n values.
	 * 
	 * A HashSet itself can use O(n) space, while its operations are O(1) time.
	 *
	 * -------------------------------------------------
	 *
	 * 4. PATTERN RECOGNITION
	 * -------------------------------------------------
	 * If problem asks:
	 *
	 *     duplicates
	 *     seen before
	 *     uniqueness
	 *     membership lookup
	 *
	 * think:
	 *
	 *     HashSet
	 *
	 * Similar problems:
	 *
	 *     Two Sum
	 *     Longest Substring Without Repeating Characters
	 *
	 */
}
