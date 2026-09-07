import java.util.Locale;

public class TemperatureLogger implements Observer {

    private int readings = 0;
    private double lowest = Double.MAX_VALUE;
    private double highest = -Double.MAX_VALUE;

    @Override
    public String getName() {
        return "TemperatureLogger";
    }

    @Override
    public synchronized void update(double temperature) {
        this.readings++;
        this.lowest = Math.min(this.lowest, temperature);
        this.highest = Math.max(this.highest, temperature);
        System.out.printf(Locale.ROOT, "  Logger wrote reading number %d: %.1f C.%n",
                this.readings, temperature);
    }

    public synchronized void printSummary() {
        if (this.readings == 0) {
            System.out.println("Logger has no readings.");
            return;
        }
        System.out.printf(Locale.ROOT, "Logger summary: %d readings, lowest %.1f C, highest %.1f C.%n",
                this.readings, this.lowest, this.highest);
    }
}
