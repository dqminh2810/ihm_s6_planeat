package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import view.ViewBase;

public class ControllerPopupAjoutPlats extends Controller{
    @FXML
    private Button addButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteAllButton;
    @FXML
    private Button closeButton;

    public ControllerPopupAjoutPlats(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage, previousController, actualView);
    }

    public void init(){

    }
}
