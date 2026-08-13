package shared.oopConcepts;

/*
 * ABSTRACTION
 * Hiding HOW something works and exposing only WHAT it does.
 *
 * Encapsulation vs Abstraction (easy to mix up):
 *   - Encapsulation hides DATA (fields) — e.g. making `balance` private.
 *   - Abstraction hides IMPLEMENTATION DETAILS — e.g. you call
 *     `startEngine()` on a car without knowing how the engine
 *     actually starts internally.
 *
 * In Java, abstraction is usually done with:
 *   - abstract classes (can have some implemented methods AND some
 *     unimplemented "abstract" methods that subclasses must fill in)
 *   - interfaces (a pure contract — just method signatures, no
 *     implementation, until a class implements them)
 *
 * The caller only needs to know WHAT method to call. They don't need
 * to know, or care, HOW each specific class implements it.
 */
public class Abstraction {

	// ---------- Abstract class = a partial blueprint ----------
	// You can never do `new Shape()` directly — it's incomplete on purpose.
	// It defines WHAT every Shape must be able to do (calculateArea()),
	// but not HOW, since that depends on the specific shape.
	static abstract class Shape {

		// Abstract method: no body here at all. Just a promise that
		// every subclass MUST provide its own implementation.
		abstract double calculateArea();

		// Abstract classes CAN also have regular, fully implemented
		// methods that subclasses inherit as-is.
		void describe() {
			System.out.println("This shape has an area of " + calculateArea());
		}
	}

	// ---------- Concrete classes = the "how" ----------
	static class Circle extends Shape {
		private double radius;

		Circle(double radius) {
			this.radius = radius;
		}

		// Circle provides ITS OWN "how" for calculateArea()
		@Override
		double calculateArea() {
			return Math.PI * radius * radius;
		}
	}

	static class Rectangle extends Shape {
		private double width;
		private double height;

		Rectangle(double width, double height) {
			this.width = width;
			this.height = height;
		}

		// Rectangle provides a COMPLETELY DIFFERENT "how"
		@Override
		double calculateArea() {
			return width * height;
		}
	}

	public static void main(String[] args) {
		Shape circle = new Circle(5);
		Shape rectangle = new Rectangle(4, 6);

		// describe() is called the exact same way for both, and neither
		// caller needs to know the actual math happening inside
		// calculateArea() — that detail is fully hidden away.
		circle.describe();
		rectangle.describe();

		// Shape shape = new Shape(); // would NOT compile —
		// abstract classes can never be instantiated directly
	}
	// exposing a simple, uniform way to interact with something, while burying the
	// messy specifics inside each class.

//	One thing worth flagging since it trips people up: this looks a lot like the Animal/Dog/Cat 
	// polymorphism example from earlier — same shape (abstract parent, overriding subclasses). 
	//The difference is really about emphasis: polymorphism is about the "same call, different 
	//behavior at runtime" trick; abstraction is about "why we bothered hiding the details in the 
	//first place." In practice they usually show up together in the same code, which is why the 
	//examples look similar.
	
	
	/*
	 * WHY BOTHER WITH ABSTRACTION? (the actual motivation)
	 *
	 * 1. You can write code that works with things you haven't built yet.
	 *
	 *    abstract class PaymentMethod {
	 *        abstract void processPayment(double amount);
	 *    }
	 *
	 *    Checkout code can be written TODAY, calling
	 *    paymentMethod.processPayment(50.00), before CreditCard, PayPal,
	 *    or ApplePay even exist. Six months later someone adds
	 *    "Bitcoin extends PaymentMethod" and the checkout code doesn't
	 *    change at all — it already knows how to call processPayment()
	 *    on anything that IS-A PaymentMethod. The checkout code depends
	 *    on the CONTRACT, not on specific classes.
	 *
	 * 2. You can treat wildly different things the same way, in one list.
	 *
	 *    List<Shape> shapes = List.of(new Circle(5), new Rectangle(4, 6));
	 *    double totalArea = 0;
	 *    for (Shape s : shapes) {
	 *        totalArea += s.calculateArea();
	 *    }
	 *
	 *    Without abstraction you'd need separate lists and separate loops
	 *    per shape type — way more code, and it breaks every time you add
	 *    a new shape.
	 *
	 * 3. It protects against mistakes.
	 *
	 *    If Shape forces every subclass to implement calculateArea(), it's
	 *    literally impossible to create a shape that forgets to define its
	 *    own area logic — the compiler won't let you. It's a safety net.
	 *
	 * 4. Teams can work in parallel without stepping on each other.
	 *
	 *    One person writes checkout code against PaymentMethod while
	 *    someone else is still building CreditCard's actual implementation.
	 *    They agree on the contract (method signatures) up front, then
	 *    work independently.
	 *
	 * BOTTOM LINE: abstraction isn't about hiding for hiding's sake — it's
	 * about DECOUPLING "code that uses a thing" from "code that implements
	 * the thing," so each side can change, grow, or be built independently
	 * without breaking the other.
	 */
}