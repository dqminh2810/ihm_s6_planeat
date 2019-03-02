package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mocks.MealMocks;
import model.*;
import view.ViewBase;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControllerAgenda extends Controller {
    @FXML
    private Button returnButton;
    @FXML
    private Button previousWeekButton;
    @FXML
    private Button nextWeekButton;
    @FXML
    private Button shareButton;
    @FXML
    private Text currentDateText;
    @FXML
    private ListView<MealDated> monday_list;
    @FXML
    private ListView<MealDated> tuesday_list;
    @FXML
    private ListView<MealDated> wednesday_list;
    @FXML
    private ListView<MealDated> thursday_list;
    @FXML
    private ListView<MealDated> friday_list;
    @FXML
    private ListView<MealDated> saturday_list;
    @FXML
    private ListView<MealDated> sunday_list;
    @FXML
    private Button addMeal1Button;
    @FXML
    private Button addMeal2Button;
    @FXML
    private Button addMeal3Button;
    @FXML
    private Button addMeal4Button;
    @FXML
    private Button addMeal5Button;
    @FXML
    private Button addMeal6Button;
    @FXML
    private Button addMeal7Button;

    //this list contain the seven days of week meals. Monday is at 0 index
    private ArrayList<ObservableList<MealDated>> mealsDaysList;
    private LocalDate firstDayOfActualWeek;

    public ControllerAgenda(Stage stage, Controller previousController, ViewBase previousView) {
        super(stage,previousController, previousView);
    }

    public void init(){
        firstDayOfActualWeek = LocalDate.now().with(DayOfWeek.MONDAY); //to get monday
        mealsDaysList =  new ArrayList<>();

        shareButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Partager un menu");
            alert.setHeaderText("Partagez votre semaine de repas");
            alert.setContentText("Le repas est partagé");
            alert.showAndWait();
        });

        setDateText();
        clickOnReturnButton(returnButton);
        clickOnPreviousWeek();
        clickOnNextWeek();

        for(int i=0;i<7;i++){
            ObservableList<MealDated> mealsOfDay = FXCollections.observableArrayList();
            mealsDaysList.add(mealsOfDay);
            switch (i){
                case 0:
                    monday_list.setItems(mealsOfDay);
                    break;
                case 1:
                    tuesday_list.setItems(mealsOfDay);
                    break;
                case 2:
                    wednesday_list.setItems(mealsOfDay);
                    break;
                case 3:
                    thursday_list.setItems(mealsOfDay);
                    break;
                case 4:
                    friday_list.setItems(mealsOfDay);
                    break;
                case 5:
                    saturday_list.setItems(mealsOfDay);
                    break;
                case 6:
                    sunday_list.setItems(mealsOfDay);
                    break;
            }
        }

        addMeal1Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.MONDAY));
        addMeal2Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.TUESDAY));
        addMeal3Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.WEDNESDAY));
        addMeal4Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.THURSDAY));
        addMeal5Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.FRIDAY));
        addMeal6Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.SATURDAY));
        addMeal7Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.SUNDAY));
        clickOnListviewItem(monday_list, DayOfWeek.MONDAY);
        clickOnListviewItem(tuesday_list, DayOfWeek.TUESDAY);
        clickOnListviewItem(wednesday_list, DayOfWeek.WEDNESDAY);
        clickOnListviewItem(thursday_list, DayOfWeek.THURSDAY);
        clickOnListviewItem(friday_list, DayOfWeek.FRIDAY);
        clickOnListviewItem(saturday_list, DayOfWeek.SATURDAY);
        clickOnListviewItem(sunday_list, DayOfWeek.SUNDAY);
        loadLists();
    }

    private void popupToAddMealDate(DayOfWeek dayOfWeek){
        Dialog<MealDated> dialog = new Dialog<>();
        dialog.setTitle("Selectionner un repas");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);

        Label meal = new Label("Repas : ");
        Label middle = new Label(" : ");
        ChoiceBox<Meal> meals = new ChoiceBox<>();
        ObservableList<Meal> mealsList = FXCollections.observableArrayList();
        mealsList.setAll(MealMocks.meals);
        meals.setItems(mealsList);
        meals.getSelectionModel().select(0);
        TextField hours = new TextField();
        hours.setPromptText("heure");
        TextField minutes = new TextField();
        minutes.setPromptText("minutes");

        GridPane grid = new GridPane();
        grid.add(meal, 0, 0);
        grid.add(meals, 1, 0, 2, 1);
        grid.setVgap(20);
        grid.add(hours, 0, 1);
        grid.add(middle, 1, 1);
        grid.add(minutes, 2, 1);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonOk);

        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonCancel);

        Node buttonOkSecurity = dialog.getDialogPane().lookupButton(buttonOk);
        buttonOkSecurity.setDisable(true);

        hours.textProperty().addListener((observable, oldValue, newValue) -> {
            if(checkIfHourIsOk(newValue) && checkIfMinuteIsOk(minutes.getText()))
                buttonOkSecurity.setDisable(false);
            else
                buttonOkSecurity.setDisable(true);
        });

        minutes.textProperty().addListener((observable, oldValue, newValue) -> {
            if(checkIfMinuteIsOk(newValue) && checkIfHourIsOk(hours.getText()))
                buttonOkSecurity.setDisable(false);
            else
                buttonOkSecurity.setDisable(true);
        });

        dialog.setResultConverter(b -> {
            if (b == buttonOk) {
                LocalDateTime localDate = LocalDateTime.of(firstDayOfActualWeek.getYear(),
                        firstDayOfActualWeek.getMonth(),
                        firstDayOfActualWeek.getDayOfMonth(),
                        Integer.parseInt(hours.getText()),
                        Integer.parseInt(minutes.getText()));
                localDate = localDate.plusDays(dayOfWeek.getValue()-1);
                return new MealDated(meals.getSelectionModel().getSelectedItem(), localDate);
            }
            return null;
        });

        Optional<MealDated> result = dialog.showAndWait();
        if(result.isPresent()){
            User.actualUser.addRepas(result.get());
            mealsDaysList.get(result.get().getTime().getDayOfWeek().getValue()-1).add(result.get());
            sortMeals();
        }
    }

    private void clickOnListviewItem(ListView<MealDated> listView, DayOfWeek dayOfWeek){
        listView.setOnMouseClicked(e -> {
            MealDated selected = listView.getSelectionModel().getSelectedItem();
            if(selected != null){
                popupToSeeMeal(selected, dayOfWeek);
            }
        });
    }

    private void popupToSeeMeal(MealDated mealSelected, DayOfWeek dayOfWeek){
        Dialog dialog = new Dialog<>();
        dialog.setTitle(mealSelected.getMeal().getName());
        dialog.setHeaderText(null);
        dialog.setGraphic(null);

        VBox parentBox = new VBox();
        Text text = new Text("Plats : ");
        parentBox.getChildren().add(text);
        for(Dish dish : mealSelected.getMeal().getDishes()){
            VBox dishesBox = new VBox();
            dishesBox.setPadding(new Insets(0, 0,0 ,10));
            Text dishName = new Text(dish.getName());
            dishesBox.getChildren().add(dishName);
            parentBox.getChildren().add(dishesBox);
            for(Ingredient food : dish.getIngredients()){
                VBox ingredientBox = new VBox();
                ingredientBox.setPadding(new Insets(0, 0,0 ,10));
                String textToPut = food.getFood().getName();
                if(food.getQuantity() != -1){
                    textToPut += " - " + food.getQuantity()+"g";
                }
                Text text1 = new Text(textToPut);
                ingredientBox.getChildren().add(text1);
                dishesBox.getChildren().add(ingredientBox);
            }
        }

        dialog.getDialogPane().setContent(parentBox);

        ButtonType buttonReturn = new ButtonType("Retour", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonReturn);

        ButtonType buttonDelete = new ButtonType("Supprimer", ButtonBar.ButtonData.OTHER);
        dialog.getDialogPane().getButtonTypes().add(buttonDelete);


        dialog.setResultConverter(button -> {
            if(button.equals(buttonDelete)){
                mealsDaysList.get(dayOfWeek.getValue()-1).remove(mealSelected);
                User.actualUser.getMeals().remove(mealSelected);
            }
            return null;
        });

        dialog.showAndWait();
    }

    private boolean checkIfMinuteIsOk(String newValue){
        if(!newValue.matches("\\d*") || newValue.isEmpty()){
            return false;
        }
        int val = Integer.parseInt(newValue);
        return val >= 0 && val <= 59;
    }

    private boolean checkIfHourIsOk(String newValue){
        if(!newValue.matches("\\d*") || newValue.isEmpty()){
            return false;
        }
        int val = Integer.parseInt(newValue);
        return val >= 0 && val <= 23;
    }

    private void setDateText(){
        currentDateText.setText(firstDayOfActualWeek.toString() + " - " + firstDayOfActualWeek.plusDays(6).toString());
    }

    private void clearLists(){
        for(int i=0;i<mealsDaysList.size();i++){
            mealsDaysList.get(i).clear();
        }
    }

    private void loadLists(){
        for(int i = 0; i<User.actualUser.getMeals().size(); i++){
            MealDated meal = User.actualUser.getMeals().get(i);
            LocalDate currentDate = meal.getTime().toLocalDate();
            long daysBetween = DAYS.between(currentDate, firstDayOfActualWeek);
            if(daysBetween <= 0 && daysBetween >= -6){
                mealsDaysList.get(currentDate.getDayOfWeek().getValue()-1).add(meal);
            }
        }
        sortMeals();
    }

    private void sortMeals(){
        for (ObservableList<MealDated> mealDateds : mealsDaysList) {
            Collections.sort(mealDateds);
        }
    }

    private void clickOnPreviousWeek(){
        previousWeekButton.setOnAction(event -> {
            firstDayOfActualWeek = firstDayOfActualWeek.minusWeeks(1);
            setDateText();
            clearLists();
            loadLists();
        });
    }

    private void clickOnNextWeek(){
        nextWeekButton.setOnAction(event -> {
            firstDayOfActualWeek = firstDayOfActualWeek.plusWeeks(1);
            setDateText();
            clearLists();
            loadLists();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
