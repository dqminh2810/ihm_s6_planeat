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
import model.*;
import model.CourseType;
import model.Dish;
import model.Ingredient;
import view.ViewDishManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerDishManager extends Controller
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
    private Button addDishButton;

    @FXML
    private ChoiceBox<CourseType> courseTypeSplitMenu;

    @FXML
    private ListView<Dish> dishList;

    private ObservableList<Dish> dishes;

    @FXML
    private ListView<Ingredient> ingredientList;

    private ObservableList<Ingredient> ingredients;

    private Dish selectedDish;


    public ControllerDishManager(Stage stage, Controller previousController)
    {
        super(stage, previousController);
        this.actualView = new ViewDishManager();
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
                errorText.setText("Il existe déjà un plat " + name);
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

    private void clickOnIngredientList(ListView<Ingredient> listView)
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

    private void clickOnDishList(ListView<Dish> listView)
    {
        listView.setOnMouseClicked(e ->
        {
            Dish selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null)
            {
                selectDish(selected);
            }
        });
    }

    private void selectedDish(Dish selected)
    {
        selectedDish = selected;
        nameTextfield.setText(selected.getName());
        descriptionTextarea.setText(selected.getDescription());
        courseTypeSplitMenu.setValue(selected.getCourseType());
        ingredients = FXCollections.observableArrayList(selected.getIngredients());
    }

    private void selectDish(Dish selected)
    {
        errorText.setText("");

        Dialog<Dish> dialog = new Dialog<>();
        dialog.setTitle("Gérer un plat");
        dialog.setHeaderText("Gérer un plat");
        dialog.setGraphic(null);

        ButtonType buttonOk = new ButtonType("Editer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonOk);

        ButtonType buttonDelete = new ButtonType("Supprimer", ButtonBar.ButtonData.OTHER);
        dialog.getDialogPane().getButtonTypes().add(buttonDelete);

        ButtonType buttonCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonCancel);

        Node buttonOkSecurity = dialog.getDialogPane().lookupButton(buttonOk);
        buttonOkSecurity.setDisable(false);

        dialog.setResultConverter(b ->
        {
            if (b == buttonOk)
            {
                return selected;
            }

            if (b == buttonDelete)
            {
                int i;
                for(i=0; (i<DishMocks.dishes.size())&& !(DishMocks.dishes.get(i).equals(selected)); i++);
                DishMocks.dishes.remove(i);
                dishes = FXCollections.observableArrayList(DishMocks.dishes);
            }
            return null;
        });

        Optional<Dish> result = dialog.showAndWait();
        if (result.isPresent())
        {
            selectedDish(result.get());
        }
    }

    private void updateIngredient(Ingredient selected)
    {
        errorText.setText("");

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

        ButtonType buttonOk = new ButtonType("Écraser", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonOk);

        ButtonType buttonDelete = new ButtonType("Supprimer", ButtonBar.ButtonData.OTHER);
        dialog.getDialogPane().getButtonTypes().add(buttonDelete);

        ButtonType buttonCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
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

            if (b == buttonDelete)
            {
                int index;
                for (index=0; index<ingredients.size() && !(ingredients.get(index).toString().equals(selected.toString())); index++);
                ingredients.remove(index);
            }

            return null;
        });

        Optional<Ingredient> result = dialog.showAndWait();
        if (result.isPresent())
        {
            for(int ingI=0; ingI<ingredients.size(); ingI++)
                if((ingredients.get(ingI).getFood().equals(result.get().getFood())) && (ingI != i))
                {
                    errorText.setText("Le plat contient déjà l'aliment " + result.get().getFood().getName());
                    return;
                }

            ingredients.remove(i);
            ingredients.add(i, result.get());
        }
    }

    private void addIngredient()
    {
        errorText.setText("");

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

        ButtonType buttonOk = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonOk);

        ButtonType buttonCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
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
        selectedDish = null;

        ingredients = FXCollections.observableArrayList();
        ingredientList.setItems(ingredients);
        clickOnIngredientList(ingredientList);

        dishes = FXCollections.observableArrayList(DishMocks.dishes);
        dishList.setItems(dishes);

        clickOnReturnButton(returnButton);
        addIngredientButton.setOnAction(event -> addIngredient());
        saveButton.setOnAction(event -> saveDish());
        addDishButton.setOnAction(event -> setView(new ControllerAddDish(getStage(), this)));
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