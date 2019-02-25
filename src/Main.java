import controller.*;
import javafx.application.Application;
import mocks.FoodMocks;
import mocks.UserMocks;
import javafx.stage.Stage;
import model.User;
import view.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        FoodMocks.initMocks();
        UserMocks.initMocks();
        User.actualUser = UserMocks.users.get("todesco@gmail.com");

        ViewBase view = new ViewAccueil();
        Controller controller = new ControllerAccueil(primaryStage, null, view);
        controller.setView(controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
