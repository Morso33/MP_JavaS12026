import java.util.Locale;

public class Main {

    private static final long FIRST_PHASE_MILLIS = 15000;
    private static final long SECOND_PHASE_MILLIS = 15000;

    public static void main(String[] args) throws InterruptedException {

        WeatherStation station = new WeatherStation("Kaisaniemi");

        System.out.println("[´] Weather station started");
        System.out.printf(Locale.ROOT, "Initial temperature: %.1f C (limits %.1f C ... %.1f C)%n",
                station.getTemperature(), WeatherStation.MIN_TEMPERATURE, WeatherStation.MAX_TEMPERATURE);

        Observer phone = new PhoneDisplay("Tung Tung Sahur");
        Observer television = new TvWeatherChannel("MTV");
        Observer greenhouse = new GreenhouseController("Tomato Town", 5.0);
        TemperatureLogger logger = new TemperatureLogger();

        station.registerObserver(phone);
        station.registerObserver(television);
        station.registerObserver(greenhouse);
        station.registerObserver(logger);

        station.start();

        Thread.sleep(FIRST_PHASE_MILLIS);

        System.out.println();
        System.out.println("[*] Television off");
        station.removeObserver(television);

        Thread.sleep(SECOND_PHASE_MILLIS);

        station.stopStation();
        Thread.sleep(200);

        System.out.println();
        System.out.println("[+] Television off");
        System.out.printf(Locale.ROOT, "Last temperature: %.1f C%n", station.getTemperature());
        logger.printSummary();
    }
}
