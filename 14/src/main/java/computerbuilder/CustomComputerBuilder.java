package computerbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a computer from components chosen by the user.
 * The choices are collected with the fluent {@code with...} methods first;
 * the director then calls the normal build steps to put them into the computer.
 */
public class CustomComputerBuilder implements ComputerBuilder {

    private String processor = ComponentCatalog.PROCESSORS.get(0);
    private int ramGb = ComponentCatalog.RAM_SIZES_GB.get(0);
    private String hardDrive = ComponentCatalog.HARD_DRIVES.get(0);
    private String graphicsCard = ComponentCatalog.GRAPHICS_CARDS.get(0);
    private String operatingSystem = ComponentCatalog.OPERATING_SYSTEMS.get(0);
    private final List<String> accessories = new ArrayList<>();

    private Computer computer;

    public CustomComputerBuilder() {
        reset();
    }

    public CustomComputerBuilder withProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public CustomComputerBuilder withRam(int ramGb) {
        this.ramGb = ramGb;
        return this;
    }

    public CustomComputerBuilder withHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
        return this;
    }

    public CustomComputerBuilder withGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
    }

    public CustomComputerBuilder withOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public CustomComputerBuilder withAccessory(String accessory) {
        this.accessories.add(accessory);
        return this;
    }

    @Override
    public void reset() {
        computer = new Computer("Custom Computer");
    }

    @Override
    public void buildProcessor() {
        computer.setProcessor(processor);
    }

    @Override
    public void buildRAM() {
        computer.setRamGb(ramGb);
    }

    @Override
    public void buildHardDrive() {
        computer.setHardDrive(hardDrive);
    }

    @Override
    public void buildGraphicsCard() {
        computer.setGraphicsCard(graphicsCard);
    }

    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem(operatingSystem);
    }

    @Override
    public void buildAccessories() {
        for (String accessory : accessories) {
            computer.addAccessory(accessory);
        }
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
