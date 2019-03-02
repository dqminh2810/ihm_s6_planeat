package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import view.ViewBase;
import view.ViewConnexion;
import view.ViewInscription;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLancement extends Controller {
    @FXML
    private Button connexionButton;
    @FXML
    private Button inscriptionButton;

    public ControllerLancement(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage,previousController, actualView);
    }

    public void init(){
        connexionButton.setOnAction( event -> setView(new ControllerConnexion(getStage(), this, new ViewConnexion())));
        inscriptionButton.setOnAction( event -> setView(new ControllerInscription(getStage(), this, new ViewInscription())));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
