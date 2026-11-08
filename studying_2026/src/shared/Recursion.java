package shared;

public class Recursion {

	/*
	 * Recursion is when a method calls itself to solve a smaller version of the
	 * same problem.
	 * 
	 * So instead of writing a loop that handles every step, we write a method that
	 * says "I know how to handle a piece of this porblem, I'll call myself to
	 * handle the next piece.
	 * 
	 * EVERY RECURSIVE METHOD NEEDS 1. Base case -- the condition that stops
	 * recursion 2. Recursive case -- the part where the method calls itself, making
	 * progress towards the base case
	 */

	// simple case
	public static void countdown(int n) {
		// Base case -- stop when n reaches 0
		if (n == 0) {
			System.out.println("Countdown done!");
			return;
		}

		// process the current number aka print
		System.out.println(n);

		// Recursive Case: call ourselves with a smaller number
		countdown(n - 1);

		// so we print n
		// then do countdown(n-1) which prints n again
		// until we reach n = 0

		// This is where the call stack comes in!!!

		/*
		 * Java uses a call stack to keep track of active method calls When a method is
		 * called, Java adds a new stack frame which contains info associated with that
		 * invocation, like local vars and where execution should resume when the method
		 * returns. And once the method finishes, frame is removed
		 * 
		 * so it's like if n = 3 call countdown(3) call countdown(2) call countdown(1)
		 * call countdown(0) THEN completes 0 --> base case so removes countdown(0) then
		 * removes countdown(1) then removes countdown(2) then removes countdown(3) and
		 * stack is empty
		 * 
		 * This ^^ process is called backtracking. The call stack makes this possible
		 * without manually keeping track of the entire path.
		 * 
		 * 
		 * This is particularly relevant for DFS because we use the call stack to
		 * remember where it came from
		 */
	}

	// other common examples
	public static int fib(int n) {
		// Base cases: returns 0 for 0, and 1 for 1
		if (n <= 1) {
			return n;
		}
		// Recursive case: sum of the two preceding numbers
		return fib(n - 1) + fib(n - 2);
	}

	public static void main(String[] args) {
		countdown(5);
		System.out.println();
		System.out.println(fib(4));
	}
}
