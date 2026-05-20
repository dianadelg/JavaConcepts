package afterOptimizing;

public class PalindromeNumber {
	
	public static boolean isPalindrome(int x) {
		 if (x < 0 || (x % 10 == 0 && x != 0)) {
			 //if negative, not palindrome
			 //if ones digit is 0 -- not palindrome because 10 --> 01 not valid
			 //UNLESS the number itself is 0
		        return false;
		    }

		    int reversedHalf = 0; //Instead of reversing the WHOLE number, we only reverse the BACK HALF
		    //we use this to hold digits from the back of x
		    //take last digit of x, move to reversed half
		    //so 12321 -- x becomes 1232, reversed half becomes 1
		    //			--x becomes 123, reversed half becomes 12
		    //			--x becomes 12, reversed half becomes 123
		    while (x > reversedHalf) { //we stop when reversedHalf >= x
		    	//because it means we've processed half the digits
		        int digit = x % 10;
		        reversedHalf = reversedHalf * 10 + digit; //add digit at end of x to end of reverse half
		        x = x / 10;
		    }

		    return x == reversedHalf || x == reversedHalf / 10;
		    //so at this pint, we stop when half the digits
		    //in a palindrome, middle digiy doesn't matter
		    //so in this case, 3 doesn't matter but the surrounding 12 and 21 does
		    //so we remove it -- that is the x == reversedHalf / 10 check
		    //otherwise if even number of digits, x and reversed half can be compared, that's this part: x == reversedHalf
		    
		    //big takeaway: splitting the number into a front and back half 
		    //WITHOUT converting to strings
		    //because reversing the entire integer can result in overflow
	}
	
