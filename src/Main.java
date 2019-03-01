import controller.*;
import javafx.application.Application;
import mocks.FoodMocks;
import mocks.MealDatedMocks;
import mocks.MealMocks;
import mocks.UserMocks;
import javafx.stage.Stage;
import model.User;
import view.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        FoodMocks.initMocks();
        MealMocks.initMocks();
        MealDatedMocks.initMocks();
        UserMocks.initMocks();
        User.actualUser = UserMocks.users.get("todesco@gmail.com");

        ViewBase view = new ViewAgenda();
        Controller controller = new ControllerAgenda(primaryStage, null, view);
        controller.setView(controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
