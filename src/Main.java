import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import view.ViewInscription;
import javafx.stage.Stage;
import view.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        ViewBase view = new ViewAddFood();
        Controller controller = new ControllerAddFood(primaryStage, null, view);
        controller.setView(controller);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
