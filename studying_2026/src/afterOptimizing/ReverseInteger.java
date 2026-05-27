package afterOptimizing;

public class ReverseInteger {

	public static int reverse(int x) {
	    int reverse = 0; //reverse number goes here, like in palindrome

	    while (x != 0) { //keep looping while there are no digits left to process, takes negative
	    	//into account unlike the previous >0 check
	        int digit = x % 10; //gets last digit
	        x = x / 10; //removes last digit after processing it

	        //OVERFLOW CHECK START - check what would break the int limit
	        //java int range: -2147483648 to 2147483647 
	        if (reverse > Integer.MAX_VALUE / 10 ||
	        		//meaning if we * by 10, it would definitely be way too big if we're already
	        		//> the max when divided by 10
	            (reverse == Integer.MAX_VALUE / 10 && digit > 7)) {
	        	//and if reverse/10 equals the max value, then the last digit matters
	        	//given the max number, it must be 0-7. If 8,9 -- overflow (goes over max) 
	            return 0;
	        }

	        if (reverse < Integer.MIN_VALUE / 10 ||
	            (reverse == Integer.MIN_VALUE / 10 && digit < -8)) {
	        	//this is the same but for the negative/min number
	            return 0;
	        }
	        //OVERFLOW CHECK END

	        reverse = reverse * 10 + digit;
	    }

	    return reverse;
	}
	
	public static void main(String[] args) {
		System.out.println(reverse(205));
		System.out.println(reverse(20));
		System.out.println(reverse(-20)); 
		System.out.println(reverse(2147483647)); //doing overflow, not catching
	}
	
