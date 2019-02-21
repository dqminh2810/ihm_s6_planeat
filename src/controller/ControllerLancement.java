package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import view.ViewBase;
import view.ViewConnexion;
import view.ViewInscription;

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

    /*
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
    */
}
