package computerbuilder;

/** Builds office computers: reliable, quiet and affordable, with productivity peripherals. */
public class OfficeComputerBuilder implements ComputerBuilder {

    private final Grade grade;
    private Computer computer;

    public OfficeComputerBuilder() {
        this(Grade.STANDARD);
    }

    public OfficeComputerBuilder(Grade grade) {
        this.grade = grade;
        reset();
    }

    @Override
    public void reset() {
        computer = new Computer(grade.getDisplayName() + " Office Computer");
    }

    @Override
    public void buildProcessor() {
        switch (grade) {
            case BUDGET:
                computer.setProcessor("Intel Core i3-14100");
                break;
            case PREMIUM:
                computer.setProcessor("Intel Core i7-14700");
                break;
            default:
                computer.setProcessor("Intel Core i5-14500");
        }
    }

    @Override
    public void buildRAM() {
        switch (grade) {
            case BUDGET:
                computer.setRamGb(8);
                break;
            case PREMIUM:
                computer.setRamGb(32);
                break;
            default:
                computer.setRamGb(16);
        }
    }

    @Override
    public void buildHardDrive() {
        switch (grade) {
            case BUDGET:
                computer.setHardDrive("256 GB SSD");
                break;
            case PREMIUM:
                computer.setHardDrive("1 TB SSD");
                break;
            default:
                computer.setHardDrive("512 GB SSD");
        }
    }

    @Override
    public void buildGraphicsCard() {
        if (grade == Grade.PREMIUM) {
            computer.setGraphicsCard("NVIDIA T400 (multi-monitor)");
        } else {
            computer.setGraphicsCard("Intel UHD Graphics 730 (integrated)");
        }
    }

    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem("Windows 11 Pro");
    }

    @Override
    public void buildAccessories() {
        computer.addAccessory("Keyboard and mouse set");
        if (grade == Grade.BUDGET) {
            computer.addAccessory("22\" 1080p monitor");
        } else if (grade == Grade.STANDARD) {
            computer.addAccessory("24\" 1080p monitor");
            computer.addAccessory("HD webcam");
        } else {
            computer.addAccessory("2 x 27\" 1440p monitors");
            computer.addAccessory("HD webcam");
            computer.addAccessory("USB-C docking station");
        }
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
