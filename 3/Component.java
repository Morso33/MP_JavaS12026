import java.util.Locale;

public abstract class Component {

    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract double getTotalSalary();

    public void printTotalSalary() {
        System.out.printf(Locale.ROOT, "Total salary of %s: %.2f%n", this.name, this.getTotalSalary());
    }

    public void printXml() {
        System.out.println(this.toXml(0));
    }

    protected abstract String toXml(int level);

    public abstract void add(Component component);

    public abstract void remove(Component component);

    public abstract Component getChild(int index);

    protected static String indent(int level) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            builder.append("  ");
        }
        return builder.toString();
    }

    protected static String escape(String text) {
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;");
    }
}
