package afterOptimizing;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParenthesis {
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>(); //O(1)
	        for (int i = 0; i < s.length(); i++) { //O(n) 
	        	char currentChar = s.charAt(i);

	        	//push the expected closing instead when we encounter
	        	//an opening
	        	
	        	//instead of storing what DID open, store what MUST close it later
	            if (currentChar == '(') {
	                stack.push(')');
	            } else if (currentChar == '{') {
	                stack.push('}');
	            } else if (currentChar == '[') {
	                stack.push(']');
	            } else {
	            	//in here when we encounter a closing bracket, you need to make sure
	            	//it is the closing bracket we are expecting. So we can just match with
	            	//the expected parenthesis we pushed earlier ooooooh I see
	                if (stack.isEmpty() || stack.pop() != currentChar) {
	                    return false;
	                }
	            }//Above is all O(1)
	        }

	        return stack.isEmpty(); //if we get to the end, stack should be empty and it means there
	        //were all matches, so we return true
	        //but in the case of (((, stack is not empty by the time we reach the end of the string
	        //so when we call this, we return stack.IsEmpty() which is false
	    }

	    public static void main(String[] args) {
	        String test1 = "[]";      // true
	        String test2 = "[([])]";  // true
	        String test3 = "[]][";    // false
	        String test4 = "]";       // false
	        String test5 = "[[[";     // false

	        System.out.println(isValid(test1));
	        System.out.println(isValid(test2));
	        System.out.println(isValid(test3));
	        System.out.println(isValid(test4));
	        System.out.println(isValid(test5));
	    }
	    
	    /*
	     * VALID PARENTHESES / STACK PATTERNS TO REMEMBER
	     *
	     * -------------------------------------------------
	     * 1. WHY A STACK?
	     * -------------------------------------------------
	     * Parentheses problems are usually STACK problems because:
	     *
	     *     Last opened = first closed
	     *
	     * Example:
	     *
	     *     [ ( ) ]
	     *
	     * '(' was opened LAST,
	     * so it must close FIRST.
	     *
	     * That is:
	     *
	     *     LIFO = Last In, First Out
	     *
	     * which is exactly how a stack behaves.
	     *
	     * -------------------------------------------------
	     *
	     * 2. TWO VALID STACK APPROACHES
	     * -------------------------------------------------
	     *
	     * APPROACH A: Store opening brackets
	     *
	     *     push('(')
	     *
	     * then later:
	     *
	     *     pop() == '(' ?
	     *
	     * This works but requires multiple matching checks.
	     *
	     * -------------------------------------------------
	     *
	     * APPROACH B (cleaner): Store EXPECTED closing brackets
	     *
	     * When seeing:
	     *
	     *     '(' -> push(')')
	     *     '[' -> push(']')
	     *     '{' -> push('}')
	     *
	     * Then when we encounter a closer:
	     *
	     *     if(stack.pop() != currentChar)
	     *
	     * This is cleaner because:
	     * - only ONE comparison needed
	     * - no giant nested if-statements
	     *
	     * BIG mental shift:
	     *
	     * Stack is NOT storing:
	     *
	     *     "what opened"
	     *
	     * Stack IS storing:
	     *
	     *     "what closing bracket am I still waiting for?"
	     *
	     * -------------------------------------------------
	     *
	     * 3. STACK.EMPTY() AT THE END
	     * -------------------------------------------------
	     * VERY important edge case.
	     *
	     * Example:
	     *
	     *     "((("
	     *
	     * Nothing mismatched,
	     * BUT we are still waiting for closers.
	     *
	     * Stack still contains:
	     *
	     *     ')'
	     *     ')'
	     *     ')'
	     *
	     * Therefore:
	     *
	     *     return stack.isEmpty();
	     *
	     * means:
	     *
	     *     "Did everything that opened eventually close?"
	     *
	     * Empty stack = valid
	     * Non-empty stack = invalid
	     *
	     * -------------------------------------------------
	     *
	     * 4. NULL / EMPTY STACK CHECKS
	     * -------------------------------------------------
	     * Before popping:
	     *
	     * ALWAYS check:
	     *
	     *     stack.isEmpty()
	     *
	     * Otherwise:
	     *
	     *     stack.pop()
	     *
	     * can throw exceptions.
	     *
	     * Common safe pattern:
	     *
	     *     if(stack.isEmpty() || stack.pop() != currentChar)
	     *
	     * -------------------------------------------------
	     *
	     * 5. COMMON STACK PATTERN RECOGNITION
	     * -------------------------------------------------
	     * If problem involves:
	     *
	     * - nested structures
	     * - matching pairs
	     * - reversing order
	     * - "most recent" tracking
	     * - undo behavior
	     * - balanced expressions
	     *
	     * THINK:
	     *
	     *     "Should I use a stack?"
	     *
	     * -------------------------------------------------
	     *
	     * 6. BIG-O PATTERN
	     * -------------------------------------------------
	     * One pass through string:
	     *
	     *     Time: O(n)
	     *
	     * Worst case:
	     *
	     *     every char gets pushed onto stack
	     *
	     * so:
	     *
	     *     Space: O(n)
	     *
	     * Example worst case:
	     *
	     *     "(((([[[{{{"
	     *
	     * -------------------------------------------------
	     *
	     * 7. EDGE CASES TO ALWAYS TEST
	     * -------------------------------------------------
	     *
	     * Empty string:
	     *
	     *     ""
	     *
	     * Single closing bracket:
	     *
	     *     "]"
	     *
	     * Only opening brackets:
	     *
	     *     "((("
	     *
	     * Wrong nesting:
	     *
	     *     "([)]"
	     *
	     * Correct nesting:
	     *
	     *     "[({})]"
	     *
	     * -------------------------------------------------
	     *
	     * 8. INTERVIEW TAKEAWAY
	     * -------------------------------------------------
	     * If I find myself:
	     *
	     * - matching things
	     * - repeatedly checking "latest opened"
	     * - needing reverse order behavior
	     *
	     * THINK:
	     *
	     *     STACK
	     *
	     */
	    /*
	     * 9. IMPORTANT INTERVIEW NOTE
	     * -------------------------------------------------
	     * My ORIGINAL solution using opening brackets was ALSO optimized.
	     *
	     * Example:
	     *
	     *     push('(')
	     *     later pop() and compare to '('
	     *
	     * That solution still achieved:
	     *
	     *     Time: O(n)
	     *     Space: O(n)
	     *
	     * The only issue was forgetting:
	     *
	     *     return stack.isEmpty();
	     *
	     * at the end.
	     *
	     * Without that check:
	     *
	     *     "((("
	     *
	     * would incorrectly return true because
	     * no mismatch occurred, even though brackets
	     * were still left open.
	     *
	     * The "expected closer" solution:
	     *
	     *     push(')')
	     *
	     * is NOT more optimized.
	     *
	     * It is simply:
	     *
	     * - cleaner
	     * - fewer conditionals
	     * - easier to read
	     *
	     * IMPORTANT:
	     *
	     * Multiple solutions can have the SAME Big-O.
	     *
	     * Optimization is not always about runtime.
	     * Sometimes it is:
	     *
	     * - readability
	     * - maintainability
	     * - reducing edge cases
	     * - simplifying logic
	     *
	     */

}
