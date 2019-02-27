package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mocks.MealMocks;
import model.Meal;
import model.MealDated;
import model.User;
import view.ViewBase;
import view.ViewGestionMenu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControllerAgenda extends Controller {
    @FXML
    private Button returnButton;
    @FXML
    private Button previousWeekButton;
    @FXML
    private Button nextWeekButton;
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
    private ObservableList<MealDated> monday;
    private ObservableList<MealDated> tuesday;
    private ObservableList<MealDated> wednesday;
    private ObservableList<MealDated> thursday;
    private ObservableList<MealDated> friday;
    private ObservableList<MealDated> saturday;
    private ObservableList<MealDated> sunday;

    private LocalDate firstDayOfActualWeek;

    public ControllerAgenda(Stage stage, Controller previousController, ViewBase previousView) {
        super(stage,previousController, previousView);
    }

    public void init(){
        firstDayOfActualWeek = LocalDate.now().with(DayOfWeek.MONDAY); //to get monday
        setDateText();
        clickOnReturnButton(returnButton);
        clickOnPreviousWeek();
        clickOnNextWeek();

        monday = FXCollections.observableArrayList();
        monday_list.setItems(monday);
        tuesday = FXCollections.observableArrayList();
        tuesday_list.setItems(tuesday);
        wednesday = FXCollections.observableArrayList();
        wednesday_list.setItems(wednesday);
        thursday = FXCollections.observableArrayList();
        thursday_list.setItems(thursday);
        friday = FXCollections.observableArrayList();
        friday_list.setItems(friday);
        saturday = FXCollections.observableArrayList();
        saturday_list.setItems(saturday);
        sunday = FXCollections.observableArrayList();
        sunday_list.setItems(sunday);

        addMeal1Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.MONDAY));
        addMeal2Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.TUESDAY));
        addMeal3Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.WEDNESDAY));
        addMeal4Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.THURSDAY));
        addMeal5Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.FRIDAY));
        addMeal6Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.SATURDAY));
        addMeal7Button.setOnAction(event -> popupToAddMealDate(DayOfWeek.SUNDAY));

        loadLists();
    }

    private void popupToAddMealDate(DayOfWeek dayOfWeek){
        ChoiceDialog<Meal> dialog = new ChoiceDialog(MealMocks.meals.get(0), MealMocks.meals);
        dialog.setTitle("Selectionner un repas");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Repas");

        ButtonType buttonAddMeal = new ButtonType("Ajouter repas", ButtonBar.ButtonData.OTHER);
        dialog.getDialogPane().getButtonTypes().add(buttonAddMeal);

        ((Button) dialog.getDialogPane().lookupButton(buttonAddMeal)).setOnAction(event -> {
            setView(new ControllerGestionMenu(getStage(), ControllerAgenda.this, new ViewGestionMenu()));
        });

        Optional<Meal> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
        }
    }

    private void setDateText(){
        currentDateText.setText(firstDayOfActualWeek.toString() + " - " + firstDayOfActualWeek.plusDays(6).toString());
    }

    private void clearLists(){
        monday.clear();
        tuesday.clear();
        wednesday.clear();
        thursday.clear();
        friday.clear();
        saturday.clear();
        sunday.clear();
    }

    private void loadLists(){
        ArrayList<MealDated> mondayList = new ArrayList<>();
        ArrayList<MealDated> tuesdayList = new ArrayList<>();
        ArrayList<MealDated> wednesdayList = new ArrayList<>();
        ArrayList<MealDated> thursdayList = new ArrayList<>();
        ArrayList<MealDated> fridayList = new ArrayList<>();
        ArrayList<MealDated> saturdayList = new ArrayList<>();
        ArrayList<MealDated> sundayList = new ArrayList<>();

        for(int i = 0; i<User.actualUser.getMeals().size(); i++){
            MealDated meal = User.actualUser.getMeals().get(i);
            LocalDate currentDate = meal.getTime().toLocalDate();
            long daysBetween = DAYS.between(currentDate, firstDayOfActualWeek);
            if(daysBetween <= 0 && daysBetween >= -6){

                switch (currentDate.getDayOfWeek()){
                    case MONDAY:
                        mondayList.add(meal);
                        break;
                    case TUESDAY:
                        tuesdayList.add(meal);
                        break;
                    case WEDNESDAY:
                        wednesdayList.add(meal);
                        break;
                    case THURSDAY:
                        thursdayList.add(meal);
                        break;
                    case FRIDAY:
                        fridayList.add(meal);
                        break;
                    case SATURDAY:
                        saturdayList.add(meal);
                        break;
                    case SUNDAY:
                        sundayList.add(meal);
                        break;
                }
            }
        }
        Collections.sort(mondayList);
        Collections.sort(tuesdayList);
        Collections.sort(wednesdayList);
        Collections.sort(thursdayList);
        Collections.sort(fridayList);
        Collections.sort(saturdayList);
        Collections.sort(sundayList);
        monday.addAll(mondayList);
        tuesday.addAll(tuesdayList);
        wednesday.addAll(wednesdayList);
        thursday.addAll(thursdayList);
        friday.addAll(fridayList);
        saturday.addAll(saturdayList);
        sunday.addAll(sundayList);
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
}