	/*
	 * REVERSE INTEGER PATTERNS / THINGS TO REMEMBER
	 *
	 * -------------------------------------------------
	 * 1. CORE INTEGER DIGIT PATTERN
	 * -------------------------------------------------
	 * These 3 operations appear CONSTANTLY in integer
	 * manipulation problems:
	 *
	 * GET LAST DIGIT:
	 *
	 *     digit = x % 10
	 *
	 * REMOVE LAST DIGIT:
	 *
	 *     x = x / 10
	 *
	 * APPEND DIGIT TO NEW NUMBER:
	 *
	 *     reverse = reverse * 10 + digit
	 *
	 * Example:
	 *
	 *     x = 123
	 *
	 *     digit = 3
	 *     reverse = 0 * 10 + 3 = 3
	 *     x = 12
	 *
	 * -------------------------------------------------
	 *
	 * 2. IMPORTANT OVERFLOW LESSON
	 * -------------------------------------------------
	 * Java integer overflow DOES NOT throw exceptions.
	 *
	 * This will NOT work:
	 *
	 *     try/catch
	 *
	 * because Java silently wraps overflowed ints into
	 * corrupted values.
	 *
	 * Example:
	 *
	 *     2147483647 + 1
	 *
	 * becomes:
	 *
	 *     -2147483648
	 *
	 * automatically.
	 *
	 * -------------------------------------------------
	 *
	 * 3. IMPORTANT OVERFLOW PATTERN
	 * -------------------------------------------------
	 * NEVER check:
	 *
	 *     "Did overflow happen?"
	 *
	 * AFTER the operation.
	 *
	 * By then:
	 *
	 *     value already corrupted
	 *
	 * Instead check:
	 *
	 *     "WOULD overflow happen?"
	 *
	 * BEFORE the operation.
	 *
	 * -------------------------------------------------
	 *
	 * 4. WHY Integer.MAX_VALUE / 10?
	 * -------------------------------------------------
	 * Before doing:
	 *
	 *     reverse = reverse * 10 + digit
	 *
	 * we ask:
	 *
	 *     "Will multiplying by 10 overflow?"
	 *
	 * Max int:
	 *
	 *     2147483647
	 *
	 * Therefore:
	 *
	 *     214748364
	 *
	 * is the LAST safe number before multiplying by 10.
	 *
	 * Because:
	 *
	 *     214748364 * 10 = 2147483640
	 *
	 * still safe.
	 *
	 * BUT:
	 *
	 *     214748365 * 10
	 *
	 * already overflows.
	 *
	 * -------------------------------------------------
	 *
	 * 5. WHY CHECK digit > 7?
	 * -------------------------------------------------
	 * If:
	 *
	 *     reverse == 214748364
	 *
	 * we are EXACTLY at the edge.
	 *
	 * The LAST digit can only be:
	 *
	 *     0-7
	 *
	 * because:
	 *
	 *     2147483647
	 *
	 * is max int.
	 *
	 * Therefore:
	 *
	 *     digit > 7
	 *
	 * means overflow would occur.
	 *
	 * -------------------------------------------------
	 *
	 * 6. WHY digit < -8?
	 * -------------------------------------------------
	 * Min int:
	 *
	 *     -2147483648
	 *
	 * Therefore:
	 *
	 *     -8
	 *
	 * is the lowest safe final digit.
	 *
	 * -------------------------------------------------
	 *
	 * 7. IMPORTANT NEGATIVE NUMBER LESSON
	 * -------------------------------------------------
	 * DO NOT convert negatives to positives:
	 *
	 *     x = x * -1
	 *
	 * because:
	 *
	 *     Integer.MIN_VALUE
	 *
	 * cannot become positive inside int range.
	 *
	 * Instead:
	 *
	 *     let modulo/division naturally handle negatives
	 *
	 * Java supports:
	 *
	 *     -123 % 10 = -3
	 *     -123 / 10 = -12
	 *
	 * -------------------------------------------------
	 *
	 * 8. BIG-O FOR INTEGER PROBLEMS
	 * -------------------------------------------------
	 * Runtime depends on:
	 *
	 *     number of digits
	 *
	 * NOT:
	 *
	 *     numeric value itself
	 *
	 * Example:
	 *
	 *     1,000,000,000
	 *
	 * huge value
	 * BUT only 10 digits.
	 *
	 * Each iteration removes one digit:
	 *
	 *     x = x / 10
	 *
	 * Therefore:
	 *
	 *     Time = O(d)
	 *
	 * where:
	 *
	 *     d = number of digits
	 *
	 * -------------------------------------------------
	 *
	 * 9. WHY O(log x)?
	 * -------------------------------------------------
	 * Digits grow logarithmically relative to number value.
	 *
	 * Example:
	 *
	 *     9       -> 1 digit
	 *     99      -> 2 digits
	 *     999     -> 3 digits
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
	 * 10. SPACE COMPLEXITY LESSON
	 * -------------------------------------------------
	 * Space complexity measures:
	 *
	 *     EXTRA MEMORY allocated
	 *
	 * NOT:
	 *
	 *     how large numeric values become
	 *
	 * Example:
	 *
	 *     int reverse = 1;
	 *     reverse = 123456789;
	 *
	 * still only:
	 *
	 *     ONE integer variable
	 *
	 * Therefore:
	 *
	 *     O(1) space
	 *
	 * Primitive variables are treated as:
	 *
	 *     constant-size memory
	 *
	 * -------------------------------------------------
	 *
	 * 11. FINAL BIG-O
	 * -------------------------------------------------
	 * Time:
	 *
	 *     O(log x)
	 *
	 * because one digit removed each iteration.
	 *
	 * Space:
	 *
	 *     O(1)
	 *
	 * because only a few integer variables are used.
	 *
	 * -------------------------------------------------
	 *
	 * 12. INTERVIEW PATTERN RECOGNITION
	 * -------------------------------------------------
	 * If problem says:
	 *
	 *     reverse digits
	 *     integer manipulation
	 *     no 64-bit integers
	 *
	 * think:
	 *
	 *     modulo/division extraction
	 *     overflow BEFORE arithmetic
	 *
	 * If arithmetic can overflow:
	 *
	 * ALWAYS think:
	 *
	 *     "Check BEFORE operation."
	 *
	 */
	
}
