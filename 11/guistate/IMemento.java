package memento.guistate;

import java.time.LocalDateTime;

public interface IMemento {
    // memento metadata methods

    // the time when the memento was created
    LocalDateTime getTimestamp();

    // human-readable summary of the saved state, e.g. for the history window
    String getDescription();
}
