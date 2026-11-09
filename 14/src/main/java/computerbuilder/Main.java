package computerbuilder;

import java.util.Scanner;

/**
 * Demonstrates the Builder pattern: the same director assembles different
 * computers depending on which builder it is given.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Builder pattern demo ===\n");

        // 1. One director, two different builders -> two different products
        ComputerDirector director = new ComputerDirector(new GamingComputerBuilder());
        Computer gamingPc = director.constructComputer();
        print("Gaming computer built with GamingComputerBuilder:", gamingPc);

        director.setBuilder(new OfficeComputerBuilder());
        Computer officePc = director.constructComputer();
        print("Office computer built with OfficeComputerBuilder:", officePc);

        // 2. Component grades: same builder type, different quality of parts
        director.setBuilder(new GamingComputerBuilder(Grade.PREMIUM));
        print("Premium-grade gaming computer:", director.constructComputer());

        director.setBuilder(new OfficeComputerBuilder(Grade.BUDGET));
        print("Budget-grade office computer:", director.constructComputer());

        // 3. A different construction process: the base machine without accessories
        director.setBuilder(new GamingComputerBuilder(Grade.BUDGET));
        print("Budget gaming computer without accessories (constructBaseComputer):",
                director.constructBaseComputer());

        // 4. Fully customised computer with user-selected parts and accessories
        CustomComputerBuilder custom = new CustomComputerBuilder()
                .withProcessor("AMD Ryzen 7 9800X3D")
                .withRam(32)
                .withHardDrive("1 TB SSD + 4 TB HDD")
                .withGraphicsCard("AMD Radeon RX 9070 XT")
                .withOperatingSystem("Ubuntu 24.04 LTS")
                .withAccessory("Wi-Fi 7 card")
                .withAccessory("27\" 1440p 240 Hz OLED monitor");
        director.setBuilder(custom);
        print("Custom computer built with CustomComputerBuilder:", director.constructComputer());

        // 5. Interactive part
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to design your own computer? (y/n): ");
        if (scanner.hasNextLine() && scanner.nextLine().trim().toLowerCase().startsWith("y")) {
            new ComputerBuilderUI(scanner, System.out).run();
        } else {
            System.out.println("\nDone.");
        }
    }

    private static void print(String title, Computer computer) {
        System.out.println(title);
        System.out.println(computer);
        System.out.println();
    }
}
