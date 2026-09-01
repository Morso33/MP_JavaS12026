import java.util.Locale;

public class Employee extends Component {

    private double salary;

    public Employee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getTotalSalary() {
        return this.salary;
    }

    @Override
    protected String toXml(int level) {
        return String.format(Locale.ROOT, "%s<employee name=\"%s\" salary=\"%.2f\"/>",
                indent(level), escape(this.name), this.salary);
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("Not supported in leaf.");
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("Not supported in leaf.");
    }

    @Override
    public Component getChild(int index) {
        throw new UnsupportedOperationException("Not supported in leaf.");
    }
}
