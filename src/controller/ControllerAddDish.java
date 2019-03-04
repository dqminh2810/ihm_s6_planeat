package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mocks.DishMocks;
import model.CourseType;
import model.Dish;
import model.Ingredient;
import view.ViewBase;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerAddDish extends Controller
{
    @FXML
    private Button returnButton;

    @FXML
    private TextField nameTextfield;

    @FXML
    private TextArea descriptionTextarea;

    @FXML
    private Text errorText;

    @FXML
    private Button saveButton;

    @FXML
    private ChoiceBox<CourseType> courseTypeSplitMenu;

    private ArrayList<Ingredient> ingredients;


    public ControllerAddDish(Stage stage, Controller previousController)
    {
        super(stage, previousController);
        //TODO : add View dish
    }

    private void saveDish()
    {
        errorText.setText("");

        String name = nameTextfield.getText();
        if (name.equals(""))
        {
            errorText.setText("Le champ Nom est obligatoire");
            return;
        }

        for (Dish d : DishMocks.dishes)
        {
            if(d.getName().equals(name))
            {
                errorText.setText("Il existe déjà un repas " + name);
                return;
            }
        }

        String description = descriptionTextarea.getText();

        CourseType courseType = courseTypeSplitMenu.getSelectionModel().getSelectedItem();

        Dish dish = new Dish(name, description, courseType, ingredients);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadCourseType();
        ingredients = new ArrayList<Ingredient>();

        clickOnReturnButton(returnButton);
        saveButton.setOnAction(event -> saveDish());
    }

    private void loadCourseType()
    {
        ObservableList<CourseType> list = FXCollections.observableArrayList();
        list.add(CourseType.STARTER);
        list.add(CourseType.MAIN_COURSE);
        list.add(CourseType.DESSERT);
        courseTypeSplitMenu.setItems(list);
        courseTypeSplitMenu.getSelectionModel().select(0);
    }
}