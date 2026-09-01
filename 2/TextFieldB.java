public class TextFieldB extends TextField {
    public TextFieldB(String text) {
        super(text);
    }

    @Override
    public void display() {
        int width = text.length() + 2;
        System.out.println("+" + "-".repeat(width) + "+");
        System.out.println("| " + text + " |");
        System.out.println("+" + "-".repeat(width) + "+");
    }
}
