public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getInstance();
        logger.setFileName("new_log.txt"); // Change file name

        logger.write("Simulation started");
        logger.write("Processing data...");

        Logger elsewhere = Logger.getInstance();
        elsewhere.write("Message from another part of the program");
        System.out.println("Same instance: " + (logger == elsewhere));

        logger.write("Simulation finished");
        logger.close();

        System.out.println("Log messages written to " + logger.getFileName());
    }
}
