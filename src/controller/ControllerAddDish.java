package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.FoodCategory;
import view.ViewBase;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAddDish extends Controller
{
    @FXML
    private Button returnButton;

    @FXML
    private Text errorText;

    @FXML
    private Button addImageButton;
    @FXML
    private Button saveButton;

    public ControllerAddDish(Stage stage, Controller previousController, ViewBase previousView)
    {
        super(stage, previousController, previousView);
    }

    @Override
    void init()
    {
        clickOnReturnButton(returnButton);
        saveButton.setOnAction(event -> saveDish());
    }

    private void saveDish()
    {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}