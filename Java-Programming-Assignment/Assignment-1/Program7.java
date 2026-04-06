// A simple interface to be used by the Anonymous Inner Class
interface Greeting {
    void sayHello();
}

public class OuterClass {
    // Private field of the outer class
    private String outerField = "Outer Class Secret Data";

    /* * 1. MEMBER INNER CLASS
     * Defined directly inside the outer class, outside of any method.
     * It has full access to the outer class's members, including private ones.
     */
    class MemberInnerClass {
        void display() {
            System.out.println("=== 1. Member Inner Class ===");
            System.out.println("I am inside the Member Inner Class.");
            System.out.println("Accessing from outer class: " + outerField);
        }
    }

    /* * 2. LOCAL INNER CLASS
     * Defined inside a block, typically a method. 
     * It can only be instantiated within the method where it is defined.
     */
    public void demonstrateLocalInnerClass() {
        // Local variables accessed by a local inner class must be final or effectively final
        String localVariable = "Local Method Data";

        class LocalInnerClass {
            void printDetails() {
                System.out.println("\n=== 2. Local Inner Class ===");
                System.out.println("I am inside the Local Inner Class.");
                System.out.println("Accessing from outer class: " + outerField);
                System.out.println("Accessing local method variable: " + localVariable);
            }
        }

        // We must instantiate and use it inside this specific method
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.printDetails();
    }

    /* * 3. ANONYMOUS INNER CLASS
     * An inner class declared without a name. 
     * It is instantiated at the exact same time it is declared.
     */
    public void demonstrateAnonymousInnerClass() {
        System.out.println("\n=== 3. Anonymous Inner Class ===");
        
        // Creating an anonymous inner class that implements the Greeting interface
        Greeting anonymousGreeting = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello from the Anonymous Inner Class!");
                System.out.println("I can also access: " + outerField);
            }
        };

        // Calling the method defined in the anonymous class
        anonymousGreeting.sayHello();
    }

    // Main method to run the demonstrations
    public static void main(String[] args) {
        OuterClass outerObject = new OuterClass();

        // Instantiating the Member Inner Class requires an instance of the Outer Class
        OuterClass.MemberInnerClass memberInnerObject = outerObject.new MemberInnerClass();
        memberInnerObject.display();

        // Triggering the method that contains the Local Inner Class
        outerObject.demonstrateLocalInnerClass();

        // Triggering the method that contains the Anonymous Inner Class
        outerObject.demonstrateAnonymousInnerClass();
    }
}
