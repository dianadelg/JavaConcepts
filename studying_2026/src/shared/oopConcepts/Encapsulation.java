package shared.oopConcepts;

/*
 * ENCAPSULATION
 * Bundling data (fields) together with the methods that operate on it,
 * and restricting direct outside access to that data.
 *
 * In practice this means:
 *   - Fields are usually `private` — no one outside the class can
 *     touch them directly.
 *   - Public "getter" and "setter" methods control HOW those fields
 *     are read or changed.
 *
 * Why bother?
 *   - You can validate data before it's set (e.g. reject a negative
 *     balance) instead of trusting outside code to behave.
 *   - You can change the internal implementation later without
 *     breaking any code that uses the class, since outside code only
 *     ever talks to it through the public methods.
 *   - It hides complexity — the user of the class doesn't need to know
 *     HOW balance is stored/validated, just that getBalance() works.
 *
 * Access modifiers used here:
 *   private -> only accessible inside this class
 *   public  -> accessible from anywhere
 */
public class Encapsulation {

	static class BankAccount {

		// private field: no outside code can do account.balance = -500
		// directly. It can only go through the methods below.
		private double balance;

		BankAccount(double startingBalance) {
			// Even the constructor routes through the validation logic
			// in deposit(), instead of setting the field directly.
			this.balance = 0;
			if (startingBalance > 0) {
				deposit(startingBalance);
			}
		}

		// public getter: controlled READ access
		double getBalance() {
			return balance;
		}

		// public setter-style method: controlled WRITE access, with validation
		void deposit(double amount) {
			if (amount <= 0) {
				System.out.println("Deposit amount must be positive. Ignored.");
				return;
			}
			balance += amount;
		}

		void withdraw(double amount) {
			if (amount <= 0) {
				System.out.println("Withdrawal amount must be positive. Ignored.");
				return;
			}
			if (amount > balance) {
				System.out.println("Insufficient funds. Withdrawal denied.");
				return;
			}
			balance -= amount;
		}
	}

	public static void main(String[] args) {
		BankAccount account = new BankAccount(100);

		// account.balance = -500; // would NOT compile — balance is private
		/*
		 * the whole point of encapsulation. If balance were public, any code anywhere
		 * could set it to a negative number, bypass all your validation logic, and put
		 * the account in a broken state. Making it private forces every change to go
		 * through deposit()/withdraw(), which means the class itself guarantees its own
		 * rules are followed, no matter who's using it.
		 */

		System.out.println("Starting balance: " + account.getBalance());

		account.deposit(50);
		System.out.println("After deposit: " + account.getBalance());

		account.withdraw(30);
		System.out.println("After withdrawal: " + account.getBalance());

		account.withdraw(1000); // rejected, prints a message, balance unchanged
		System.out.println("After failed withdrawal: " + account.getBalance());

		account.deposit(-20); // rejected, prints a message, balance unchanged
		System.out.println("After failed deposit: " + account.getBalance());
	}
	
	/*
	 * MENTAL MODEL: THE VENDING MACHINE
	 *
	 * Think of a public method like the button on a vending machine.
	 * You press the button, a snack comes out. That button is the only
	 * thing you're allowed to interact with from the outside.
	 *
	 * Inside the machine, the snacks could be on a spinning rack, a
	 * conveyor belt, robot arms — you have no idea, and you don't care.
	 * The company could completely redesign the inside, and as long as
	 * pressing the same button still gives you the same snack, you'd
	 * never notice anything changed.
	 *
	 * Same idea here: the PUBLIC METHOD (button) keeps the same name and
	 * return type. The PRIVATE FIELD (inside mechanism) can change freely
	 * behind it.
	 *
	 *
	 * HOW THIS WORKS MECHANICALLY (using getBalance() as the example):
	 *
	 *   private int balanceInCents;
	 *
	 *   double getBalance() {
	 *       return balanceInCents / 100.0;
	 *   }
	 *
	 * Say balanceInCents = 5000. Step by step when getBalance() runs:
	 *   1. Java reads the field: balanceInCents is 5000 (an int).
	 *   2. It runs the math: 5000 / 100.0 = 50.0 (a double, because
	 *      100.0 forces the division to happen in double).
	 *   3. That 50.0 is a brand new value, built fresh on this line —
	 *      it's not the field itself being sent out.
	 *   4. return sends that 50.0 back out, matching the method's
	 *      promised return type of double.
	 *
	 * The field never leaves the method. The method peeks at the private
	 * field, does some conversion/math on it, and only ships out the
	 * final result. The caller never sees balanceInCents directly — only
	 * whatever the return line produces.
	 *
	 *
	 * WIDENING vs NARROWING (why no cast was needed above):
	 *
	 * int -> double is a WIDENING conversion (small type -> bigger type
	 * that can hold everything the small one could, plus more). Java does
	 * this automatically, no cast required. That's why
	 * "return balanceInCents;" alone would even compile in a method that
	 * returns double — Java auto-converts the int to a double for you.
	 *
	 * Going the other direction (double -> int) is NARROWING, and risks
	 * losing data (the decimal part), so Java forces you to write an
	 * explicit cast, e.g.:
	 *     int amountInCents = (int) Math.round(amount * 100);
	 */
	
	/*
	 * WHY PRIVATE FIELDS LET YOU CHANGE INTERNALS SAFELY:
	 *
	 * Say later you switch `balance` from a double to storing cents as an
	 * int (to avoid floating-point rounding errors with money). Since
	 * `balance` is private, no outside code ever touched it directly —
	 * everyone only calls getBalance() / deposit() / withdraw(). So you can
	 * rewrite the internal math however you want, and as long as those
	 * public method signatures stay the same, every bit of code that uses
	 * this class keeps working, completely unchanged.
	 *
	 * If `balance` had been public, every place that did `account.balance`
	 * directly would break the moment the internal type changed, forcing
	 * you to hunt down and fix every usage across the whole project.
	 */
}
