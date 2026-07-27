package afterOptimizing;

import java.util.HashMap;

public class LongestSubstringTwoDistinct {
	
//	Given a string s, find the length of the longest substring that contains at most two 
//  distinct characters.
//
//	Example 1:
//
//	Input: s = "eceba"
//	Output: 3
//	Explanation: The substring is "ece" which its length is 3.
//	Example 2:
//
//	Input: s = "ccaabbb"
//	Output: 5
//	Explanation: The substring is "aabbb" which its length is 5.
	
//	Constraints	
//	1 <= s.length <= 104
//	s consists of English letters.
	
	//input: string
	//output is going to be the length of the longest substring (continuous)
	//criteria of not being a window: > 2 distinct characters
	//each unique character can repeat as many times as it wants
	
	 public static int lengthOfLongestSubstringTwoDistinct(String s) {
	        HashMap<Character, Integer> counts = new HashMap<>();

	        int left = 0;
	        int maxLength = 0;

	        for (int right = 0; right < s.length(); right++) {
	            char rightChar = s.charAt(right);

	            // Add the new rightmost character to the window.
	            counts.put(
	                rightChar,
	                counts.getOrDefault(rightChar, 0) + 1
//	                Get the current count.
//	                If the character isn't present, use zero instead.
//	                This does the same job as what I had without separate cases.
	            );

	            // Shrink until the window contains at most two distinct characters.
	            while (counts.size() > 2) {
	                char leftChar = s.charAt(left);

	                counts.put(leftChar, counts.get(leftChar) - 1);

	                if (counts.get(leftChar) == 0) {
	                    counts.remove(leftChar);
	                }

	                left++;
	            }
	            
//	            my if version can work as a lazy-window maximum-length solution. It shrinks once per right-pointer iteration and avoids updating the answer while invalid.
//	            However, the standard while version maintains the stronger and more reusable invariant:
//	            At the end of every outer-loop iteration, the current window is valid.

	            // The window is guaranteed to be valid here.
	            int currentLength = right - left + 1;
	            //You only need the window length after the window has been made valid.
	            maxLength = Math.max(maxLength, currentLength);
	        }

	        return maxLength;
	    }
	
	public static void main(String[] args) {
		String s = "ecebe";
		String s2 = "ccaabbb";
		
		System.out.println(lengthOfLongestSubstringTwoDistinct(s)); //3
		System.out.println(lengthOfLongestSubstringTwoDistinct(s2)); //5
		System.out.println(lengthOfLongestSubstringTwoDistinct("a")); //1
		System.out.println(lengthOfLongestSubstringTwoDistinct("ecec")); //4
		
		//O(n) runtime
		//O(1) space -- count is bound by alphabet size (26)
		
	}
	
	/*
	 * Your original solution had the correct core recognition and data structure. The main cleanup was turning it into the repeatable Sliding Window template:
		Add right → while invalid, remove left → update answer
	 */
	
