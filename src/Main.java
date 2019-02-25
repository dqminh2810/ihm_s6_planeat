import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import mocks.FoodMocks;
import mocks.UserMocks;
import model.User;
import view.ViewInscription;
import javafx.stage.Stage;
import view.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        ViewBase view = new ViewGestionMenu();
        Controller controller = new ControllerGestionMenu(primaryStage, null, view);
        UserMocks.initMocks();
        FoodMocks.initMocks();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
