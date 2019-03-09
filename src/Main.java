import controller.*;
import javafx.application.Application;
import mocks.*;
import javafx.stage.Stage;
import model.User;
import view.ViewBase;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        FoodMocks.initMocks();
        DishMocks.initMocks();
        MealMocks.initMocks();
        MealDatedMocks.initMocks();
        UserMocks.initMocks();
        User.actualUser = UserMocks.users.get("todesco@gmail.com");

        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            ViewBase.isMaximized = newValue;
            primaryStage.setMaximized(ViewBase.isMaximized);
        });

        Controller controller = new ControllerAccueil(primaryStage, null);
        controller.setFirstView(controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
