import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/** Pure UI: no knowledge of other clients or the mediator. */
public class ChatView {
    private final TextArea messages = new TextArea();
    private final TextField input = new TextField();
    private final ComboBox<String> recipient = new ComboBox<>();

    public ChatView(String username, ChatController controller, Stage stage) {
        messages.setEditable(false);
        messages.setWrapText(true);
        VBox.setVgrow(messages, Priority.ALWAYS);

        input.setPromptText("Type a message...");
        HBox.setHgrow(input, Priority.ALWAYS);
        recipient.setPromptText("Recipient");

        Button send = new Button("Send");
        send.setDefaultButton(true);
        Runnable fire = () -> controller.onSend(recipient.getValue(), input.getText());
        send.setOnAction(e -> fire.run());

        HBox bottom = new HBox(8, recipient, input, send);
        VBox root = new VBox(8, messages, bottom);
        root.setPadding(new Insets(10));

        stage.setTitle(username);
        stage.setScene(new Scene(root, 420, 360));
        stage.show();
    }

    public void appendMessage(String line) {
        messages.appendText(line + "\n");
    }

    public void clearInput() {
        input.clear();
    }

    public void setRecipients(List<String> names) {
        String selected = recipient.getValue();
        recipient.getItems().setAll(names);
        if (selected != null && names.contains(selected)) {
            recipient.setValue(selected);
        }
    }
}
