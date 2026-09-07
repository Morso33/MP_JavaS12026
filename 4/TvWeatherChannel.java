import java.util.Locale;

public class TvWeatherChannel implements Observer {

    private final String channel;

    public TvWeatherChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String getName() {
        return "TvWeatherChannel(" + this.channel + ")";
    }

    @Override
    public void update(double temperature) {
        System.out.printf(Locale.ROOT, " The weather is currently %.1f degrees.\"%n",
                temperature);
    }
}
