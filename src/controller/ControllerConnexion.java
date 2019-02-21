package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.ViewBase;
import view.ViewLancement;

public class ControllerConnexion extends Controller {
    @FXML
    private Button connexionButton;
    @FXML
    private Button inscriptionButton;
    @FXML
    private Button returnButton;

    public ControllerConnexion(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage,previousController, actualView);
    }

    public void init(){
        clickOnReturnButton(returnButton);
    }
}
