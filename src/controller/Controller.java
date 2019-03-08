package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;
import view.ViewBase;

import java.io.IOException;

public abstract class Controller implements Initializable {
    private Stage stage; //the state is the current window
    private Controller previousController;
    protected ViewBase actualView;

    Controller(Stage stage, Controller previousController){
        this.stage = stage;
        this.previousController = previousController;
    }

    public void setView(Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(controller);
            Parent root = loader.load(getClass().getResourceAsStream("../"+ controller.getActualView().getXmlFile()));
            if(User.actualUser.isUseLightCss()){
                root.getStylesheets().add(controller.getActualView().getCssLight());
            }else{
                root.getStylesheets().add(controller.getActualView().getCss());
            }


            stage.setScene(new Scene(root, controller.getActualView().getWidth(), controller.getActualView().getHeight()));
            stage.setTitle(controller.getActualView().getLabel());
            stage.show();
            stage.setMaximized(ViewBase.isMaximized);
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
