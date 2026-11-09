package computerbuilder;

/**
 * Director: knows the order of the assembly steps, but not which parts are used.
 * The parts are decided by whichever {@link ComputerBuilder} it is given.
 */
public class ComputerDirector {

    private ComputerBuilder builder;

    public ComputerDirector(ComputerBuilder builder) {
        setBuilder(builder);
    }

    public void setBuilder(ComputerBuilder builder) {
        if (builder == null) {
            throw new IllegalArgumentException("Builder must not be null");
        }
        this.builder = builder;
    }

    /** Assembles a complete computer, including accessories. */
    public Computer constructComputer() {
        builder.reset();
        builder.buildProcessor();
        builder.buildRAM();
        builder.buildHardDrive();
        builder.buildGraphicsCard();
        builder.buildOperatingSystem();
        builder.buildAccessories();
        return builder.getComputer();
    }

    /** Assembles only the computer itself, without any accessories. */
    public Computer constructBaseComputer() {
        builder.reset();
        builder.buildProcessor();
        builder.buildRAM();
        builder.buildHardDrive();
        builder.buildGraphicsCard();
        builder.buildOperatingSystem();
        return builder.getComputer();
    }
}
