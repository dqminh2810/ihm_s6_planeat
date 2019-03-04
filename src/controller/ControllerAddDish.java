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
    private Button saveButton;

    public ControllerAddDish(Stage stage, Controller previousController)
    {
        super(stage, previousController);
        //TODO : add View dish
    }

    private void saveDish()
    {
        errorText.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        clickOnReturnButton(returnButton);
        saveButton.setOnAction(event -> saveDish());
    }
}