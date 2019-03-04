package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mocks.DishMocks;
import mocks.FoodMocks;
import mocks.MealMocks;
import model.*;
import view.ViewBase;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
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
    private Button addIngredientButton;

    @FXML
    private ChoiceBox<CourseType> courseTypeSplitMenu;

    @FXML
    private ListView<Ingredient> ingredientList;

    private ObservableList<Ingredient> ingredients;


    public ControllerAddDish(Stage stage, Controller previousController, ViewBase previousView)
    {
        super(stage, previousController, previousView);
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
            if (d.getName().equals(name))
            {
                errorText.setText("Il existe déjà un repas " + name);
                return;
            }
        }

        String description = descriptionTextarea.getText();

        CourseType courseType = courseTypeSplitMenu.getSelectionModel().getSelectedItem();

        Dish dish;

        //If an error occur during the save process
        try
        {
            dish = new Dish(name, description, courseType, new ArrayList<Ingredient>(ingredients));
            DishMocks.dishes.add(dish);
        } catch (Exception e)
        {
            errorText.setText("Une erreur empêche la sauvegarde");
            return;
        }

        //If the save worked, leave the page
        setView(getPreviousController());
    }

    private void clickOnListviewItem(ListView<Ingredient> listView)
    {
        listView.setOnMouseClicked(e ->
        {
            Ingredient selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null)
            {
                updateIngredient(selected);
            }
        });
    }

    private void updateIngredient(Ingredient selected)
    {
        Dialog<Ingredient> dialog = new Dialog<>();
        dialog.setTitle("Modifier ingrédient");
        dialog.setHeaderText("Modifier ingrédient");
        dialog.setGraphic(null);

        int i;
        for (i=0; i<ingredients.size() && !(ingredients.get(i).toString().equals(selected.toString())); i++);

        if (i >= ingredients.size())
        {
            errorText.setText("L'ingrédient à modifier n'est pas trouvé");
            return;
        }

        int j;
        for (j=0; j<FoodMocks.foods.size() && !(FoodMocks.foods.get(j).toString().equals(selected.getFood().toString())); j++);

        if (j >= FoodMocks.foods.size())
        {
            errorText.setText("L'aliment de l'ingrédient à modifier n'est pas trouvé");
            return;
        }

        Label middle = new Label(" - ");
        ChoiceBox<Food> foodChoiceBox = new ChoiceBox<>();
        ObservableList<Food> foodObservableList = FXCollections.observableArrayList();
        foodObservableList.setAll(FoodMocks.foods);
        foodChoiceBox.setItems(foodObservableList);
        foodChoiceBox.getSelectionModel().select(j);
        TextField quantity = new TextField();
        quantity.setPromptText("Quantité");
        quantity.setText(selected.getQuantity()+"");

        GridPane grid = new GridPane();
        grid.add(foodChoiceBox, 0, 0);
        grid.add(middle, 1, 0);
        grid.add(quantity, 2, 0);
        grid.add(new Label(" g"), 3, 0);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonOk);

        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonCancel);

        Node buttonOkSecurity = dialog.getDialogPane().lookupButton(buttonOk);

        quantity.textProperty().addListener((observable, oldValue, newValue) ->
        {
            int q;

            try
            {
                q = Integer.valueOf(newValue);
            } catch (Exception e)
            {
                buttonOkSecurity.setDisable(true);
                return;
            }

            if (q > 0)
                buttonOkSecurity.setDisable(false);
            else
                buttonOkSecurity.setDisable(true);
        });

        dialog.setResultConverter(b ->
        {
            if (b == buttonOk)
            {
                return new Ingredient(foodChoiceBox.getValue(), Integer.valueOf(quantity.getText()));
            }
            return null;
        });

        Optional<Ingredient> result = dialog.showAndWait();
        if (result.isPresent())
        {
            ingredients.remove(i);
            ingredients.add(i, result.get());
        }
    }

    private void addIngredient()
    {
        Dialog<Ingredient> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un ingrédient");
        dialog.setHeaderText("Ajouter un ingrédient");
        dialog.setGraphic(null);

        Label middle = new Label(" - ");
        ChoiceBox<Food> foodChoiceBox = new ChoiceBox<>();
        ObservableList<Food> foodObservableList = FXCollections.observableArrayList();
        foodObservableList.setAll(FoodMocks.foods);
        foodChoiceBox.setItems(foodObservableList);
        foodChoiceBox.getSelectionModel().select(0);
        TextField quantity = new TextField();
        quantity.setPromptText("Quantité");

        GridPane grid = new GridPane();
        grid.add(foodChoiceBox, 0, 0);
        grid.add(middle, 1, 0);
        grid.add(quantity, 2, 0);
        grid.add(new Label(" g"), 3, 0);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonOk);

        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonCancel);

        Node buttonOkSecurity = dialog.getDialogPane().lookupButton(buttonOk);
        buttonOkSecurity.setDisable(true);

        quantity.textProperty().addListener((observable, oldValue, newValue) ->
        {
            int q;

            try
            {
                q = Integer.valueOf(newValue);
            } catch (Exception e)
            {
                buttonOkSecurity.setDisable(true);
                return;
            }

            if (q > 0)
                buttonOkSecurity.setDisable(false);
            else
                buttonOkSecurity.setDisable(true);
        });

        dialog.setResultConverter(b ->
        {
            if (b == buttonOk)
            {
                return new Ingredient(foodChoiceBox.getValue(), Integer.valueOf(quantity.getText()));
            }
            return null;
        });

        Optional<Ingredient> result = dialog.showAndWait();
        if (result.isPresent())
        {
            for (Ingredient i : ingredients)
            {
                if (i.getFood().getName().equals(result.get().getFood().getName()))
                {
                    errorText.setText("Le plat contient déjà l'aliment " + result.get().getFood().getName());
                    return;
                }
            }

            ingredients.add(result.get());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadCourseType();
        ingredients = FXCollections.observableArrayList();
        ingredientList.setItems(ingredients);
        clickOnListviewItem(ingredientList);

        clickOnReturnButton(returnButton);
        addIngredientButton.setOnAction(event -> addIngredient());
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