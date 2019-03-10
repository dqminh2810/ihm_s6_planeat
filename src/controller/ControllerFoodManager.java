package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mocks.FoodMocks;
import model.Food;
import model.FoodCategory;
import view.ViewAddFood;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFoodManager extends Controller {
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
    @FXML
    private ListView<Food> foodListview;
    @FXML
    private Button deleteButton;
    @FXML
    private Button getAddBackButton;
    private ObservableList<Food> foods;
    boolean addMode = true;
    private Food foodToEdit;


    ControllerFoodManager(Stage stage, Controller previousController) {
        super(stage, previousController);
        this.actualView = new ViewAddFood();
    }

    private void saveFood() {
        errorText.setText("");

        if(!isNameOk())
            return;

        FoodCategory foodCategory = categorySplitmenu.getSelectionModel().getSelectedItem();

        double energy;
        if(!isDoubleValueOk(energyTextfield, "energy"))
            return;
        else
            energy = getDoubleValue(energyTextfield);

        double fat;
        if(!isDoubleValueOk(fatTextfield, "fat"))
            return;
        else
            fat = getDoubleValue(fatTextfield);

        double acid;
        if(!isDoubleValueOk(acidTextfield, "acid"))
            return;
        else
            acid = getDoubleValue(acidTextfield);

        double carbohydrate;
        if(!isDoubleValueOk(carbohydrateTextfield, "carbohydrate"))
            return;
        else
            carbohydrate = getDoubleValue(carbohydrateTextfield);

        double sugar;
        if(!isDoubleValueOk(sugarTextfield, "sugar"))
            return;
        else
            sugar = getDoubleValue(sugarTextfield);

        double protein;
        if(!isDoubleValueOk(proteinTextfield, "protein"))
            return;
        else
            protein = getDoubleValue(proteinTextfield);

        double salt;
        if(!isDoubleValueOk(saltTextfield, "salt"))
            return;
        else
            salt = getDoubleValue(saltTextfield);

        double fibres;
        if(!isDoubleValueOk(fibresTextfield, "fibre"))
            return;
        else
            fibres = getDoubleValue(fibresTextfield);

        double calcium;
        if(!isDoubleValueOk(calciumTextfield, "calcium"))
            return;
        else
            calcium = getDoubleValue(calciumTextfield);

        boolean containPeanut = containPeanutCheckbox.isSelected();
        boolean containGluten = containGlutenCheckbox.isSelected();

        if(!checkIfFoodHasSameName())
            return;

        System.out.println("Send");

        if(addMode) {
            Food create = new Food(nameTextfield.getText(), foodCategory, energy, fat, acid, carbohydrate, sugar, protein, salt, fibres, calcium, containPeanut, containGluten);
            FoodMocks.foods.add(create);
            foods.add(create);
            emptyFields();
        }else{
            foodToEdit.setName(nameTextfield.getText());
            foodToEdit.setCategory(foodCategory);
            foodToEdit.setEnergy(energy);
            foodToEdit.setFat(fat);
            foodToEdit.setAcid(acid);
            foodToEdit.setCarbohydrate(carbohydrate);
            foodToEdit.setProtein(protein);
            foodToEdit.setSalt(salt);
            foodToEdit.setFibres(fibres);
            foodToEdit.setCalcium(calcium);
            foodToEdit.setPeanut(containPeanut);
            foodToEdit.setGluten(containGluten);
            setAddMode();
            foodListview.refresh();
        }
    }

    private void setAddMode(){
        addMode = true;
        deleteButton.setDisable(true);
        getAddBackButton.setDisable(true);
        saveButton.setText("Ajouter l'aliment");
        foodToEdit = null;
        emptyFields();
    }

    private void setEditMode(){
        addMode = false;
        deleteButton.setDisable(false);
        getAddBackButton.setDisable(false);
        saveButton.setText("Editer l'aliment");
        fillFields();
    }

    private void emptyFields(){
        nameTextfield.setText("");
        energyTextfield.setText("");
        fatTextfield.setText("");
        acidTextfield.setText("");
        carbohydrateTextfield.setText("");
        sugarTextfield.setText("");
        proteinTextfield.setText("");
        saltTextfield.setText("");
        fibresTextfield.setText("");
        calciumTextfield.setText("");
        containGlutenCheckbox.setSelected(false);
        containPeanutCheckbox.setSelected(false);
        categorySplitmenu.getSelectionModel().select(0);
    }

    private boolean isNameOk(){
        if (nameTextfield.getText().length() == 0) {
            errorText.setText("Le champ nom est obligatoire");
            return false;
        }
        return true;
    }

    private boolean isDoubleValueOk(TextField value, String champ){
        if(value.getText().equals(""))
            return true;
        else {
            try {
                Double.valueOf(value.getText());
            } catch (Exception e) {
                errorText.setText("La valeur donnée au champ <"+champ+"> doit être numérique");
                return false;
            }
        }
        return true;
    }

    private double getDoubleValue(TextField value){
        if(value.getText().equals(""))
            return -1;
        else
            return Double.valueOf(value.getText());
    }

    private boolean checkIfFoodHasSameName(){
        if(addMode || (!addMode && !foodToEdit.getName().equals(nameTextfield.getText()))) {
            for (Food f : FoodMocks.foods) {
                if (f.getName().equals(nameTextfield.getText())) {
                    errorText.setText("Il existe déjà un aliment " + nameTextfield.getText());
                    return false;
                }
            }
        }
        return true;
    }

    private void loadFoodCategory() {
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
        loadFoodInListview();
        clickOnReturnButton(returnButton);
        saveButton.setOnAction(event -> saveFood());

        foodListview.setOnMouseClicked(e -> {
            foodToEdit = foodListview.getSelectionModel().getSelectedItem();
            if(foodToEdit != null){
                setEditMode();
            }
        });

        deleteButton.setOnAction(event -> {
            foods.remove(foodToEdit);
            FoodMocks.foods.remove(foodToEdit);
            setAddMode();
        });

        getAddBackButton.setOnAction(event -> setAddMode());
    }

    private void fillFields(){
        nameTextfield.setText(foodToEdit.getName());
        if(foodToEdit.getEnergy() != -1)
            energyTextfield.setText(Double.toString(foodToEdit.getEnergy()));
        if(foodToEdit.getFat() != -1)
            fatTextfield.setText(Double.toString(foodToEdit.getFat()));
        if(foodToEdit.getAcid() != -1)
            acidTextfield.setText(Double.toString(foodToEdit.getAcid()));
        if(foodToEdit.getCarbohydrate() != -1)
            carbohydrateTextfield.setText(Double.toString(foodToEdit.getCarbohydrate()));
        if(foodToEdit.getSugar() != -1)
            sugarTextfield.setText(Double.toString(foodToEdit.getSugar()));
        if(foodToEdit.getProtein() != -1)
            proteinTextfield.setText(Double.toString(foodToEdit.getProtein()));
        if(foodToEdit.getSalt() != -1)
            saltTextfield.setText(Double.toString(foodToEdit.getSalt()));
        if(foodToEdit.getFibres() != -1)
            fibresTextfield.setText(Double.toString(foodToEdit.getFibres()));
        if(foodToEdit.getCalcium() != -1)
            calciumTextfield.setText(Double.toString(foodToEdit.getCalcium()));
        containGlutenCheckbox.setSelected(foodToEdit.isGluten());
        containPeanutCheckbox.setSelected(foodToEdit.isPeanut());
        categorySplitmenu.getSelectionModel().select(foodToEdit.getCategory());
    }

    private void loadFoodInListview(){
        foods = FXCollections.observableArrayList();
        foods.addAll(FoodMocks.foods);
        foodListview.setItems(foods);
    }
}
