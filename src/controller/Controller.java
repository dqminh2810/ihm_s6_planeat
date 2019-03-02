package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.ViewBase;

import java.io.IOException;

public abstract class Controller implements Initializable {
    private Stage stage; //the state is the current window
    private Controller previousController;
    private ViewBase actualView;

    Controller(Stage stage, Controller previousController, ViewBase actualView){
        this.stage = stage;
        this.previousController = previousController;
        this.actualView = actualView;
    }

    abstract void init();

    public void setView(Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(controller);
            Parent root = loader.load(getClass().getResourceAsStream("../"+ controller.getActualView().getXmlFile()));
            root.getStylesheets().add(controller.getActualView().getCss());
            //TODO : mettez tous ce que vous avez dans init dans la méthode initialize()
            //controller.init();

            stage.setScene(new Scene(root, controller.getActualView().getWidth(), controller.getActualView().getHeight()));
            stage.setTitle(controller.getActualView().getLabel());
            stage.show();

        }catch (IOException io){
            io.printStackTrace();
        }
    }

    void clickOnReturnButton(Button button){
        button.setOnAction(event -> setView(previousController));
    }

    public Stage getStage() {
        return stage;
    }

    private ViewBase getActualView() {
        return actualView;
    }

    public Controller getPreviousController() {
        return previousController;
    }
}
