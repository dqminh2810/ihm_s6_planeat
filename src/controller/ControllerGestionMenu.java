package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import view.ViewBase;


public class ControllerGestionMenu extends Controller{
    @FXML
    private Button returnButton;
    @FXML
    private Button addButton;
    @FXML
    private Button agendaButton;

    public ControllerGestionMenu(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage, previousController, actualView);
    }

    public void init(){

    }

}
