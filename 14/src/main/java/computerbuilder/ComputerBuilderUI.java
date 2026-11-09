package computerbuilder;

import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Text-based user interface: the user picks a preset or chooses every component,
 * and the finished configuration is printed.
 */
public class ComputerBuilderUI {

    private final Scanner in;
    private final PrintStream out;

    public ComputerBuilderUI(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public static void main(String[] args) {
        new ComputerBuilderUI(new Scanner(System.in), System.out).run();
    }

    public void run() {
        out.println();
        out.println("=== Computer Builder ===");
        try {
            while (true) {
                out.println();
                out.println("What would you like to build?");
                out.println("  1) Gaming computer (preset)");
                out.println("  2) Office computer (preset)");
                out.println("  3) Custom computer (choose every part)");
                out.println("  0) Exit");
                int choice = readNumber("Your choice: ", 0, 3);
                if (choice == 0) {
                    out.println("Goodbye!");
                    return;
                }
                Computer computer = choice == 3 ? buildCustom() : buildPreset(choice == 1);
                out.println();
                out.println("Your computer is ready:");
                out.println(computer);
            }
        } catch (EndOfInputException e) {
            out.println();
            out.println("No more input. Goodbye!");
        }
    }

    private Computer buildPreset(boolean gaming) {
        out.println();
        out.println("Choose the component grade:");
        Grade[] grades = Grade.values();
        for (int i = 0; i < grades.length; i++) {
            out.printf("  %d) %s%n", i + 1, grades[i].getDisplayName());
        }
        Grade grade = grades[readNumber("Grade: ", 1, grades.length) - 1];

        ComputerBuilder builder = gaming ? new GamingComputerBuilder(grade) : new OfficeComputerBuilder(grade);
        ComputerDirector director = new ComputerDirector(builder);
        return readYesNo("Include the accessory bundle? (y/n): ")
                ? director.constructComputer()
                : director.constructBaseComputer();
    }

    private Computer buildCustom() {
        CustomComputerBuilder builder = new CustomComputerBuilder()
                .withProcessor(choose("Processor", ComponentCatalog.PROCESSORS))
                .withRam(choose("RAM (GB)", ComponentCatalog.RAM_SIZES_GB))
                .withHardDrive(choose("Hard drive", ComponentCatalog.HARD_DRIVES))
                .withGraphicsCard(choose("Graphics card", ComponentCatalog.GRAPHICS_CARDS))
                .withOperatingSystem(choose("Operating system", ComponentCatalog.OPERATING_SYSTEMS));

        for (String accessory : chooseAccessories()) {
            builder.withAccessory(accessory);
        }
        return new ComputerDirector(builder).constructComputer();
    }

    private <T> T choose(String title, List<T> options) {
        out.println();
        out.println(title + ":");
        for (int i = 0; i < options.size(); i++) {
            out.printf("  %d) %s%n", i + 1, options.get(i));
        }
        return options.get(readNumber(title + ": ", 1, options.size()) - 1);
    }

    private Set<String> chooseAccessories() {
        List<String> options = ComponentCatalog.ACCESSORIES;
        out.println();
        out.println("Accessories (optional):");
        for (int i = 0; i < options.size(); i++) {
            out.printf("  %d) %s%n", i + 1, options.get(i));
        }
        while (true) {
            out.print("Numbers separated by commas, or press Enter for none: ");
            String line = readLine().trim();
            Set<String> chosen = new LinkedHashSet<>();
            if (line.isEmpty()) {
                return chosen;
            }
            boolean valid = true;
            for (String part : line.split("[,\\s]+")) {
                try {
                    int n = Integer.parseInt(part);
                    if (n < 1 || n > options.size()) {
                        valid = false;
                        break;
                    }
                    chosen.add(options.get(n - 1));
                } catch (NumberFormatException e) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return chosen;
            }
            out.println("Please enter numbers between 1 and " + options.size() + ".");
        }
    }

    private int readNumber(String prompt, int min, int max) {
        while (true) {
            out.print(prompt);
            String line = readLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // fall through to the error message
            }
            out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    private boolean readYesNo(String prompt) {
        while (true) {
            out.print(prompt);
            String answer = readLine().trim().toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) {
                return true;
            }
            if (answer.equals("n") || answer.equals("no")) {
                return false;
            }
            out.println("Please answer y or n.");
        }
    }

    private String readLine() {
        if (!in.hasNextLine()) {
            throw new EndOfInputException();
        }
        return in.nextLine();
    }

    private static class EndOfInputException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
}
