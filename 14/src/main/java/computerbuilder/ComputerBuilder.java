package computerbuilder;

/**
 * Builder interface: declares one step for each part of a {@link Computer}.
 * Concrete builders decide which actual components go into each step.
 */
public interface ComputerBuilder {

    /** Starts a fresh, empty computer so the builder can be reused. */
    void reset();

    void buildProcessor();

    void buildRAM();

    void buildHardDrive();

    void buildGraphicsCard();

    void buildOperatingSystem();

    /** Optional extras such as monitor, keyboard or headset. */
    void buildAccessories();

    /** Returns the computer assembled so far. */
    Computer getComputer();
}
