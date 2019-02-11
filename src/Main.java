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
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        ControllerLancement controller = new ControllerLancement();
        loader.setController(controller);
        Parent root = loader.load(getClass().getResourceAsStream(ViewLancement.XML_FILE));
        root.getStylesheets().add(ViewInscription.CSS);
        controller.init();
        primaryStage.setScene(new Scene(root, ViewLancement.WIDTH, ViewLancement.HEIGHT));
        primaryStage.setTitle(ViewInscription.LABEL);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