	public static void main(String[] args) {
		System.out.println(isPalindrome(1234)); //no
		System.out.println(isPalindrome(1234321)); //yes
		System.out.println(isPalindrome(1)); //yes
		System.out.println(isPalindrome(-1234)); //no
		System.out.println(isPalindrome(10)); //no
		System.out.println(isPalindrome(101)); //yes
	}
	/*
	 * PALINDROME NUMBER PATTERNS / THINGS TO REMEMBER
	 *
	 * -------------------------------------------------
	 * 1. CORE PALINDROME IDEA
	 * -------------------------------------------------
	 * A palindrome reads the same:
	 *
	 *     forward
	 *     backward
	 *
	 * Example:
	 *
	 *     121
	 *     12321
	 *
	 * NOT:
	 *
	 *     10
	 *     -121
	 *
	 * -------------------------------------------------
	 *
	 * 2. OPTIMIZED APPROACH:
	 *    REVERSE ONLY HALF
	 * -------------------------------------------------
	 * Instead of:
	 *
	 *     converting to String
	 *     OR reversing the whole number
	 *
	 * reverse ONLY the back half of the digits.
	 *
	 * Why?
	 *
	 *     - avoids extra String space
	 *     - avoids integer overflow
	 *     - less work
	 *
	 * Goal:
	 *
	 *     front half == reversed back half
	 *
	 * -------------------------------------------------
	 *
	 * 3. THE 3 IMPORTANT INTEGER OPERATIONS
	 * -------------------------------------------------
	 *
	 * A. GET LAST DIGIT
	 *
	 *     x % 10
	 *
	 * Example:
	 *
	 *     123 % 10 = 3
	 *
	 * -------------------------------------------------
	 *
	 * B. REMOVE LAST DIGIT
	 *
	 *     x = x / 10
	 *
	 * Example:
	 *
	 *     123 / 10 = 12
	 *
	 * Integer division removes the decimal.
	 *
	 * -------------------------------------------------
	 *
	 * C. APPEND DIGIT TO ANOTHER NUMBER
	 *
	 *     reversed = reversed * 10 + digit
	 *
	 * Example:
	 *
	 *     reversed = 12
	 *     digit = 3
	 *
	 *     12 * 10 + 3 = 123
	 *
	 * This pattern appears CONSTANTLY in:
	 *
	 *     integer reversal problems
	 *     digit extraction problems
	 *     math-based palindrome problems
	 *
	 * -------------------------------------------------
	 *
	 * 4. VISUAL FOR HALF-REVERSAL
	 * -------------------------------------------------
	 * Example:
	 *
	 *     x = 12321
	 *
	 * Start:
	 *
	 *     x = 12321
	 *     reversedHalf = 0
	 *
	 * Move digits from END of x into reversedHalf:
	 *
	 * -------------------------------------------------
	 *
	 * Take last digit:
	 *
	 *     12321 -> 1
	 *
	 * Now:
	 *
	 *     x = 1232
	 *     reversedHalf = 1
	 *
	 * Visual:
	 *
	 *     1232 | 1
	 *
	 * -------------------------------------------------
	 *
	 * Take another digit:
	 *
	 *     1232 -> 2
	 *
	 * Now:
	 *
	 *     x = 123
	 *     reversedHalf = 12
	 *
	 * Visual:
	 *
	 *     123 | 12
	 *
	 * -------------------------------------------------
	 *
	 * Take another digit:
	 *
	 *     123 -> 3
	 *
	 * Now:
	 *
	 *     x = 12
	 *     reversedHalf = 123
	 *
	 * Visual:
	 *
	 *     12 | 123
	 *
	 * Stop once:
	 *
	 *     reversedHalf >= x
	 *
	 * because we have processed half the digits.
	 *
	 * -------------------------------------------------
	 *
	 * 5. EVEN VS ODD LENGTH PALINDROMES
	 * -------------------------------------------------
	 *
	 * EVEN:
	 *
	 *     1221
	 *
	 * Ends as:
	 *
	 *     x = 12
	 *     reversedHalf = 12
	 *
	 * Compare:
	 *
	 *     x == reversedHalf
	 *
	 * -------------------------------------------------
	 *
	 * ODD:
	 *
	 *     12321
	 *
	 * Ends as:
	 *
	 *     x = 12
	 *     reversedHalf = 123
	 *
	 * Middle digit does NOT matter.
	 *
	 * Remove middle digit:
	 *
	 *     reversedHalf / 10
	 *
	 *     123 / 10 = 12
	 *
	 * Compare:
	 *
	 *     x == reversedHalf / 10
	 *
	 * -------------------------------------------------
	 *
	 * 6. IMPORTANT EDGE CASES
	 * -------------------------------------------------
	 *
	 * NEGATIVES:
	 *
	 *     -121
	 *
	 * are NOT palindromes.
	 *
	 * Return false immediately:
	 *
	 *     x < 0
	 *
	 * -------------------------------------------------
	 *
	 * TRAILING ZEROS:
	 *
	 *     10
	 *     120
	 *
	 * are NOT palindromes because:
	 *
	 *     01 -> 1
	 *
	 * Special case:
	 *
	 *     0 IS a palindrome
	 *
	 * Therefore:
	 *
	 *     if(x % 10 == 0 && x != 0)
	 *
	 * means:
	 *
	 *     "If number ends in 0 but is not literally 0,
	 *      it cannot be a palindrome."
	 *
	 * -------------------------------------------------
	 *
	 * 7. BIG-O FOR INTEGER PROBLEMS
	 * -------------------------------------------------
	 * IMPORTANT:
	 *
	 * Runtime is based on:
	 *
	 *     NUMBER OF DIGITS
	 *
	 * NOT:
	 *
	 *     numeric value itself
	 *
	 * Let:
	 *
	 *     d = number of digits
	 *
	 * Example:
	 *
	 *     x = 1,000,000,000
	 *
	 * huge value
	 * BUT only 10 digits.
	 *
	 * Digit-based algorithms process:
	 *
	 *     one digit at a time
	 *
	 * Therefore:
	 *
	 *     Time = O(d)
	 *
	 * -------------------------------------------------
	 *
	 * 8. WHY O(log x)?
	 * -------------------------------------------------
	 * Number of digits grows logarithmically relative
	 * to the value of x.
	 *
	 * Example:
	 *
	 *     9       -> 1 digit
	 *     99      -> 2 digits
	 *     999     -> 3 digits
	 *
	 * Every extra digit means the number got ~10x larger.
	 *
	 * Therefore:
	 *
	 *     d = log10(x)
	 *
	 * So these are equivalent:
	 *
	 *     O(d)
	 *     O(log x)
	 *
	 * -------------------------------------------------
	 *
	 * 9. FINAL BIG-O
	 * -------------------------------------------------
	 * Half-reversal processes about half the digits:
	 *
	 *     O(d / 2)
	 *
	 * Big-O ignores constants:
	 *
	 *     O(d / 2) -> O(d)
	 *
	 * Space:
	 *
	 *     O(1)
	 *
	 * because only a few integer variables are used.
	 *
	 * Final:
	 *
	 *     Time: O(d) == O(log x)
	 *     Space: O(1)
	 *
	 * -------------------------------------------------
	 *
	 * 10. INTERVIEW PATTERN RECOGNITION
	 * -------------------------------------------------
	 * If problem says:
	 *
	 *     "without converting to String"
	 *
	 * think:
	 *
	 *     modulo/division digit extraction
	 *
	 * If problem involves:
	 *
	 *     reversing digits
	 *     checking symmetry
	 *     comparing front/back behavior
	 *
	 * think:
	 *
	 *     reverse half the number
	 *
	 * If reversing integers:
	 *
	 * ALWAYS think about:
	 *
	 *     integer overflow
	 *
	 */

}
