package afterOptimizing;

public class ValidPalindrome {
	
//	A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
//
//	Given a string s, return true if it is a palindrome, or false otherwise.
	
	
	public static boolean isPalindrome(String s) {
	    int left = 0; 
	    int right = s.length() - 1; //starting indices at beginning and end, we do this since we will be skipping chars so it's cleaner than a for loop
		// Two pointers start at opposite ends.
		// A while loop is cleaner than a for loop because left/right may move
		// different amounts when skipping non-alphanumeric characters.
	    //still the two pointer approach, but less passes to clean the string -- we just skip weird char
	    // Instead of creating cleaned copies, we skip non-alphanumeric chars in place.
	    
	    while (left < right) {

	        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
	            left++;
	        } //increase left index when not a number/letter

	        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
	            right--;
	        } //decrease right index when not a number/letter

	        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
	            //if chars lowercase are !=, return false
	        	return false;
	        }

	        //otherwise continue loop while increasing left ptr/index and decreasing right ptr/index
		    // Current pair matched, so move both pointers inward
		    // to compare the next pair.
	        
	        left++;
	        right--;
	    }

	    return true;
	    // Time: O(n)
	    	// left only moves right and right only moves left.
	    	// Across the entire algorithm, each pointer traverses the string at most once. 

	    // Space: O(1)
	    	// We only store two integer pointers and temporary character values.
	    	// No data structure grows with the input size.
	}
	
	public static void main (String[] args) {
		System.out.println(isPalindrome("A man, a plan, a canal: Panama")); //true
		System.out.println(isPalindrome("race a car")); //false
		System.out.println(isPalindrome("  ")); //empty string is true
	}

	/*
	 * ============================================================
	 * VALID PALINDROME
	 * TWO POINTERS — NOTES / TAKEAWAYS / PATTERN
	 * ============================================================
	 *
	 * RECOGNITION
	 * -----------
	 * The problem asks whether a string reads the same
	 * forward and backward.
	 *
	 * That means we want to compare opposite ends.
	 *
	 *      Palindrome + compare from both ends
	 *      -> Two Pointers
	 *
	 *
	 * POINTERS
	 * --------
	 * left:
	 *      Starts at the beginning and moves right.
	 *
	 * right:
	 *      Starts at the end and moves left.
	 *
	 *
	 * MAIN DECISION
	 * -------------
	 * Every iteration asks:
	 *
	 *      "After skipping irrelevant characters,
	 *       do the left and right characters match?"
	 *
	 * If NO:
	 *
	 *      return false
	 *
	 * If YES:
	 *
	 *      move both pointers inward
	 *
	 *
	 * IGNORING NON-ALPHANUMERIC CHARACTERS
	 * ------------------------------------
	 * The prompt says only letters and numbers matter.
	 *
	 * Instead of creating a cleaned copy of the string,
	 * skip characters we don't care about.
	 *
	 *      Character.isLetterOrDigit(...)
	 *
	 * If left is not on a letter or number:
	 *
	 *      left++
	 *
	 * If right is not on a letter or number:
	 *
	 *      right--
	 *
	 *
	 * WHY USE WHILE TO SKIP CHARACTERS?
	 * ----------------------------------
	 * There may be several irrelevant characters in a row.
	 *
	 * Example:
	 *
	 *      "a ,,: a"
	 *
	 * One if-statement would only skip one character.
	 *
	 * A while-loop keeps moving until the pointer reaches
	 * a character we actually need to compare.
	 *
	 *
	 * CAPITALIZATION
	 * --------------
	 * Case does not matter.
	 *
	 * We do NOT need to lowercase the entire string.
	 *
	 * Instead, lowercase only the two characters
	 * currently being compared:
	 *
	 *      Character.toLowerCase(s.charAt(left))
	 *      Character.toLowerCase(s.charAt(right))
	 *
	 *
	 * INVARIANT
	 * ---------
	 * Everything OUTSIDE the current left/right pointers
	 * has already been checked and is valid.
	 *
	 * The remaining unanswered portion is:
	 *
	 *      s[left ... right]
	 *
	 *
	 * MEMORY RULE
	 * -----------
	 *      Start at both ends.
	 *
	 *      Skip junk.
	 *
	 *      Compare useful characters.
	 *
	 *      Move inward.
	 *
	 *
	 * WHY NO CLEANED STRING?
	 * ----------------------
	 * A solution using:
	 *
	 *      toLowerCase()
	 *      replace()
	 *      replaceAll()
	 *
	 * creates additional String objects because
	 * Java Strings are immutable.
	 *
	 * Two pointers let us inspect the original string
	 * directly.
	 *
	 *
	 * RUNTIME
	 * -------
	 * Each pointer moves through the string at most once.
	 *
	 * Time:
	 *
	 *      O(n)
	 *
	 *
	 * SPACE
	 * -----
	 * We only use pointer variables and temporary chars.
	 *
	 * No new string or array grows with the input.
	 *
	 * Space:
	 *
	 *      O(1)
	 *
	 *
	 * CLEAN TEMPLATE
	 * --------------
	 *
	 * int left = 0;
	 * int right = s.length() - 1;
	 *
	 * while (left < right) {
	 *
	 *     while (left < right &&
	 *            !Character.isLetterOrDigit(s.charAt(left))) {
	 *         left++;
	 *     }
	 *
	 *     while (left < right &&
	 *            !Character.isLetterOrDigit(s.charAt(right))) {
	 *         right--;
	 *     }
	 *
	 *     char leftChar =
	 *         Character.toLowerCase(s.charAt(left));
	 *
	 *     char rightChar =
	 *         Character.toLowerCase(s.charAt(right));
	 *
	 *     if (leftChar != rightChar) {
	 *         return false;
	 *     }
	 *
	 *     left++;
	 *     right--;
	 * }
	 *
	 * return true;
	 *
	 *
	 * FINAL MEMORY PHRASE
	 * -------------------
	 *      Palindrome -> compare both ends.
	 *
	 *      Skip anything irrelevant.
	 *
	 *      Compare.
	 *
	 *      Move inward.
	 */
	
	/*
	 * JAVA STRING COMPLEXITY NOTE
	 * ---------------------------
	 * Be careful about assuming:
	 *
	 *      s.equals(...)
	 *
	 * is always O(1).
	 *
	 * String.equals() compares the contents of two strings.
	 *
	 * In the worst case, Java may need to compare many or all
	 * characters before it knows whether the strings are equal.
	 *
	 * Example:
	 *
	 *      "abcdef".equals("abcdeg")
	 *
	 * Java may compare:
	 *
	 *      a == a
	 *      b == b
	 *      c == c
	 *      d == d
	 *      e == e
	 *      f != g
	 *
	 * So in general:
	 *
	 *      String.equals() -> O(n)
	 *
	 *
	 * CHECKING FOR AN EMPTY STRING
	 * ----------------------------
	 * If we only want to know whether a string is empty,
	 * prefer:
	 *
	 *      s.isEmpty()
	 *
	 * This is effectively checking:
	 *
	 *      s.length() == 0
	 *
	 * Java String objects store their length, so retrieving
	 * the length does NOT require traversing every character.
	 *
	 * Therefore:
	 *
	 *      s.isEmpty() -> O(1)
	 *      s.length()  -> O(1)
	 *
	 *
	 * For this Valid Palindrome solution, however, we don't
	 * actually need an empty-string check.
	 *
	 * If:
	 *
	 *      s = ""
	 *
	 * then:
	 *
	 *      left = 0
	 *      right = -1
	 *
	 * The condition:
	 *
	 *      while (left < right)
	 *
	 * is immediately false, and we naturally return true.
	 *
	 *
	 * TAKEAWAY
	 * --------
	 *      Comparing string CONTENT can require O(n).
	 *
	 *      Checking string LENGTH is O(1).
	 */
}
