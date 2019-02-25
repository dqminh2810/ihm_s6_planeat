package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Meal;
import model.User;
import view.ViewBase;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    private ListView<Meal> monday_list;
    @FXML
    private ListView<Meal> tuesday_list;
    @FXML
    private ListView<Meal> wednesday_list;
    @FXML
    private ListView<Meal> thursday_list;
    @FXML
    private ListView<Meal> friday_list;
    @FXML
    private ListView<Meal> saturday_list;
    @FXML
    private ListView<Meal> sunday_list;
    private ObservableList<Meal> monday;
    private ObservableList<Meal> tuesday;
    private ObservableList<Meal> wednesday;
    private ObservableList<Meal> thursday;
    private ObservableList<Meal> friday;
    private ObservableList<Meal> saturday;
    private ObservableList<Meal> sunday;


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

        loadLists();
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
        ArrayList<Meal> mondayList = new ArrayList<>();
        ArrayList<Meal> tuesdayList = new ArrayList<>();
        ArrayList<Meal> wednesdayList = new ArrayList<>();
        ArrayList<Meal> thursdayList = new ArrayList<>();
        ArrayList<Meal> fridayList = new ArrayList<>();
        ArrayList<Meal> saturdayList = new ArrayList<>();
        ArrayList<Meal> sundayList = new ArrayList<>();

        for(int i = 0; i<User.actualUser.getMeals().size(); i++){
            Meal meal = User.actualUser.getMeals().get(i);
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
