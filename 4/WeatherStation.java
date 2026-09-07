import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class WeatherStation implements Subject, Runnable {

    public static final double MIN_TEMPERATURE = -30.0;
    public static final double MAX_TEMPERATURE = 40.0;

    private static final int MIN_INTERVAL_MILLIS = 1000;
    private static final int MAX_INTERVAL_MILLIS = 5000;

    private final List<Observer> observers = new CopyOnWriteArrayList<>();
    private final Random random = new Random();
    private final String name;
    private final Thread thread;

    private volatile double temperature;

    public WeatherStation(String name) {
        this.name = name;
        this.temperature = MIN_TEMPERATURE
                + this.random.nextDouble() * (MAX_TEMPERATURE - MIN_TEMPERATURE);
        this.temperature = Math.round(this.temperature);
        this.thread = new Thread(this, "weather-station-" + name);
        this.thread.setDaemon(true);
    }

    public String getName() {
        return this.name;
    }

    public double getTemperature() {
        return this.temperature;
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer != null && !this.observers.contains(observer)) {
            this.observers.add(observer);
            System.out.printf(Locale.ROOT, "[%s] registered observer: %s%n", this.name, observer.getName());
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (this.observers.remove(observer)) {
            System.out.printf(Locale.ROOT, "[%s] removed observer: %s%n", this.name, observer.getName());
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this.temperature);
        }
    }

    public void start() {
        this.thread.start();
    }

    public void stopStation() {
        this.thread.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(this.nextInterval());
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                break;
            }
            this.updateTemperature();
            System.out.printf(Locale.ROOT, "%n[%s] temperature is now %.1f C%n", this.name, this.temperature);
            this.notifyObservers();
        }
        System.out.printf(Locale.ROOT, "%n[%s] station stopped.%n", this.name);
    }

    private int nextInterval() {
        return MIN_INTERVAL_MILLIS + this.random.nextInt(MAX_INTERVAL_MILLIS - MIN_INTERVAL_MILLIS + 1);
    }

    private void updateTemperature() {
        double change = this.random.nextBoolean() ? 1.0 : -1.0;
        double updated = this.temperature + change;
        if (updated > MAX_TEMPERATURE || updated < MIN_TEMPERATURE) {
            updated = this.temperature - change;
        }
        this.temperature = updated;
    }
}