	/*
	 * ============================================================
	 * LONGEST SUBSTRING WITH AT MOST TWO DISTINCT CHARACTERS
	 * VARIABLE SLIDING WINDOW — NOTES / MENTAL MODEL
	 * ============================================================
	 *
	 *
	 * RECOGNITION
	 * -----------
	 * The problem asks for:
	 *
	 *      - the LONGEST result,
	 *      - a SUBSTRING,
	 *      - with a condition that can become valid or invalid.
	 *
	 * A substring is contiguous.
	 *
	 * Therefore:
	 *
	 *      Longest contiguous valid section -> Sliding Window
	 *
	 *
	 * WHAT MAKES THE WINDOW VALID?
	 * ----------------------------
	 * The current window is:
	 *
	 *      s[left ... right]
	 *
	 * It is valid when it contains:
	 *
	 *      at most two distinct characters
	 *
	 * It is invalid when:
	 *
	 *      counts.size() > 2
	 *
	 *
	 * THE MAIN SLIDING-WINDOW DECISION
	 * --------------------------------
	 * Ask:
	 *
	 *      "What makes me move the left pointer?"
	 *
	 * Here:
	 *
	 *      Move left while the window contains
	 *      more than two distinct characters.
	 *
	 *
	 * WHAT THE HASHMAP STORES
	 * -----------------------
	 * The map stores character frequencies for the CURRENT window.
	 *
	 * key:
	 *      A distinct character currently in the window.
	 *
	 * value:
	 *      The number of times that character appears in the window.
	 *
	 * Example:
	 *
	 *      window = "aabbb"
	 *
	 *      counts = {
	 *          a = 2,
	 *          b = 3
	 *      }
	 *
	 *
	 * EXPANDING THE WINDOW
	 * --------------------
	 * The right pointer moves forward once per outer-loop iteration.
	 *
	 * The new rightmost character enters the window:
	 *
	 *      char rightChar = s.charAt(right);
	 *
	 *      counts.put(
	 *          rightChar,
	 *          counts.getOrDefault(rightChar, 0) + 1
	 *      );
	 *
	 * getOrDefault(character, 0) means:
	 *
	 *      - use the existing count if the character is present,
	 *      - otherwise begin at zero.
	 *
	 *
	 * WHY USE WHILE INSTEAD OF IF?
	 * ----------------------------
	 * Standard variable sliding window uses:
	 *
	 *      while (counts.size() > 2)
	 *
	 * Removing one character from the left may not restore validity.
	 *
	 * Example:
	 *
	 *      window = "ccaab"
	 *      counts = {c=2, a=2, b=1}
	 *
	 * There are three distinct characters.
	 *
	 * Remove one c:
	 *
	 *      window = "caab"
	 *      counts = {c=1, a=2, b=1}
	 *
	 * The window still has three distinct characters.
	 *
	 * Remove the second c:
	 *
	 *      window = "aab"
	 *      counts = {a=2, b=1}
	 *
	 * Now the window is valid.
	 *
	 * Therefore, continue shrinking UNTIL the condition is restored.
	 *
	 *
	 * IMPORTANT NOTE ABOUT IF
	 * -----------------------
	 * An if-based "lazy window" variation can still calculate the
	 * correct maximum length for this problem when:
	 *
	 *      - left moves once whenever the window is invalid, and
	 *      - maxLength is updated only when the window is valid.
	 *
	 * However, while is the preferred interview solution because it
	 * maintains a stronger and clearer invariant:
	 *
	 *      After the shrinking loop, the current window is valid.
	 *
	 * It is also easier to reuse for other sliding-window problems.
	 *
	 *
	 * SHRINKING THE WINDOW
	 * --------------------
	 * While invalid:
	 *
	 *      1. Find the character at the left boundary.
	 *
	 *          char leftChar = s.charAt(left);
	 *
	 *      2. Remove one occurrence from its count.
	 *
	 *          counts.put(
	 *              leftChar,
	 *              counts.get(leftChar) - 1
	 *          );
	 *
	 *      3. If its count reaches zero, remove the key.
	 *
	 *          if (counts.get(leftChar) == 0) {
	 *              counts.remove(leftChar);
	 *          }
	 *
	 *      4. Move left forward.
	 *
	 *          left++;
	 *
	 *
	 * WHY REMOVE A KEY WHEN ITS COUNT REACHES ZERO?
	 * ---------------------------------------------
	 * counts.size() represents the number of DISTINCT characters
	 * currently inside the window.
	 *
	 * If a count reaches zero, that character no longer appears
	 * inside the window.
	 *
	 * Leaving the zero-count key in the map would make map.size()
	 * incorrectly report an extra distinct character.
	 *
	 *
	 * THE SLIDING-WINDOW INVARIANT
	 * ----------------------------
	 * After the while-loop finishes:
	 *
	 *      The window s[left ... right] contains
	 *      at most two distinct characters.
	 *
	 * In other words:
	 *
	 *      The current window is valid.
	 *
	 *
	 * WHEN TO UPDATE MAX LENGTH
	 * -------------------------
	 * Update the answer AFTER the shrinking while-loop:
	 *
	 *      int currentLength = right - left + 1;
	 *
	 *      maxLength = Math.max(
	 *          maxLength,
	 *          currentLength
	 *      );
	 *
	 * At this point, the window is guaranteed to be valid.
	 *
	 *
	 * WHY +1?
	 * -------
	 * left and right are inclusive indices.
	 *
	 * Example:
	 *
	 *      left  = 2
	 *      right = 4
	 *
	 * Indices included:
	 *
	 *      2, 3, 4
	 *
	 * Length:
	 *
	 *      right - left + 1
	 *      4 - 2 + 1
	 *      3
	 *
	 *
	 * PLAIN-ENGLISH ALGORITHM
	 * -----------------------
	 * 1. Create a frequency map.
	 *
	 * 2. Set left to zero.
	 *
	 * 3. Move right across the string.
	 *
	 * 4. Add the right character to the frequency map.
	 *
	 * 5. While the map contains more than two distinct characters:
	 *
	 *      - decrement the count of the left character,
	 *      - remove its key if the count becomes zero,
	 *      - move left forward.
	 *
	 * 6. Once the window is valid, calculate:
	 *
	 *      right - left + 1
	 *
	 * 7. Update the maximum length.
	 *
	 * 8. Return the maximum.
	 *
	 *
	 * EVERY-ITERATION DECISION
	 * ------------------------
	 * The decision this algorithm makes every iteration is:
	 *
	 *      "After expanding right, do I need to move left
	 *       to restore validity?"
	 *
	 *
	 * MEMORY RULE
	 * -----------
	 *      Expand right.
	 *
	 *      Invalid?
	 *
	 *      Shrink left WHILE invalid.
	 *
	 *      Once valid, update the answer.
	 *
	 *
	 * RUNTIME
	 * -------
	 * The right pointer moves forward at most n times.
	 *
	 * The left pointer also moves forward at most n times total.
	 *
	 * Neither pointer ever moves backward.
	 *
	 *      O(n + n) = O(n)
	 *
	 * Average HashMap operations are O(1).
	 *
	 * Time:
	 *
	 *      O(n)
	 *
	 *
	 * SPACE
	 * -----
	 * The map has at most two keys after shrinking and may
	 * temporarily have three keys before shrinking.
	 *
	 * Because the number of stored keys is bounded:
	 *
	 *      O(1)
	 *
	 * More generally:
	 *
	 *      O(min(n, alphabet size))
	 *
	 * Since the problem uses a fixed English alphabet:
	 *
	 *      Space = O(1)
	 *
	 *
	 * CLEAN TEMPLATE
	 * --------------
	 *
	 * Map<Character, Integer> counts = new HashMap<>();
	 *
	 * int left = 0;
	 * int maxLength = 0;
	 *
	 * for (int right = 0; right < s.length(); right++) {
	 *
	 *     char rightChar = s.charAt(right);
	 *
	 *     counts.put(
	 *         rightChar,
	 *         counts.getOrDefault(rightChar, 0) + 1
	 *     );
	 *
	 *     while (counts.size() > 2) {
	 *
	 *         char leftChar = s.charAt(left);
	 *
	 *         counts.put(
	 *             leftChar,
	 *             counts.get(leftChar) - 1
	 *         );
	 *
	 *         if (counts.get(leftChar) == 0) {
	 *             counts.remove(leftChar);
	 *         }
	 *
	 *         left++;
	 *     }
	 *
	 *     maxLength = Math.max(
	 *         maxLength,
	 *         right - left + 1
	 *     );
	 * }
	 *
	 * return maxLength;
	 */
}