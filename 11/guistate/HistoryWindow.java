package memento.guistate;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistoryWindow {

    private Stage stage;

    public HistoryWindow(Controller controller) {
        Insets insets = new Insets(10, 10, 10, 10);

        // the ListView observes the controller's history list, so it updates automatically
        ListView<IMemento> listView = new ListView<>(controller.getHistory());
        listView.setPrefSize(380, 300);

        // restore the clicked state
        listView.setOnMouseClicked(event -> {
            IMemento selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.restoreFromHistory(selected);
                listView.getSelectionModel().clearSelection();
            }
        });

        Label label = new Label("Saved states (oldest first). Click a state to restore it.");
        label.setPadding(insets);

        VBox vBox = new VBox(label, listView);
        vBox.setPadding(insets);

        stage = new Stage();
        stage.setScene(new Scene(vBox));
        stage.setTitle("History");
    }

    public Stage getStage() {
        return stage;
    }

    public void show() {
        stage.show();
    }
}
