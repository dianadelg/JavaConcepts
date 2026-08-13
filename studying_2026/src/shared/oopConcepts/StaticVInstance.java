package shared.oopConcepts;

public class StaticVInstance {

	/*
	 * NOTE ON STATIC METHODS vs INSTANCE METHODS: (Why you still need `new
	 * Dog(...)` to call `makeSound()`)
	 *
	 * This is a separate, more important concept: static methods vs instance
	 * methods.
	 *
	 * `eat()` and `makeSound()` are instance methods — no `static` keyword on them.
	 * Instance methods belong to a specific object and can use that object's fields
	 * (like `name`). Since `Rex` and `Whiskers` each have their own `name`, Java
	 * needs an actual object in memory to know which `name` to use. That's why you
	 * need `new Dog("Rex")` first.
	 *
	 * If `makeSound()` had been declared `static`, it would belong to the class
	 * itself, not to any one object, and you could call it like `Dog.makeSound()`
	 * without ever creating a `Dog`. But then it couldn't reference `name`, because
	 * static methods don't have access to instance fields — there's no specific
	 * instance to pull `name` from.
	 */

	static class Example {
		String name;

		Example(String name) {
			this.name = name;
		}

		// INSTANCE method: needs an object to run, because it uses `name`
		void instanceGreet() {
			System.out.println("Hello from " + name);
		}

		// STATIC method: belongs to the class, not any object.
		// Can't use `name` here — there's no specific instance to read it from.
		static void staticGreet() {
			System.out.println("Hello from the Example class itself");
		}
	}

	public static void main(String[] args) {
		// Static method: call it directly on the class, no object needed
		Example.staticGreet();

		// Instance method: must create an object first
		Example e = new Example("Diana");
		e.instanceGreet();
	}

	/*
	 * Normally in Java, a "top-level" class like Example would just live in its own
	 * file, Example.java, with no static needed at all — static on a top-level
	 * class isn't even legal syntax.
	 * 
	 * But in these demo files, I'm not making Example a top-level class — I'm
	 * nesting it inside another class (StaticVsInstanceDemo), purely so everything
	 * lives in one file you can paste in at once instead of creating five separate
	 * .java files. That's a convenience choice on my part, not something Java
	 * requires.
	 * 
	 * The moment you nest a class inside another class, Java asks:
	 * "does this inner class need a living instance of the outer class to exist, or can it stand alone?"
	 * By default (no static), the inner class is tied to an instance of the outer
	 * class — meaning you'd need a StaticVsInstanceDemo object before you could
	 * even create an Example. Adding static to the nested class says
	 * "no, Example is independent — treat it basically like its own top-level class that just happens to live inside this file."
	 */
}