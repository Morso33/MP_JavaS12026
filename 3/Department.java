import java.util.*;

public class Department extends Component {

    private List<Component> children = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    @Override
    public double getTotalSalary() {
        double total = 0.0;
        for (Component child : this.children) {
            total += child.getTotalSalary();
        }
        return total;
    }

    @Override
    protected String toXml(int level) {
        StringBuilder builder = new StringBuilder();
        builder.append(indent(level))
               .append(String.format("<department name=\"%s\">", escape(this.name)));
        for (Component child : this.children) {
            builder.append(System.lineSeparator()).append(child.toXml(level + 1));
        }
        builder.append(System.lineSeparator())
               .append(indent(level))
               .append("</department>");
        return builder.toString();
    }

    @Override
    public void add(Component component) {
        this.children.add(component);
    }

    @Override
    public void remove(Component component) {
        if (this.children.remove(component)) {
            return;
        }
        for (Component child : this.children) {
            if (child instanceof Department) {
                child.remove(component);
            }
        }
    }

    @Override
    public Component getChild(int index) {
        return this.children.get(index);
    }
}
