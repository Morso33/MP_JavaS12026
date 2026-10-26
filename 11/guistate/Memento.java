package memento.guistate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Memento implements IMemento {
    private static final String[] COLOR_NAMES = {"Red", "Blue", "Yellow"};
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private int[] options;
    private boolean isSelected;
    private LocalDateTime timestamp;

    public Memento(int[] options, boolean isSelected) {
        this.options = options.clone(); // Copy options array
        this.isSelected = isSelected;
        this.timestamp = LocalDateTime.now();
        System.out.println("Memento created");
    }

    public int[] getOptions() {
        return options.clone(); // Copy so the saved state cannot be modified
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String getDescription() {
        return String.format("%s  |  %s, %s, %s  |  checkbox: %s",
                timestamp.format(TIME_FORMAT),
                COLOR_NAMES[options[0]], COLOR_NAMES[options[1]], COLOR_NAMES[options[2]],
                isSelected ? "on" : "off");
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
