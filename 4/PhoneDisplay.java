import java.util.Locale;

public class PhoneDisplay implements Observer {

    private final String owner;

    public PhoneDisplay(String owner) {
        this.owner = owner;
    }

    @Override
    public String getName() {
        return "PhoneDisplay(" + this.owner + ")";
    }

    @Override
    public void update(double temperature) {
        System.out.printf(Locale.ROOT, "[Phone] %s: it is %.1f C outside right now.%n",
                this.owner, temperature);
    }
}
