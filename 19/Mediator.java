import java.util.List;

public interface Mediator {
    void register(ChatController client);
    void unregister(ChatController client);
    void send(ChatController sender, String recipient, String text);
    List<String> getUsernames();
}
