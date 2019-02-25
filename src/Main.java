import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import mocks.UserMocks;
import model.User;
import view.ViewInscription;
import javafx.stage.Stage;
import view.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        UserMocks.initMocks();

        ViewBase view = new ViewLancement();
        Controller controller = new ControllerLancement(primaryStage, null, view);
        controller.setView(controller);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
