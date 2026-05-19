package afterOptimizing;

public class LongestCommonPrefix {
	
	public static String longestCommonPrefix(String[] strs) {
	    String word = strs[0];

	    for (int i = 0; i < word.length(); i++) {//current char position
	        //O(m) where m is the length of the word
	    	char currentChar = word.charAt(i);

	        for (int j = 1; j < strs.length; j++) {//which word we are checking
	        //O(n) where n is the number of words
	            if (i >= strs[j].length() || strs[j].charAt(i) != currentChar) {
	                //i >= strs[j].length() checking size of word -- is this word to short to even have index j
	            	//if so, prefix must stop
	            	//strs[j].charAt(i) != currentChar -- if current char does not match -- prefix must stop
	            	return word.substring(0, i);
	            }
	        }
	    }

	    return word; //Runtime: O(m*n) operations
	    //Space: O(1) -- just using some vars and counters whose size does NOT grow with input size
	    //Output can be O(m) because this is the longest word size returned, but we do not count input or returned answer size when doing runtime analysis
	}
	
	public static void main (String[] args) {
		//String[] str = {"flight","flower","flow"}; //returns fl
		String[] str = {"sflight","aflower","rflow"}; // returns null
		System.out.println(longestCommonPrefix(str));
		//problem is checking: "Do ALL words have the SAME character at THIS exact position?"
		//check column by column, meaning:
		//f l o w e r
		//f l o w
		//f l i g h t
		//we see col 3 -- o o i fails
	}
	
	/*
	 * LONGEST COMMON PREFIX PATTERNS / THINGS TO REMEMBER
	 *
	 * -------------------------------------------------
	 * 1. BIGGEST LESSON:
	 *    PREFIX != SUBSTRING
	 * -------------------------------------------------
	 *
	 * PREFIX:
	 * - must start at index 0
	 * - beginning/front of string
	 *
	 * Example:
	 *
	 *     "flower"
	 *
	 * valid prefixes:
	 *
	 *     "f"
	 *     "fl"
	 *     "flo"
	 *
	 * NOT:
	 *
	 *     "low"
	 *
	 * because it does not start at the beginning.
	 *
	 * -------------------------------------------------
	 *
	 * SUBSTRING:
	 * - continuous section
	 * - can occur anywhere
	 * - cannot skip chars
	 *
	 * Example:
	 *
	 *     "flower"
	 *
	 * substrings:
	 *
	 *     "flo"
	 *     "low"
	 *     "owe"
	 *
	 * -------------------------------------------------
	 *
	 * SUBSEQUENCE:
	 * - can skip characters
	 * - order must stay preserved
	 *
	 * Example:
	 *
	 *     "flower"
	 *
	 * subsequences:
	 *
	 *     "fwr"
	 *     "foe"
	 *     "lwr"
	 *
	 * -------------------------------------------------
	 *
	 * IMPORTANT INTERVIEW TAKEAWAY:
	 *
	 * PREFIX:
	 *     compare from beginning
	 *     compare SAME INDICES
	 *
	 * SUBSTRING:
	 *     continuous section anywhere
	 *
	 * SUBSEQUENCE:
	 *     can skip chars
	 *
	 * -------------------------------------------------
	 *
	 * 2. MAIN CONFUSION I HAD
	 * -------------------------------------------------
	 * I initially thought:
	 *
	 *     "Does every word contain these letters somewhere?"
	 *
	 * which led me toward:
	 *
	 *     contains()
	 *
	 * BUT this is WRONG for prefixes.
	 *
	 * Prefix problems are NOT asking:
	 *
	 *     "Does the character exist somewhere?"
	 *
	 * They ARE asking:
	 *
	 *     "Do all words match at the SAME POSITION
	 *      from the beginning?"
	 *
	 * -------------------------------------------------
	 *
	 * 3. HOW TO THINK ABOUT THIS PROBLEM
	 * -------------------------------------------------
	 * Pick one word as the "reference word":
	 *
	 *     strs[0]
	 *
	 * Then check:
	 *
	 *     character-by-character
	 *     index-by-index
	 *
	 * Example:
	 *
	 *     flower
	 *     flow
	 *     flight
	 *
	 * compare vertically:
	 *
	 *     f f f ✅
	 *     l l l ✅
	 *     o o i ❌
	 *
	 * stop immediately on mismatch.
	 *
	 * Answer:
	 *
	 *     "fl"
	 *
	 * -------------------------------------------------
	 *
	 * 4. IMPORTANT CONDITION
	 * -------------------------------------------------
	 *
	 *     if(i >= strs[j].length()
	 *         || strs[j].charAt(i) != currentChar)
	 *
	 * This checks TWO failure cases:
	 *
	 * -------------------------------------------------
	 *
	 * CASE 1:
	 *
	 *     i >= strs[j].length()
	 *
	 * Means:
	 *
	 *     "This word is too short."
	 *
	 * Example:
	 *
	 *     ["flower", "fl"]
	 *
	 * At i = 2:
	 *
	 *     "fl" has no index 2
	 *
	 * Therefore:
	 *
	 *     common prefix must stop.
	 *
	 * -------------------------------------------------
	 *
	 * CASE 2:
	 *
	 *     strs[j].charAt(i) != currentChar
	 *
	 * Means:
	 *
	 *     "Characters differ at this position."
	 *
	 * Example:
	 *
	 *     flower
	 *     flight
	 *
	 *     o != i
	 *
	 * Prefix breaks immediately.
	 *
	 * -------------------------------------------------
	 *
	 * 5. IMPORTANT PREFIX PATTERN
	 * -------------------------------------------------
	 * Prefix problems are often:
	 *
	 *     compare columns vertically
	 *
	 * rather than:
	 *
	 *     compare rows horizontally
	 *
	 * Think:
	 *
	 *     "Does EVERY word match at THIS index?"
	 *
	 * -------------------------------------------------
	 *
	 * 6. BIG-O PATTERN
	 * -------------------------------------------------
	 *
	 * Let:
	 *
	 *     n = number of strings
	 *     m = length of shortest string
	 *
	 * Worst case:
	 *
	 *     compare every character
	 *     across every string
	 *
	 * Time:
	 *
	 *     O(n * m)
	 *
	 * Space:
	 *
	 *     O(1)
	 *
	 * excluding returned substring.
	 *
	 * -------------------------------------------------
	 *
	 * 7. COMMON SIGNALS FOR PREFIX PROBLEMS
	 * -------------------------------------------------
	 * If problem says:
	 *
	 *     "common prefix"
	 *     "starts with"
	 *     "beginning of string"
	 *
	 * THINK:
	 *
	 *     compare SAME INDICES
	 *     starting at index 0
	 *
	 * -------------------------------------------------
	 *
	 * 8. IMPORTANT INTERVIEW TAKEAWAY
	 * -------------------------------------------------
	 * I struggled because:
	 *
	 *     contains()
	 *
	 * felt intuitive.
	 *
	 * But:
	 *
	 *     contains()
	 *
	 * checks:
	 *
	 *     "exists somewhere"
	 *
	 * Prefix problems care about:
	 *
	 *     exact positions from the start.
	 *
	 * ALWAYS clarify:
	 *
	 *     prefix?
	 *     substring?
	 *     subsequence?
	 *
	 * because they imply VERY different algorithms.
	 *
	 */

}
