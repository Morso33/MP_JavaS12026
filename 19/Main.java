import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Mediator mediator = new ChatMediator();
        String[] names = {"Alice", "Bob", "Carol"};
        for (int i = 0; i < names.length; i++) {
            Stage stage = (i == 0) ? primaryStage : new Stage();
            stage.setX(80 + i * 440);
            stage.setY(120);
            new ChatController(names[i], mediator, stage);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
