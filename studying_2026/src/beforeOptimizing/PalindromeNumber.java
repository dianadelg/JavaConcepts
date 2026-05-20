package beforeOptimizing;

public class PalindromeNumber {

//	Given an integer x, return true if x is a palindrome, and false otherwise.
//
//			 
//
//			Example 1:
//
//			Input: x = 121
//			Output: true
//			Explanation: 121 reads as 121 from left to right and from right to left.
//			Example 2:
//
//			Input: x = -121
//			Output: false
//			Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
//			Example 3:
//
//			Input: x = 10
//			Output: false
//			Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
//			 
//
//			Constraints:
//
//			-231 <= x <= 231 - 1
//			 
//
//			Follow up: Could you solve it without converting the integer to a string?
	
    public static boolean isPalindrome(int x) {
        //brute force: convert to string, use pointer from front and back until intercepts
    	String number = Integer.toString(x); //run: O(n), space O(d) where d is number of digits
    	
    	for(int i=0; i<(number.length()/2); i++) { //O(d/2) at max -- O(d)
    		if(number.charAt(i) != (number.charAt(number.length()-1-i))) { //O(1) check
    			return false;
    		}
    	}
    	return true;
    	//runtime is O(n) + O(d/2) -- O(n)
    	//space complexity: O(d)
    }
    
    public static boolean isPalindrome2(int x) {
        //reverse as int, then compare digit by digit
    	int original = x;
    	int reverse=0; //O(1) space
    	while(x != 0) { //O(d) digits times occurs
    	    int digit = x % 10; 
    	    reverse = reverse * 10 + digit;
    	    x/=10;
    	}
    	
    	return reverse == original; //O(1) check
    	//Runtime O(d) where d is the digits
    }
    //ISSUE: overflow, negative numbers
    
    
	

	public static void main(String[] args) {
		System.out.println(isPalindrome(1234)); //no
		System.out.println(isPalindrome(1234321)); //yes
		System.out.println(isPalindrome(1)); //yes
		
		System.out.println(isPalindrome2(1234)); //no
		System.out.println(isPalindrome2(1234321)); //yes
		System.out.println(isPalindrome2(1)); //yes
	}
	
}
