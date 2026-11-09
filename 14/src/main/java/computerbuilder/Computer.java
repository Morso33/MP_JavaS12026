package computerbuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The product: a fully assembled computer.
 * Instances are filled in step by step by a {@link ComputerBuilder}.
 */
public class Computer {

    private final String type;
    private String processor;
    private int ramGb;
    private String hardDrive;
    private String graphicsCard;
    private String operatingSystem;
    private final List<String> accessories = new ArrayList<>();

    public Computer(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getRamGb() {
        return ramGb;
    }

    public void setRamGb(int ramGb) {
        if (ramGb <= 0) {
            throw new IllegalArgumentException("RAM size must be positive");
        }
        this.ramGb = ramGb;
    }

    public String getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public List<String> getAccessories() {
        return Collections.unmodifiableList(accessories);
    }

    public void addAccessory(String accessory) {
        accessories.add(accessory);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String line = "+------------------------------------------------------------+";
        sb.append(line).append('\n');
        sb.append(String.format("| %-58s |%n", type));
        sb.append(line).append('\n');
        sb.append(row("Processor", processor));
        sb.append(row("RAM", ramGb > 0 ? ramGb + " GB" : null));
        sb.append(row("Hard drive", hardDrive));
        sb.append(row("Graphics card", graphicsCard));
        sb.append(row("Operating system", operatingSystem));
        if (accessories.isEmpty()) {
            sb.append(row("Accessories", "none"));
        } else {
            for (int i = 0; i < accessories.size(); i++) {
                sb.append(row(i == 0 ? "Accessories" : "", "- " + accessories.get(i)));
            }
        }
        sb.append(line);
        return sb.toString();
    }

    private static String row(String label, String value) {
        return String.format("| %-17s %-40s |%n", label, value == null ? "(not installed)" : value);
    }
}
