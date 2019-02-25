package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.ViewBase;

public class ControllerAccueil extends Controller {

    @FXML
    private Button prevdayButton;
    @FXML
    private Text curdateText;
    @FXML
    private Button nextdayButton;
    @FXML
    private ListView curdayList;
    @FXML
    private Text curmealHourText;
    @FXML
    private Text curmealNameText;


    public ControllerAccueil(Stage stage, Controller previousController, ViewBase previousView) {
        super(stage,previousController, previousView);
    }

    public void init(){

    }

}
