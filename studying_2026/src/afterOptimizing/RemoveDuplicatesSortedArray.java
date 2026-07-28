package afterOptimizing;

public class RemoveDuplicatesSortedArray {
//	Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place 
//	such that each unique element appears only once. The relative order of the elements should be kept the same.
//
//	Consider the number of unique elements in nums to be k​​​​​​​​​​​​​​. After removing duplicates, return the number of unique elements k.
//
//	The first k elements of nums should contain the unique numbers in sorted order. The remaining elements beyond index 
//	k - 1 can be ignored.
	
//	Input: nums = [0,0,1,1,1,2,2,3,3,4]
//			Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
//			Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
//			It does not matter what you leave beyond the returned k (hence they are underscores).
	
    public static int removeDuplicates(int[] nums) {
    	//sorted makes me think pointers
    	//must do in place
    	
//    	i = explores every number.
//    	k = where the next unique number belongs
    	
        int k = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }

//        for(int num: nums) {
//        	System.out.print(num+  " ");
//        }
//    	System.out.println();
//    	
        return k;
        
    }
    
    public static void main (String[] args) {
    	int[] test = {0,0,1,1,1,2,2,3,3,4};
    	System.out.println(removeDuplicates(test));
    }
    
    /*
     * ============================================================
     * REMOVE DUPLICATES FROM SORTED ARRAY
     * TWO POINTERS — NOTES / TAKEAWAYS / PATTERN
     * ============================================================
     *
     *
     * RECOGNITION
     * -----------
     * The problem gives:
     *
     *      - a SORTED array,
     *      - duplicates,
     *      - modification IN PLACE,
     *      - and asks for the number of unique elements.
     *
     * The keyword is:
     *
     *      SORTED
     *
     * Since the array is sorted, duplicate values are always
     * adjacent.
     *
     * Therefore:
     *
     *      Sorted array + in-place compaction
     *      -> Two Pointers
     *
     *
     * THE TWO POINTERS
     * ----------------
     * i = read pointer
     *
     *      Explores every element in the array.
     *
     * k = write pointer
     *
     *      Points to where the NEXT unique value belongs.
     *
     *
     * MEMORY RULE
     * -----------
     *      i reads.
     *
     *      k writes.
     *
     *
     * WHY IS nums[k] ALWAYS THE CORRECT PLACE TO WRITE?
     * -------------------------------------------------
     * k is NOT just another pointer.
     *
     * k represents:
     *
     *      The number of unique values we've found so far.
     *
     * This is guaranteed by our invariant:
     *
     *      nums[0 ... k-1]
     *
     * already contains every unique value we've discovered,
     * packed together in sorted order.
     *
     * Since arrays are zero-indexed:
     *
     *      If k = 3,
     *
     * we've already found 3 unique values.
     *
     * Those values occupy indices:
     *
     *      0, 1, 2
     *
     * Therefore, the next unique value MUST belong at:
     *
     *      index 3
     *
     * which is exactly:
     *
     *      nums[k]
     *
     * After writing the new value:
     *
     *      nums[k] = nums[i];
     *
     * we increment:
     *
     *      k++;
     *
     * because we've now found one more unique value.
     *
     * Think of k as:
     *
     *      "The first empty slot immediately after all of
     *       the unique values."
     *
     *
     * Example:
     *
     * Original:
     *
     *      [0,0,1,1,1,2,2,3,3,4]
     *
     * Initially:
     *
     *      k = 1
     *
     * Why?
     *
     * Because the first value (0) is automatically unique.
     *
     * We've already accepted:
     *
     *      [0]
     *
     * So the next unique value belongs immediately after it.
     *
     * When we discover 1:
     *
     *      nums[1] = 1
     *
     * Now:
     *
     *      [0,1,...]
     *
     * We've found 2 unique values, so:
     *
     *      k = 2
     *
     * The next unique value now belongs at index 2.
     *
     * Every time we discover a new unique value:
     *
     *      - write it into nums[k]
     *      - increment k
     *
     * k always stays one position past the last unique value.
     *
     *
     * WHY DOES k START AT 1?
     * ----------------------
     * The first element is automatically unique because there
     * is nothing before it to compare against.
     *
     * Therefore:
     *
     *      nums[0]
     *
     * is already in its correct position.
     *
     * So:
     *
     *      k = 1
     *
     * means the next unique value should be written at index 1.
     *
     *
     * WHY DOES i START AT 1?
     * ----------------------
     * Each element is compared with the element immediately
     * before it:
     *
     *      nums[i] != nums[i - 1]
     *
     * Therefore i must begin at index 1.
     *
     *
     * MAIN DECISION
     * -------------
     * Every iteration asks:
     *
     *      "Is nums[i] different from nums[i - 1]?"
     *
     * If NO:
     *
     *      Duplicate.
     *
     *      Skip it.
     *
     * If YES:
     *
     *      New unique value.
     *
     *      Copy it to nums[k].
     *
     *      Increment k.
     *
     *
     * CORE LOGIC
     * ----------
     *
     *      if (nums[i] != nums[i - 1]) {
     *          nums[k] = nums[i];
     *          k++;
     *      }
     *
     *
     * WHY COMPARE WITH nums[i - 1]?
     * -----------------------------
     * Since the array is sorted, equal values are grouped
     * together.
     *
     * Example:
     *
     *      [0,0,1,1,1,2]
     *
     * A value is new exactly when it differs from the value
     * immediately before it.
     *
     * Example:
     *
     *      0 == 0 -> duplicate
     *      1 != 0 -> new value
     *      1 == 1 -> duplicate
     *      2 != 1 -> new value
     *
     *
     * WHAT nums[k] = nums[i] DOES
     * ---------------------------
     * It copies the newly discovered unique value into the
     * next available position at the front of the array.
     *
     * It does NOT delete values.
     *
     * It simply overwrites positions that are no longer
     * important.
     *
     * Example:
     *
     * Original:
     *
     *      [0,0,1,1,1,2]
     *
     * After writing 1:
     *
     *      [0,1,1,1,1,2]
     *
     * After writing 2:
     *
     *      [0,1,2,1,1,2]
     *
     * Only the first k positions matter.
     *
     *
     * IMPORTANT: VALUES VS INDICES
     * ----------------------------
     * nums[i] is a VALUE.
     *
     * i is an INDEX.
     *
     * nums[k] is a VALUE stored at index k.
     *
     * k is an INDEX.
     *
     * Never do:
     *
     *      current++;
     *
     * thinking you're moving through the array.
     *
     * That changes the VALUE.
     *
     * To move through the array:
     *
     *      i++
     *
     * changes the INDEX.
     *
     *
     * INVARIANT
     * ---------
     * At the start of every iteration:
     *
     *      nums[0 ... k - 1]
     *
     * contains every unique value we've found so far,
     * packed together in sorted order with no gaps.
     *
     * k equals the number of unique values found so far.
     *
     * Because arrays are zero-indexed,
     *
     *      k
     *
     * is also the first empty position immediately after
     * the compacted unique values.
     *
     * Therefore, whenever we discover a new unique value,
     * it MUST be written into nums[k].
     *
     *
     * WHY RETURN k?
     * -------------
     * k represents BOTH:
     *
     *      1. the number of unique values found
     *
     *      2. the next empty write position
     *
     * If:
     *
     *      k = 5
     *
     * then the meaningful part of the array is:
     *
     *      nums[0 ... 4]
     *
     * Everything after that can be ignored.
     *
     *
     * PLAIN-ENGLISH ALGORITHM
     * -----------------------
     * 1. Accept the first value as unique.
     *
     * 2. Set k = 1.
     *
     * 3. Read every remaining value using i.
     *
     * 4. Compare nums[i] with nums[i-1].
     *
     * 5. If they're equal:
     *
     *      Skip it.
     *
     * 6. If they're different:
     *
     *      Copy nums[i] into nums[k].
     *
     *      Increment k.
     *
     * 7. Continue until the end.
     *
     * 8. Return k.
     *
     *
     * TRACE
     * -----
     * Input:
     *
     *      [0,0,1,1,1,2,2,3,3,4]
     *
     * Start:
     *
     *      k = 1
     *
     * i = 1:
     *
     *      0 == 0
     *
     *      Duplicate.
     *
     *      Skip.
     *
     * i = 2:
     *
     *      1 != 0
     *
     *      nums[1] = 1
     *
     *      k = 2
     *
     * i = 3:
     *
     *      1 == 1
     *
     *      Skip.
     *
     * i = 4:
     *
     *      1 == 1
     *
     *      Skip.
     *
     * i = 5:
     *
     *      2 != 1
     *
     *      nums[2] = 2
     *
     *      k = 3
     *
     * Continue for 3 and 4.
     *
     * Final meaningful portion:
     *
     *      [0,1,2,3,4]
     *
     * Return:
     *
     *      5
     *
     *
     * EVERY-ITERATION DECISION
     * ------------------------
     * The question this algorithm asks every iteration is:
     *
     *      "Is this a NEW value?"
     *
     * If YES:
     *
     *      Write it.
     *
     * If NO:
     *
     *      Skip it.
     *
     *
     * GENERAL PATTERN
     * ---------------
     * This is a READ / WRITE Two Pointer pattern.
     *
     * One pointer explores every element.
     *
     * The other pointer builds the final answer.
     *
     * Use this pattern whenever:
     *
     *      - modifying an array in place,
     *      - filtering values,
     *      - removing values,
     *      - preserving order,
     *      - compacting valid elements toward the front.
     *
     * Generic template:
     *
     *      int write = 0;
     *
     *      for (int read = 0; read < nums.length; read++) {
     *
     *          if (shouldKeep(nums[read])) {
     *
     *              nums[write] = nums[read];
     *
     *              write++;
     *          }
     *      }
     *
     * In this problem:
     *
     *      shouldKeep(...)
     *
     * means:
     *
     *      nums[i] != nums[i - 1]
     *
     *
     * RELATED PROBLEMS
     * ----------------
     * This same read/write pattern appears in:
     *
     *      - Move Zeroes
     *      - Remove Element
     *      - Remove Duplicates II
     *      - Filtering arrays in place
     *      - Compacting valid values
     *
     *
     * RUNTIME
     * -------
     * i visits every element exactly once.
     *
     * Every operation inside the loop is O(1).
     *
     * Time:
     *
     *      O(n)
     *
     *
     * SPACE
     * -----
     * Only a few integer variables are used.
     *
     * No extra data structures are created.
     *
     * Space:
     *
     *      O(1)
     *
     *
     * CLEAN TEMPLATE
     * --------------
     *
     * int k = 1;
     *
     * for (int i = 1; i < nums.length; i++) {
     *
     *     if (nums[i] != nums[i - 1]) {
     *
     *         nums[k] = nums[i];
     *
     *         k++;
     *     }
     * }
     *
     * return k;
     *
     *
     * FINAL MEMORY PHRASE
     * -------------------
     *      Sorted means duplicates are adjacent.
     *
     *      i reads every value.
     *
     *      k is the first empty slot after all unique values.
     *
     *      Copy only NEW values.
     */
    
    
}
