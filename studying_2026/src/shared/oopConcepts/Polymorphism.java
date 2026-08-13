package shared.oopConcepts;

/*
 * POLYMORPHISM
 * "One interface, many behaviors" — the same method call/name behaves
 * differently depending on context.
 *
 * Two flavors shown here:
 *   1. Runtime polymorphism (method OVERRIDING) — resolved at runtime,
 *      based on the actual object type, not the reference type.
 *   2. Compile-time polymorphism (method OVERLOADING) — resolved at
 *      compile time, based on the arguments you pass.
 */
public class Polymorphism {

    // ---------- 1. RUNTIME POLYMORPHISM (overriding) ----------

    // Parent class defines the shared behavior (the "interface")
    static abstract class Animal {
        abstract String makeSound();
    }

    // Each subclass overrides makeSound() with its own behavior
    static class Dog extends Animal {
        String makeSound() { return "Woof"; }
    }

    static class Cat extends Animal {
        String makeSound() { return "Meow"; }
    }

    static class Cow extends Animal {
        String makeSound() { return "Moo"; }
    }

    // ---------- 2. COMPILE-TIME POLYMORPHISM (overloading) ----------

    // Same method name, different parameter lists.
    // Java picks the right version based on what you pass in, at compile time.
    static class Calculator {
        int add(int a, int b) {
            return a + b;
        }

        double add(double a, double b) {
            return a + b;
        }

        int add(int a, int b, int c) {
            return a + b + c;
        }
    }

    public static void main(String[] args) {

        // --- Runtime polymorphism in action ---
        Animal[] animals = { new Dog(), new Cat(), new Cow() };

        // Same call, a.makeSound(), but different behavior each time.
        // The reference type is Animal, but Java looks at the ACTUAL
        // object (Dog/Cat/Cow) at runtime to decide which method runs.
        // This is called "dynamic dispatch".
        for (Animal a : animals) {
            System.out.println(a.makeSound());
        }

        // --- Compile-time polymorphism in action ---
        Calculator calc = new Calculator();

        // Same method name "add", but the compiler picks the version
        // that matches the argument types/count, BEFORE the program runs.
        System.out.println(calc.add(2, 3));          // uses add(int, int)
        System.out.println(calc.add(2.5, 3.5));       // uses add(double, double)
        System.out.println(calc.add(1, 2, 3));        // uses add(int, int, int)
    }
}
