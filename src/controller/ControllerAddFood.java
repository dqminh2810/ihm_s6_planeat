package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.FoodCategory;
import view.ViewBase;

public class ControllerAddFood extends Controller
{
    @FXML
    private Button returnButton;

    @FXML
    private TextField nameTextfield;
    @FXML
    private TextField energyTextfield;
    @FXML
    private TextField fatTextfield;
    @FXML
    private TextField acidTextfield;
    @FXML
    private TextField carbohydrateTextfield;
    @FXML
    private TextField sugarTextfield;
    @FXML
    private TextField proteinTextfield;
    @FXML
    private TextField saltTextfield;


    @FXML
    private CheckBox containPeanutCheckbox;
    @FXML
    private CheckBox containGlutenCheckbox;

    @FXML
    private Button addImageButton;
    @FXML
    private Button saveButton;

    @FXML
    private ChoiceBox<FoodCategory> categorySplitmenu;

    public ControllerAddFood(Stage stage, Controller previousController, ViewBase previousView)
    {
        super(stage, previousController, previousView);
    }

    @Override
    void init()
    {
        loadFoodCategory();
        clickOnReturnButton(returnButton);
        saveButton.setOnAction(event -> saveFood());
    }

    private void saveFood()
    {
        
    }

    private void loadFoodCategory()
    {
        ObservableList<FoodCategory> list = FXCollections.observableArrayList();
        list.add(FoodCategory.WHITE_MEAT);
        list.add(FoodCategory.RED_MEAT);
        list.add(FoodCategory.VEGETABLE);
        list.add(FoodCategory.FRUIT);
        list.add(FoodCategory.FISH);
        list.add(FoodCategory.ANIMAL_ORIGIN);
        list.add(FoodCategory.OTHER);
        categorySplitmenu.setItems(list);
        categorySplitmenu.getSelectionModel().select(0);
    }
}
