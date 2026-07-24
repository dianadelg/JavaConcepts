package beforeOptimizing;

public class SubarraySumEqualsK {

//	Given an array of integers nums and an integer k, return the total number of 
//	subarrays whose sum equals to k.
//
//	A subarray is a contiguous non-empty sequence of elements within an array.
	
	//Why Kadane doesn't work. Target, and:
	//being "too big" doesn't mean anything anymore.
	//Future numbers can bring you back down.

	
	//my algo proposed:
	/*
	 * it's like, 3 -- doesn't work, > 2. Drop it. Start at 0 as sum 1 < k. Works. 
	 * Add next 1+5 > k. Drop it. Start at zero 5. 
	 * Add to sum. Drop it, > k 2. == k. Increase count of how many =k. etc
	 */
	
	//my algo would've worked if only positive numbers. But negative gives an issue:
	//2 1 2 -1 --> and k = 2
	// 2 -- yes
	// 2 + 1 -- no
	// 2 + 1 + 2 -- no
	// 2 + 1 + 2 - 1 -- no
	// 1 -- no
	// 1 + 2 =-- no
	// 1 + 2 - 1 -- yes
	// 2 -- yes
	// 2 - 1 -- no
	// -1 -- no

	/*
	 *  Here's the really important realization

		You just discovered something profound:
		
		There is no safe local decision.
		
		That's why neither Sliding Window nor Kadane works.
	 */
	
	/*
	 * Question:

		Should I remove the left?
		
		You don't know.
		
		Question:
		
		Should I throw everything away?
		
		You don't know.
		
		Question:
		
		Should I keep going?
		
		You don't know.
		
		Every choice could be wrong depending on the next number.
		
		That's the giveaway that you need a different technique.
	 */
	
    public static int subarraySum(int[] nums, int k) {
    	int numberOfArraysK=0;
    	int localSum=0;
    	
    	for(int i=0; i<nums.length; i++) {
    		localSum+=nums[i];
    		if(k==localSum) {
    			numberOfArraysK++;
    		}
    		for(int j=i+1; j<nums.length; j++) {
        		localSum+= nums[j];
        		if(k==localSum) {
        			numberOfArraysK++;
        		}
    		}
    		localSum=0;
    	}
    	//Runtime O(n^2)
    	//Space O(1)
    	
    	return numberOfArraysK;
    }
    
    public static void main (String [] args) {
    	int [] nums = {1, 2, -1, 1, -3}; //3
    	System.out.println(subarraySum(nums, 2));
    	
    	int [] nums2 = {1, 2, -1, 1}; //3
    	System.out.println(subarraySum(nums2, 2));
    	
    	int [] nums3 = {2}; //1
    	System.out.println(subarraySum(nums3, 2));
    }
    
  //Why Kadane doesn't work. Target, and:
  	//being "too big" doesn't mean anything anymore.
  	//Future numbers can bring you back down.

  	
  	//my algo proposed:
  	/*
  	 * it's like, 3 -- doesn't work, > 2. Drop it. Start at 0 as sum 1 < k. Works. 
  	 * Add next 1+5 > k. Drop it. Start at zero 5. 
  	 * Add to sum. Drop it, > k 2. == k. Increase count of how many =k. etc
  	 */
  	
  	//my algo would've worked if only positive numbers. But negative gives an issue:
  	//2 1 2 -1 --> and k = 2
  	// 2 -- yes
  	// 2 + 1 -- no
  	// 2 + 1 + 2 -- no
  	// 2 + 1 + 2 - 1 -- no
  	// 1 -- no
  	// 1 + 2 =-- no
  	// 1 + 2 - 1 -- yes
  	// 2 -- yes
  	// 2 - 1 -- no
  	// -1 -- no

  	/*
  	 *  Here's the really important realization

  		You just discovered something profound:
  		
  		There is no safe local decision.
  		
  		That's why neither Sliding Window nor Kadane works.
  	 */
  	
  	/*
  	 * Question:

  		Should I remove the left?
  		
  		You don't know.
  		
  		Question:
  		
  		Should I throw everything away?
  		
  		You don't know.
  		
  		Question:
  		
  		Should I keep going?
  		
  		You don't know.
  		
  		Every choice could be wrong depending on the next number.
  		
  		That's the giveaway that you need a different technique.
  	 */
    
    /*
     * If problem asks for:
     *
     * - exact subarray sum = k
     * - count the number of subarrays
     * - contiguous
     * - continuous
     * - consecutive
     * - subarray
     *
     * STOP and ask:
     *
     * "Can I make a SAFE local decision?"
     *
     * -------------------------------------------------------
     * SLIDING WINDOW
     * -------------------------------------------------------
     *
     * Ask:
     *
     * "When this condition happens, does moving LEFT
     * definitely bring me closer to a valid answer?"
     *
     * Examples:
     * - duplicate character
     * - window larger than k
     * - at most k distinct characters
     * - fixed-size window
     * - POSITIVE numbers only and sum > target
     *
     * If YES:
     *
     *     Sliding Window
     *
     * Why?
     *
     * Because moving LEFT is PROVABLY safe.
     *
     * -------------------------------------------------------
     * KADANE
     * -------------------------------------------------------
     *
     * Ask:
     *
     * "Is my previous running sum hurting ALL future sums?"
     *
     * Rule:
     *
     * if(currentSum < 0)
     *
     * Throw away EVERYTHING.
     *
     * Why?
     *
     * A negative running sum can never increase a future sum.
     *
     * IMPORTANT:
     *
     * Kadane does NOT compare currentSum to k.
     *
     * It is solving:
     *
     *     "What is the maximum (or minimum) sum?"
     *
     * NOT:
     *
     *     "Can I hit an exact target?"
     *
     * -------------------------------------------------------
     * PREFIX SUM + HASHMAP
     * -------------------------------------------------------
     *
     * If neither algorithm has a SAFE decision...
     *
     * Example:
     *
     * nums = [3, -1]
     * k = 2
     *
     * At 3:
     *
     * Remove LEFT?
     *      Maybe...
     *
     * Keep going?
     *      Maybe...
     *
     * Restart?
     *      Maybe...
     *
     * Every choice could be wrong depending on future numbers.
     *
     * There is NO safe local decision.
     *
     * Therefore:
     *
     * Don't move pointers.
     * Don't restart.
     *
     * Remember HISTORY instead.
     *
     * Think:
     *
     * Prefix Sum + HashMap
     *
     * -------------------------------------------------------
     * INTERVIEW TEST
     * -------------------------------------------------------
     *
     * Before coding ask:
     *
     * "Can I PROVE this local decision is always safe?"
     *
     * YES -> Sliding Window
     *
     * YES, but ONLY when currentSum < 0
     *      and I'm maximizing/minimizing a sum
     *      -> Kadane
     *
     * NO -> Probably Prefix Sum (or another algorithm)
     */
	
	
}
