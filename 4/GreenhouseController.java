import java.util.Locale;

public class GreenhouseController implements Observer {

    private final String greenhouse;
    private final double heatingLimit;

    public GreenhouseController(String greenhouse, double heatingLimit) {
        this.greenhouse = greenhouse;
        this.heatingLimit = heatingLimit;
    }

    @Override
    public String getName() {
        return "GreenhouseController(" + this.greenhouse + ")";
    }

    @Override
    public void update(double temperature) {
        String action = temperature < this.heatingLimit ? "heating ON" : "heating OFF";
        System.out.printf(Locale.ROOT, "  Greenhouse %s at %.1f C -> %s.%n",
                this.greenhouse, temperature, action);
    }
}
