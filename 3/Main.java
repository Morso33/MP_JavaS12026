
public class Main {

    public static void main(String[] args) {

        Component organization = new Department("Bob Johnson's Donalds");

        Component engineering = new Department("Engineering");
        Component software = new Department("Software Development");
        Component hardware = new Department("Hardware Development");
        Component sales = new Department("Sales");

        Component ceo = new Employee("Bob Johnson", 12000.00);
        Component cto = new Employee("Bob Donald's", 9500.00);
        Component developer1 = new Employee("Chen Johnson", 5200.00);
        Component developer2 = new Employee("David Johnson", 4800.00);
        Component engineer = new Employee("Eve Johnson", 5100.00);
        Component salesperson = new Employee("Frank Johnson", 4300.00);
        Component intern = new Employee("Grace Johnson", 1800.00);

        organization.add(ceo);

        engineering.add(cto);
        software.add(developer1);
        software.add(developer2);
        software.add(intern);
        hardware.add(engineer);
        engineering.add(software);
        engineering.add(hardware);

        sales.add(salesperson);

        organization.add(engineering);
        organization.add(sales);

        System.out.println("=== Organization structure as XML ===");
        organization.printXml();

        System.out.println();
        System.out.println("=== Total salaries ===");
        organization.printTotalSalary();
        engineering.printTotalSalary();
        software.printTotalSalary();

        System.out.println();
        System.out.println("=== Adding a marketing department with two employees ===");
        Component marketing = new Department("Marketing");
        marketing.add(new Employee("Hugo Halme", 4600.00));
        marketing.add(new Employee("Iris Ilves", 4100.00));
        organization.add(marketing);
        organization.printXml();
        organization.printTotalSalary();

        System.out.println();
        System.out.println("=== Removing the intern and the hardware department ===");
        organization.remove(intern);
        organization.remove(hardware);
        organization.printXml();
        organization.printTotalSalary();

        System.out.println();
        System.out.println("=== An employee is a leaf ===");
        try {
            ceo.add(new Employee("Jack Jokinen", 3000.00));
        } catch (UnsupportedOperationException exception) {
            System.out.println("Cannot add to an employee: " + exception.getMessage());
        }
    }
}
