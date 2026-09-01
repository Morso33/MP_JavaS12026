public class UIApplication {
    public static void main(String[] args) {
        System.out.println("=== Style A UI Elements ===\n");
        demonstrateStyle(new AFactory());

        System.out.println("\n=== Style B UI Elements ===\n");
        demonstrateStyle(new BFactory());

        System.out.println("\n=== Dynamic Text Update Demo ===\n");
        UIFactory factory = new BFactory();
        Button button = factory.createButton("Click Me");
        TextField textField = factory.createTextField("Enter text");
        Checkbox checkbox = factory.createCheckbox("Accept terms");

        System.out.println("Initial state:");
        button.display();
        textField.display();
        checkbox.display();

        System.out.println("\nAfter setText():");
        button.setText("Submit");
        textField.setText("Hello World");
        checkbox.setText("Agree to policy");

        button.display();
        textField.display();
        checkbox.display();
    }

    private static void demonstrateStyle(UIFactory factory) {
        Button button = factory.createButton("Click Me");
        TextField textField = factory.createTextField("Enter text");
        Checkbox checkbox = factory.createCheckbox("Accept terms");

        System.out.println("Button:");
        button.display();

        System.out.println("\nTextField:");
        textField.display();

        System.out.println("\nCheckbox:");
        checkbox.display();
    }
}
