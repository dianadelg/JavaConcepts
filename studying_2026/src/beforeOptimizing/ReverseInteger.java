package beforeOptimizing;

public class ReverseInteger {
	
//	Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
//
//			Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
//
//			 
//
//			Example 1:
//
//			Input: x = 123
//			Output: 321
//			Example 2:
//
//			Input: x = -123
//			Output: -321
//			Example 3:
//
//			Input: x = 120
//			Output: 21
//			 
//
//			Constraints:
//
//			-231 <= x <= 231 - 1
	
    public static int reverse(int x) {
        //thinking of doing the same algo for palindrome
    	int original = x;
    	int reverse = 0;
    	if(x<0) {
    		x=x*-1; //set to positive for now
    	}
    	while(x>0) {
    		//Get last digit -- x % 10 
    		//Remove last digit -- x / 10
    		//Append digit to another number -- newNum = newNum * 10 + digit
    		try{
    			reverse = reverse * 10 + (x%10);
    		}catch(ArithmeticException e) {
    			return 0;
    		};
    		x=x/10;
    	}
    	if(original < 0) {
    		return -1*reverse;
    	}else {
    		return reverse;
    	}
    }
	
	
	public static void main(String[] args) {
		System.out.println(reverse(205));
		System.out.println(reverse(20));
		System.out.println(reverse(-20)); 
		System.out.println(reverse(2147483647)); //doing overflow, not catching
	}

}
