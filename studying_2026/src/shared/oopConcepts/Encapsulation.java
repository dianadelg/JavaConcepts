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
