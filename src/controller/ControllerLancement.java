package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import view.ViewConnexion;
import view.ViewInscription;
import view.ViewLancement;

import java.io.IOException;

public class ControllerLancement {
    @FXML
    private Button connexionButton;
    @FXML
    private Button inscriptionButton;


    public void init(){
        connexionButton.setOnAction( event -> goToConnexionView());
        inscriptionButton.setOnAction( event -> goToConnexionView());    //add a listner to inscription button
    }

    public void goToConnexionView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            ControllerConnexion controller = new ControllerConnexion();
            loader.setController(controller);
            Parent root = loader.load(getClass().getResourceAsStream("../"+ViewConnexion.XML_FILE));
            root.getStylesheets().add(ViewConnexion.CSS);
            controller.init();

            Stage stage = (Stage) connexionButton.getScene().getWindow();
            stage.setScene(new Scene(root, ViewConnexion.WIDTH, ViewConnexion.HEIGHT));
            stage.setTitle(ViewConnexion.LABEL);
            stage.show();

        }catch (IOException io){
            io.printStackTrace();
        }

    }


}
