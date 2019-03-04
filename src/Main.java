import controller.*;
import javafx.application.Application;
import mocks.*;
import javafx.stage.Stage;
import model.User;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        FoodMocks.initMocks();
        DishMocks.initMocks();
        MealMocks.initMocks();
        MealDatedMocks.initMocks();
        UserMocks.initMocks();
        User.actualUser = UserMocks.users.get("todesco@gmail.com");

        Controller controller = new ControllerAccueil(primaryStage, null);
        controller.setView(controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
