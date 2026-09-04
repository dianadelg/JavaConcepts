package beforeOptimizing;

public class ValidPalindrome {
	
//	A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
//
//	Given a string s, return true if it is a palindrome, or false otherwise.
	
	
	/*
	 * Notes: empty string is a palindrome
	 * Need to remove spaces, punctuation, capitalization doesn't matter
	 * 
	 * consider \n and \t, maybe an edge case?
	 */
	
    public static boolean isPalindrome(String s) {
        //make to lower case
    	s = s.toLowerCase(); //O(1) space and complexity
    	
    	//remove spaces
    	s = s.replace(" ", ""); //O(n) runtime, O(1) space
    	
    	if(s.equals("")) {
    		//empty string, return true
    		return true;
    	}
    	
    	//remove punctuation
    	s = s.replaceAll("\\p{Punct}", ""); //O(n) runtime, O(1) space
    	
    	for(int i=0; i<s.length()/2; i++){
    		if(s.charAt(i)!=s.charAt(s.length()-i-1)) {
    			return false;
    		}
    	}
    	return true;
    	
    	//then two pointers like palindrome number 
    }
	
	public static void main (String[] args) {
		System.out.println(isPalindrome("A man, a plan, a canal: Panama")); //true
		System.out.println(isPalindrome("race a car")); //false
		System.out.println(isPalindrome("  ")); //empty string is true
	}
	
	/*
	 * TO DO: edge cases, need to thoroughly test edge cases
	 */

}
