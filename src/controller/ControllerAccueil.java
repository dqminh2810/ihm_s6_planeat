package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.MealDated;
import model.User;
import view.ViewAddFood;
import view.ViewAgenda;
import view.ViewBase;
import view.ViewStat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControllerAccueil extends Controller {

    @FXML
    private Button prevdayButton;
    @FXML
    private Text curdateText;
    @FXML
    private Button nextdayButton;
    @FXML
    private ListView curdayList;
    @FXML
    private Text curmealHourText;
    @FXML
    private Text curmealNameText;
    @FXML
    private Button statsButton;
    @FXML
    private PieChart caloriesPiechart;
    @FXML
    private PieChart proteinPiechart;
    @FXML
    private PieChart glucosesPiechart;
    @FXML
    private PieChart dontsucrePiechart;
    @FXML
    private Button agendaButton;
    @FXML
    private Button addMealButton;
    @FXML
    private Button createMealButton;
    @FXML
    private Button createIngredientButton;
    @FXML
    private Button profileButton;
    @FXML
    private Text curHourText;

    LocalDate selectedDay;
    private ObservableList<MealDated> selectedDayMealList;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");


    public ControllerAccueil(Stage stage, Controller previousController, ViewBase previousView) {
        super(stage,previousController, previousView);
    }

    private void changeDay(LocalDate day){
            this.selectedDay = day;
            curdateText.setText(day.format(DateTimeFormatter.ofPattern("dd/MM")));
            clearList();
            loadList();
    }

    private void setCurrentMeal(){

        Collections.sort(User.actualUser.getMeals());

        for(MealDated meal : User.actualUser.getMeals()){
            if(meal.getTime().compareTo(LocalDateTime.now()) >=  0){
                curmealNameText.setText(meal.getMeal().getName());
                curmealHourText.setText(meal.getTime().format(formatter));
                return;
            }
        }

        //TODO : trouver quoi marquer dans le cas ou aucun repas n'as été entré pour l'instant. ex: 1ere ouverture de l'app
        curmealNameText.setText("Aucun repas prévus prochainement !");
        curmealHourText.setText("(entrer de nouveaux repas en cliquant sur 'ajouter repas')");
    }

    private void clearList() {
        selectedDayMealList.clear();
    }

    private void loadList(){
        for(int i = 0; i<User.actualUser.getMeals().size(); i++){
            MealDated meal = User.actualUser.getMeals().get(i);
            LocalDate currentDate = meal.getTime().toLocalDate();
            long daysBetween = DAYS.between(currentDate, selectedDay);
            if(daysBetween ==0){
                selectedDayMealList.add(meal);
            }
        }
        Collections.sort(selectedDayMealList);
    }

    public void init(){
        //liste calendrier
        selectedDayMealList =  FXCollections.observableArrayList();
        curdayList.setItems(selectedDayMealList);

        changeDay(LocalDate.now());
        prevdayButton.setOnAction(event -> changeDay(selectedDay.minusDays(1)));
        nextdayButton.setOnAction(event -> changeDay(selectedDay.plusDays(1)));

        //menu a droite TODO: ajouter bonjours utilisateur avec un text a droite, ajouter iconnes.
        agendaButton.setOnAction(event -> setView(new ControllerAgenda(getStage(), this, new ViewAgenda())));
        createIngredientButton.setOnAction(event -> setView(new ControllerAddFood(getStage(), this, new ViewAddFood())));
        //createMealButton.setOnAction(event ->  new ControllerAddDish(getStage(),this,new ViewAddD()));
        //addMealButton.setOnAction(event -> setView(new ControllerAddDish(getStage(), this, new ViewAddDish())));
        //profileButton.setOnAction(event -> setView(new ControllerProfile(getStage(), this, new ViewProfile())));


        //statistiques TODO: trouver les "cicle % round progress" (import)
        statsButton.setOnAction(event -> setView(new ControllerStat(getStage(),this, new ViewStat())));

        dontsucrePiechart.setStartAngle(90);
        dontsucrePiechart.setData(FXCollections.observableArrayList(new PieChart.Data("", 50),new PieChart.Data("", 50)));
        dontsucrePiechart.setLabelsVisible(false);

        glucosesPiechart.setStartAngle(90);
        glucosesPiechart.setData(FXCollections.observableArrayList(new PieChart.Data("", 30),new PieChart.Data("", 70)));
        glucosesPiechart.setLabelsVisible(false);

        caloriesPiechart.setStartAngle(90);
        caloriesPiechart.setData(FXCollections.observableArrayList(new PieChart.Data("", 90),new PieChart.Data("", 10)));
        caloriesPiechart.setLabelsVisible(false);

        proteinPiechart.setStartAngle(90);
        proteinPiechart.setData(FXCollections.observableArrayList(new PieChart.Data("", 95),new PieChart.Data("", 5)));
        proteinPiechart.setLabelsVisible(false);

        //prochain repas
        setCurrentMeal();

        //heure TODO: un timer de 1 minute pour la mettre a jour ?
        curHourText.setText(LocalDateTime.now().format(formatter));

    }

}
