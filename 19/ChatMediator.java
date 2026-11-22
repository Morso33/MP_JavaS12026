import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Concrete mediator: the only place that knows about all clients. */
public class ChatMediator implements Mediator {
    private final Map<String, ChatController> clients = new LinkedHashMap<>();

    @Override
    public void register(ChatController client) {
        clients.put(client.getUsername(), client);
        notifyUserListChanged();
    }

    @Override
    public void unregister(ChatController client) {
        clients.remove(client.getUsername());
        notifyUserListChanged();
    }

    @Override
    public void send(ChatController sender, String recipient, String text) {
        ChatController target = clients.get(recipient);
        if (target == null || target == sender) {
            sender.showError("Cannot send to '" + recipient + "'.");
            return;
        }
        sender.messageSent(recipient, text);
        target.messageReceived(sender.getUsername(), text);
    }

    @Override
    public List<String> getUsernames() {
        return new ArrayList<>(clients.keySet());
    }

    private void notifyUserListChanged() {
        for (ChatController c : clients.values()) {
            c.usersChanged();
        }
    }
}
