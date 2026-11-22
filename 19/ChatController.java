import javafx.stage.Stage;

import java.util.List;

/** Colleague: owns one ChatView and talks only to the mediator. */
public class ChatController {
    private final String username;
    private final Mediator mediator;
    private final ChatView view;

    public ChatController(String username, Mediator mediator, Stage stage) {
        this.username = username;
        this.mediator = mediator;
        this.view = new ChatView(username, this, stage);
        mediator.register(this);
        stage.setOnCloseRequest(e -> mediator.unregister(this));
    }

    public String getUsername() {
        return username;
    }

    // --- from the view ---
    public void onSend(String recipient, String text) {
        if (recipient == null || text == null || text.isBlank()) {
            return;
        }
        mediator.send(this, recipient, text.trim());
    }

    // --- from the mediator ---
    public void messageSent(String recipient, String text) {
        view.appendMessage("To " + recipient + ": " + text);
        view.clearInput();
    }

    public void messageReceived(String sender, String text) {
        view.appendMessage("From " + sender + ": " + text);
    }

    public void showError(String msg) {
        view.appendMessage("[!] " + msg);
    }

    public void usersChanged() {
        List<String> others = mediator.getUsernames();
        others.remove(username);
        view.setRecipients(others);
    }
}
