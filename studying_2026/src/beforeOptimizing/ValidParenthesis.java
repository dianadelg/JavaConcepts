package beforeOptimizing;
import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParenthesis {
//	Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//	An input string is valid if:
//
//	Open brackets must be closed by the same type of brackets.
//	Open brackets must be closed in the correct order.
//	Every close bracket has a corresponding open bracket of the same type.
	
	public static boolean isValid(String s) {
		//Algorithm -- push each letter of the string onto the stack -- O(n) where n is length of string at max
		//As we go through the string, add to stack. If we get a closed parenthesis }]) -- pop from stack and see if corresponding
		//if yes, keep going until s is completely gone through. If we hit a mismatch beforehand, return false
		Deque<Character> stack = new ArrayDeque<>(); //Space O(n) at worse case
		
		//[{}]
		for(int i=0; i<s.length(); i++) { //O(n) runtime
			char currentChar = s.charAt(i);
			if(currentChar=='}' || currentChar==')' || currentChar==']') {
				//pop from stack. Check if null first
				if(stack.peek()== null) { // O(1) runtime
					//means nothing on it, or first one
					return false;
				}
				char item = stack.pop();
				if(currentChar == ')') {
					if(item!='(') {
						return false;
					}
				}else if(currentChar == '}') {
					if(item!='{') {
						return false;
					}
				}else if (currentChar == ']'){
					//currentChar 
					if(item!='[') {
						return false;
					}
				}else {
					return false; //will we ever get to this case
				}
			}else {
				//means it's {[(
				stack.push(s.charAt(i));
			}
		}
		return true;
	}
	
	public static void main (String[] args) {
		String test1= "[]"; //true
		String test2= "[([])]"; //true
		String test3= "[]]["; //false
		String test4 = "]"; //false
		
		System.out.println(isValid(test1));
		System.out.println(isValid(test2));		
		System.out.println(isValid(test3));		
		System.out.println(isValid(test4));	
	}
}
