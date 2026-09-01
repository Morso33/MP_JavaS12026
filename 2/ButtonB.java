public class ButtonB extends Button {
    public ButtonB(String text) {
        super(text);
    }

    @Override
    public void display() {
        int padding = 2;
        int width = text.length() + 2 * padding;
        System.out.println("+" + "-".repeat(width) + "+");
        System.out.println("|" + " ".repeat(padding) + text + " ".repeat(padding) + "|");
        System.out.println("+" + "-".repeat(width) + "+");
    }
}
