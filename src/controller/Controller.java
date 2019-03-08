package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Theme;
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

    private Parent getRoot(Controller controller){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setController(controller);
            Parent root = loader.load(getClass().getResourceAsStream("../"+ controller.getActualView().getXmlFile()));
            root.getStylesheets().add(controller.getActualView().getCssBase());
            if(User.actualUser.getThemeCss().equals(Theme.LIGHT)){
                root.getStylesheets().add(controller.getActualView().getCssLight());
            }else{
                root.getStylesheets().add(controller.getActualView().getCssDark());
            }
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setFirstView(Controller controller){
        Parent root = getRoot(controller);
        if(root != null){
            stage.setScene(new Scene(root, controller.getActualView().getWidth(), controller.getActualView().getHeight()));
            stage.setTitle(controller.getActualView().getLabel());
            stage.show();
            stage.setMaximized(ViewBase.isMaximized);
        }
    }

    public void setView(Controller controller) {
        stage.getScene().setRoot(getRoot(controller));
        stage.setTitle(controller.getActualView().getLabel());
        stage.show();
        stage.setMaximized(ViewBase.isMaximized);
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
