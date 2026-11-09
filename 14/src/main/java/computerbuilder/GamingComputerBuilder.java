package computerbuilder;

/** Builds gaming computers: strong CPU and GPU, lots of fast storage, gaming peripherals. */
public class GamingComputerBuilder implements ComputerBuilder {

    private final Grade grade;
    private Computer computer;

    public GamingComputerBuilder() {
        this(Grade.STANDARD);
    }

    public GamingComputerBuilder(Grade grade) {
        this.grade = grade;
        reset();
    }

    @Override
    public void reset() {
        computer = new Computer(grade.getDisplayName() + " Gaming Computer");
    }

    @Override
    public void buildProcessor() {
        switch (grade) {
            case BUDGET:
                computer.setProcessor("AMD Ryzen 5 7600");
                break;
            case PREMIUM:
                computer.setProcessor("AMD Ryzen 9 9950X3D");
                break;
            default:
                computer.setProcessor("AMD Ryzen 7 9800X3D");
        }
    }

    @Override
    public void buildRAM() {
        switch (grade) {
            case BUDGET:
                computer.setRamGb(16);
                break;
            case PREMIUM:
                computer.setRamGb(64);
                break;
            default:
                computer.setRamGb(32);
        }
    }

    @Override
    public void buildHardDrive() {
        switch (grade) {
            case BUDGET:
                computer.setHardDrive("1 TB NVMe SSD");
                break;
            case PREMIUM:
                computer.setHardDrive("4 TB NVMe SSD (PCIe 5.0)");
                break;
            default:
                computer.setHardDrive("2 TB NVMe SSD");
        }
    }

    @Override
    public void buildGraphicsCard() {
        switch (grade) {
            case BUDGET:
                computer.setGraphicsCard("NVIDIA GeForce RTX 5060");
                break;
            case PREMIUM:
                computer.setGraphicsCard("NVIDIA GeForce RTX 5090");
                break;
            default:
                computer.setGraphicsCard("NVIDIA GeForce RTX 5070 Ti");
        }
    }

    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem(grade == Grade.PREMIUM ? "Windows 11 Pro" : "Windows 11 Home");
    }

    @Override
    public void buildAccessories() {
        computer.addAccessory("RGB mechanical keyboard");
        computer.addAccessory("Gaming mouse");
        if (grade != Grade.BUDGET) {
            computer.addAccessory("Wireless gaming headset");
        }
        if (grade == Grade.PREMIUM) {
            computer.addAccessory("27\" 1440p 240 Hz OLED monitor");
        }
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
