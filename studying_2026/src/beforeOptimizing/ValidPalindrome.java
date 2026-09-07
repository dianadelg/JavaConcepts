package beforeOptimizing;

public class ValidPalindrome {
	
//	A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
//
//	Given a string s, return true if it is a palindrome, or false otherwise.
	
	
	/*
	 * Notes: empty string is a palindrome
	 * Need to remove spaces, punctuation, capitalization doesn't matter
	 * 
	 * consider \n and \t, maybe an edge case? -- CAVEAT: s consists only of printable ASCII characters.
	 */
	
    public static boolean isPalindrome(String s) {
        //make to lower case
    	s = s.toLowerCase().replace(" ", ""); //O(n) space -- STRINGS ARE IMMUTABLE -- and O(2n) --> O(n)complexity
    	
    	if(s.equals("")) { //could do s.isEmpty() --> O(1) but this is also O(1)
    		//empty string, return true
    		return true;
    	}
    	
    	//issue -- all of these create new strings multiple times. This is inefficient!!!
    	
    	//could I chain all of these replace, lowercase, replaceAll together to clean it up?
    
    	
    	//remove punctuation
    	s = s.replaceAll("\\p{Punct}", ""); //O(n) runtime, O(n)space -- strings are immutable
    	
    	for(int i=0; i<s.length()/2; i++){ //O(n)/2 -- O(n)
    		if(s.charAt(i)!=s.charAt(s.length()-i-1)) { //O(1) -- string length is O(1) because java stores string length as a field so it's easy to lookup
    			//charAt is direct array indexing, not a traversal --> O(1)
    			return false;
    		}
    	} //loop is O(n) * O(1) = O(n)
    	//space complexity of loop -- O(1) 
    	//O(1) space because the loop only uses a fixed number of primitive variables (i, and the temporary chars from charAt) that don't grow with the input size — it reads from the existing string instead of creating a new one.
    	return true;
    	
    	//then two pointers like palindrome number 
    }
	
	public static void main (String[] args) {
		System.out.println(isPalindrome("A man, a plan, a canal: Panama")); //true
		System.out.println(isPalindrome("race a car")); //false
		System.out.println(isPalindrome("  ")); //empty string is true
	}
	
	/*
	 * TO DO: edge cases, need to thoroughly test edge cases, check runtimes/space complexity
	 */

}
