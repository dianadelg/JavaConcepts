package shared.oopConcepts;

/*
 * INHERITANCE
 * A class ("subclass" / "child") can inherit fields and methods from
 * another class ("superclass" / "parent") using the `extends` keyword.
 *
 * This gives you:
 *   - Code reuse: shared fields/methods live in one place (the parent).
 *   - The "is-a" relationship: a Dog IS-A Animal, a Cat IS-A Animal.
 *   - The foundation for polymorphism (see PolymorphismDemo).
 *
 * Key keywords:
 *   extends  -> declares the parent class
 *   super    -> calls the parent's constructor or methods
 *   @Override -> (optional but good practice) marks a method that
 *                replaces the parent's version
 */
public class Inheritance {
	
	/*
	 * INHERITANCE
	 * A class ("subclass" / "child") can inherit fields and methods from
	 * another class ("superclass" / "parent") using the `extends` keyword.
	 *
	 * This gives you:
	 *   - Code reuse: shared fields/methods live in one place (the parent).
	 *   - The "is-a" relationship: a Dog IS-A Animal, a Cat IS-A Animal.
	 *   - The foundation for polymorphism (see PolymorphismDemo).
	 *
	 * Key keywords:
	 *   extends   -> declares the parent class
	 *   super     -> calls the parent's constructor or methods
	 *   @Override -> (optional but good practice) marks a method that
	 *                replaces the parent's version
	 *
	 *
	 * NOTE ON WHY THIS FILE LOOKS THE WAY IT DOES:
	 *
	 * `InheritanceDemo` isn't really a "class" in the OOP sense here — it's
	 * just a container used to bundle everything into one file so it's easy
	 * to paste in. This is a common pattern for demo/practice files.
	 *
	 * NOTE ON `static` ON THE NESTED CLASSES (Animal, Dog, Cat):
	 *
	 * `static` on a nested class means: "this inner class doesn't need an
	 * instance of the outer class (InheritanceDemo) to exist." Without
	 * `static`, you'd have to write something clunky like:
	 *
	 *     InheritanceDemo demo = new InheritanceDemo();
	 *     InheritanceDemo.Dog dog = demo.new Dog("Rex");
	 *
	 * — the inner class would be tied to a specific outer instance. `static`
	 * just cuts that tie so `Dog` behaves like its own independent class.
	 *
	 * So `static` here is really about the relationship between
	 * Animal/Dog/Cat and InheritanceDemo — it has nothing to do with how
	 * Dog/Cat/Animal behave among themselves.
	 *
	 * (If each class lived in its own file instead of being nested here,
	 * none of them would need the `static` keyword at all — top-level
	 * classes are never declared static.)
	 */

    // ---------- Parent class ----------
    static class Animal {
        String name;

        Animal(String name) {
            this.name = name;
        }

        // A method every Animal has by default
        void eat() {
            System.out.println(name + " is eating");
        }

        void makeSound() {
            System.out.println(name + " makes a sound");
        }
    }

    // ---------- Child class ----------
    // Dog inherits `name` and `eat()` for free.
    // It only needs to add what's DIFFERENT about a Dog.
    static class Dog extends Animal {

        Dog(String name) {
            super(name); // calls Animal's constructor to set `name`
        }

        // Overriding: replacing the parent's version with a Dog-specific one
        @Override
        void makeSound() {
            System.out.println(name + " says Woof");
        }

        // A method that's unique to Dog — Animal doesn't have this
        void fetch() {
            System.out.println(name + " fetches the ball");
        }
    }

    // ---------- Another child class ----------
    static class Cat extends Animal {

        Cat(String name) {
            super(name);
        }

        @Override
        void makeSound() {
            System.out.println(name + " says Meow");
        }
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Fig");
        Cat cat = new Cat("Salem");

        dog.eat();        // inherited from Animal, not redefined in Dog
        dog.makeSound();  // overridden in Dog
        dog.fetch();      // exists only in Dog

        cat.eat();         // inherited from Animal
        cat.makeSound();   // overridden in Cat
        // cat.fetch();    // would NOT compile — fetch() only exists on Dog
    }
}