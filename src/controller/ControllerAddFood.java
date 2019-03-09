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
import mocks.FoodMocks;
import model.Food;
import model.FoodCategory;
import view.ViewAddFood;
import view.ViewBase;

import java.net.URL;
import java.util.ResourceBundle;

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
    private TextField fibresTextfield;
    @FXML
    private TextField calciumTextfield;


    @FXML
    private CheckBox containPeanutCheckbox;
    @FXML
    private CheckBox containGlutenCheckbox;

    @FXML
    private Text errorText;

    @FXML
    private Button saveButton;

    @FXML
    private ChoiceBox<FoodCategory> categorySplitmenu;

    public ControllerAddFood(Stage stage, Controller previousController)
    {
        super(stage, previousController);
        this.actualView = new ViewAddFood();
    }

    private void saveFood()
    {
        errorText.setText("");

        String name = nameTextfield.getText();
        if (name.equals(""))
        {
            errorText.setText("Le champ Nom est obligatoire");
            return;
        }

        FoodCategory foodCategory = categorySplitmenu.getSelectionModel().getSelectedItem();

        double energy;
        if(energyTextfield.getText().equals(""))
            energy = -1;
        else
        {
            try
            {
                energy = Double.valueOf(energyTextfield.getText());
            } catch (Exception e)
            {
                errorText.setText("La valeur donnée au champ <Énergie> doit être numérique");
                return;
            }
        }

        double fat;
        if(fatTextfield.getText().equals(""))
            fat = -1;
        else
        {
            try
            {
                fat = Double.valueOf(fatTextfield.getText());
            } catch (Exception e)
            {
                errorText.setText("La valeur donnée au champ <Matière grasses> doit être numérique");
                return;
            }
        }

        double acid;
        if(acidTextfield.getText().equals(""))
            acid = -1;
        else
        {
            try
            {
                acid = Double.valueOf(acidTextfield.getText());
            } catch (Exception e)
            {
                errorText.setText("La valeur donnée au champ <Acide gras saturés> doit être numérique");
                return;
            }
        }

        double carbohydrate;
        if(carbohydrateTextfield.getText().equals(""))
            carbohydrate = -1;
        else
        {
            try
            {
                carbohydrate = Double.valueOf(carbohydrateTextfield.getText());
            } catch (Exception e)
            {
                errorText.setText("La valeur donnée au champ <Glucides> doit être numérique");
                return;
            }
        }

        double sugar;
        if(sugarTextfield.getText().equals(""))
            sugar = -1;
        else
        {
            try
            {
                sugar = Double.valueOf(sugarTextfield.getText());
            } catch (Exception e)
            {
                errorText.setText("La valeur donnée au champ <Sucres> doit être numérique");
                return;
            }
        }

        double protein;
        if(proteinTextfield.getText().equals(""))
            protein = -1;
        else
        {
            try
            {
                protein = Double.valueOf(proteinTextfield.getText());
            } catch (Exception e)
            {
                errorText.setText("La valeur donnée au champ <Protéines> doit être numérique");
                return;
            }
        }

        double salt;
        if(saltTextfield.getText().equals(""))
            salt = -1;
        else
        {
            try
            {
                salt = Double.valueOf(saltTextfield.getText());
            } catch (Exception e)
            {
                errorText.setText("La valeur donnée au champ <Sel> doit être numérique");
                return;
            }
        }

        double fibres;
        if(fibresTextfield.getText().equals(""))
            fibres = -1;
        else
        {
            try
            {
                fibres = Double.valueOf(fibresTextfield.getText());
            } catch (Exception e)
            {
                errorText.setText("La valeur donnée au champ <Fibre> doit être numérique");
                return;
            }
        }

        double calcium;
        if(calciumTextfield.getText().equals(""))
            calcium = -1;
        else
        {
            try
            {
                calcium = Double.valueOf(calciumTextfield.getText());
            } catch (Exception e)
            {
                errorText.setText("La valeur donnée au champ <Calcium> doit être numérique");
                return;
            }
        }

        boolean containPeanut = containPeanutCheckbox.isSelected();
        boolean containGluten = containGlutenCheckbox.isSelected();

        Food food;

        //If an error occur during the save process
        try
        {
            food = new Food(name, foodCategory, energy, fat, acid, carbohydrate, sugar, protein, salt, fibres, calcium, containPeanut, containGluten);
        }
        catch (Exception e)
        {
            errorText.setText("Une erreur empêche la sauvegarde");
            return;
        }

        for (Food f : FoodMocks.foods)
        {
            if (f.getName().equals(name))
            {
                errorText.setText("Il existe déjà un aliment " + name);
                return;
            }
        }

        //If an error occur during the save process
        try
        {
            FoodMocks.foods.add(food);
        }
        catch (Exception e)
        {
            errorText.setText("Une erreur empêche la sauvegarde");
            return;
        }

        //If the save worked, leave the page
        setView(getPreviousController());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFoodCategory();
        clickOnReturnButton(returnButton);
        saveButton.setOnAction(event -> saveFood());
    }
}
