package afterOptimizing;
import java.util.*;

	public class ValidAnagram {
		
//		Given two strings s and t, return true if the two strings are anagrams of each other, 
//		otherwise return false.
	//
//		An anagram is a string that contains the exact same characters as another string, 
//		but the order of the characters can be different

//		Input: s = "racecar", t = "carrace"
	//
//		Output: true
		
		//PATTERN: using an array as a frequency counter
		
//		Plain-English Algorithm (Optimized):
//			1. Check lengths: If the two strings have different lengths, return false immediately.
//			2. Create a frequency array: Initialize an integer array of size 26 (one index for each lowercase English letter).
//			3. Loop through both strings simultaneously:
//			   - Increment the frequency of the current character in s.
//			   - Decrement the frequency of the current character in t.
//			   - Use charAt(i) - 'a' to map each character to an array index (0–25).
//			4. Check the frequencies: Loop through the frequency array. If any count is not zero, return false.
//			5. Return true: If every frequency is zero, both strings contain exactly the same characters with the same counts.
		
		//TL;DR: Valid Anagram: Same length → Count up for S → Count down for T → All zeros = anagram.
		
		//I WANT TO NOTE. What if it was capital letters, unicode chars, etc?
		//this would break, because 'A' - 'a' would give a negative index
		//IN THIS CASE, instead of a frequency array, we'd use a hashmap and store the counts the same way
		//and we'd loop like this:
		
//		for (int count : map.values()) {
//		    if (count != 0) {
//		        return false;
//		    }
//		}
//
//		return true;
		
		//TL;DR p2: Fixed alphabet → Frequency array. Flexible alphabet → HashMap.
		
		public static boolean isAnagram(String s, String t) {
		    if (s.length() != t.length()) {
		        return false;
		    } //same as before, if not the same length, we know not an anagram, some letter are missing in one of the words
		    //so return false

		    int[] counts = new int[26]; //frequency array
		    //instead of using a hashmap, we know there are only 26 lowercase letters
		    //dedicate each index to a letter
		    //a -- 0
		    //b -- 1
		    //c -- 2...etc

		    for (int i = 0; i < s.length(); i++) { //loop once, pick either word as length since we know they're equal length
		        counts[s.charAt(i) - 'a']++;
		        counts[t.charAt(i) - 'a']--;
		        //instead of individually passing through s and t, process both strings at the same time
		    
		        /*
		         * Explaining what's going on here, suppose s = "cab"
		         * current char == 'c'
		         * we need to know where that belongs in the array
		         * 'a' = 97 ASCII
		         * 'b' = 98
		         * 'c' = 99 etc
		         * 
		         * so 'c' - 'a' = 99 - 97 = 2, array index 2 is where we keep count for c 
		         * 
		         * and we just increment the count every time we come across a letter like I did before
		         * 
		         * now for t --> think of this like that valid parenthesis one
		         * we are instead going to decrease count when we find a letter in t
		         * 
		         * so at the end, we want to see counts of all 0 for each letter if it's an anagram
		         * 
		         * THIS WORKS BECAUSE: 
		         * - every letter from s contributes +1
		         * - every letter from t contributes -1
		         * - if they are true anagrams, every positive count is canceled by a matching negative count
		         * 
		         */
		    
		    }

		    for (int count : counts) {
		    	//optimized for loop, for each element in counts
		        if (count != 0) {
		        	//if at any point, one of the letters has a count of not zero, means not valid
		            return false;
		        }
		    }

		    return true;
		}
		
//		Time: O(n)
//		Space: O(1)
	    
		
		public static void main (String[] args) {
			System.out.println(isAnagram("racecar", "carrace"));
			System.out.println(isAnagram("jar", "jam"));
			System.out.println(isAnagram("abb", "aab")); //showing as true, why?
		}
		
		/*
		 * VALID ANAGRAM PATTERNS / THINGS TO REMEMBER
		 *
		 * -------------------------------------------------
		 * 1. CHOOSING THE RIGHT DATA STRUCTURE
		 * -------------------------------------------------
		 *
		 * Problem asks...
		 *
		 * "Have I seen this before?"
		 *      -> HashSet
		 *
		 * "Need to map one thing to another?"
		 *      -> HashMap
		 *
		 * "Need to count occurrences/frequencies?"
		 *      -> Frequency Counter
		 *         (int[] if the range is small,
		 *          HashMap otherwise)
		 *
		 * -------------------------------------------------
		 * 2. MY THINKING
		 * -------------------------------------------------
		 *
		 * I correctly identified that this problem
		 * requires FREQUENCIES, not just existence.
		 *
		 * That's why my HashMap solution works.
		 *
		 * -------------------------------------------------
		 * 3. WHY THE OPTIMIZED SOLUTION USES AN ARRAY
		 * -------------------------------------------------
		 *
		 * The problem states:
		 *
		 *     lowercase English letters only
		 *
		 * There are only:
		 *
		 *     26 possible characters
		 *
		 * Therefore, instead of storing:
		 *
		 *     Character -> Count
		 *
		 * inside a HashMap,
		 *
		 * we can dedicate one array slot
		 * to every possible letter.
		 *
		 * Index:
		 *
		 *     0 = a
		 *     1 = b
		 *     ...
		 *     25 = z
		 *
		 * This removes hashing overhead and
		 * reduces space from a HashMap to a
		 * constant-size array.
		 *
		 * -------------------------------------------------
		 * 4. FREQUENCY COUNTER PATTERN
		 * -------------------------------------------------
		 *
		 * While iterating:
		 *
		 *     count[s.charAt(i) - 'a']++;
		 *     count[t.charAt(i) - 'a']--;
		 *
		 * Every character in s contributes:
		 *
		 *     +1
		 *
		 * Every matching character in t contributes:
		 *
		 *     -1
		 *
		 * If the strings are anagrams,
		 * every count cancels back to zero.
		 *
		 * -------------------------------------------------
		 * 5. INTERVIEW PATTERN RECOGNITION
		 * -------------------------------------------------
		 *
		 * If the problem has:
		 *
		 *     small, fixed range of values
		 *
		 * Examples:
		 *
		 *     lowercase letters (26)
		 *     uppercase letters (26)
		 *     digits (10)
		 *
		 * strongly consider:
		 *
		 *     int[]
		 *
		 * instead of:
		 *
		 *     HashMap
		 *
		 * Arrays are usually faster and use
		 * less memory when the range is fixed.
		 *
		 * -------------------------------------------------
		 * 6. BIG-O
		 * -------------------------------------------------
		 *
		 * HashMap Solution:
		 *
		 *     Time: O(n)
		 *     Space: O(n) (general case)
		 *
		 * Array Frequency Solution:
		 *
		 *     Time: O(n)
		 *     Space: O(1)
		 *
		 * because the array always contains
		 * exactly 26 elements regardless of
		 * input size.
		 *
		 */

	}
