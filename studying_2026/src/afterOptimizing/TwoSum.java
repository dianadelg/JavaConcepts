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
	
	/*
	 * TWO SUM PATTERNS / THINGS TO REMEMBER
	 *
	 * 1. BRUTE FORCE PATTERN
	 * -------------------------------------------------
	 * The first solution checks every pair:
	 *
	 *     for(int i = 0; i < nums.length - 1; i++) {
	 *         for(int j = i + 1; j < nums.length; j++) {
	 *             if(nums[i] + nums[j] == target) {
	 *                 return new int[] {i, j};
	 *             }
	 *         }
	 *     }
	 *
	 * This works, but it is slower.
	 *
	 * Time: O(n^2)
	 * Space: O(1)
	 *
	 * -------------------------------------------------
	 *
	 * 2. HASHMAP OPTIMIZATION PATTERN
	 * -------------------------------------------------
	 * Instead of checking every pair, store numbers already seen.
	 *
	 * For each number:
	 *
	 *     complement = target - nums[i]
	 *
	 * Then ask:
	 *
	 *     "Have I already seen the number I need?"
	 *
	 * If yes, return:
	 *
	 *     map.get(complement), i
	 *
	 * If no, store:
	 *
	 *     nums[i], i
	 *
	 * Time: O(n)
	 * Space: O(n)
	 *
	 * -------------------------------------------------
	 *
	 * 3. ORDER MATTERS
	 * -------------------------------------------------
	 * Check the map BEFORE inserting the current number.
	 *
	 * Correct:
	 *
	 *     if(map.containsKey(complement)) {
	 *         return new int[] {map.get(complement), i};
	 *     }
	 *
	 *     map.put(nums[i], i);
	 *
	 * This prevents accidentally using the same element twice.
	 *
	 * Important for cases like:
	 *
	 *     nums = [3, 3], target = 6
	 *
	 * -------------------------------------------------
	 *
	 * 4. COMPLEMENT FORMULA
	 * -------------------------------------------------
	 * If:
	 *
	 *     nums[i] + x = target
	 *
	 * then:
	 *
	 *     x = target - nums[i]
	 *
	 * That x is the complement.
	 *
	 * This is the key idea of Two Sum.
	 *
	 * -------------------------------------------------
	 *
	 * 5. HASHMAP CONTENTS
	 * -------------------------------------------------
	 * Store:
	 *
	 *     number -> index
	 *
	 * Example:
	 *
	 *     map.put(nums[i], i);
	 *
	 * Do NOT just store the number.
	 * The problem asks for indices, not values.
	 *
	 * -------------------------------------------------
	 *
	 * 6. BIG-O NOTES
	 * -------------------------------------------------
	 * HashMap lookup:
	 *
	 *     containsKey()
	 *     get()
	 *     put()
	 *
	 * are O(1) average time.
	 *
	 * The loop runs n times.
	 *
	 * Final:
	 *
	 *     Time: O(n)
	 *     Space: O(n)
	 *
	 * Space is O(n) because in the worst case,
	 * the map stores every number before finding the answer.
	 *
	 * -------------------------------------------------
	 *
	 * 7. INTERVIEW PATTERN RECOGNITION
	 * -------------------------------------------------
	 * If I see:
	 *
	 *     "Find two values that combine to target"
	 *
	 * THINK:
	 *
	 *     "Can I store what I've already seen?"
	 *
	 * If I see:
	 *
	 *     nested loop checking pairs
	 *
	 * THINK:
	 *
	 *     "Can a HashMap reduce this to one pass?"
	 *
	 */

}
