package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.MealDated;
import model.User;
import org.pdfsam.ui.RingProgressIndicator;
import view.*;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
//    @FXML
//    private PieChart caloriesPieChart;
//    @FXML
//    private PieChart proteinPiechart;
//    @FXML
//    private PieChart glucosesPiechart;
//    @FXML
//    private PieChart dontsucrePiechart;
    @FXML
    private GridPane statsGridPane;
    @FXML
    private Button agendaButton;
    @FXML
    private Button addMealButton;
    @FXML
    private Button dishManagerButton;
    @FXML
    private Button createIngredientButton;
    @FXML
    private Button profileButton;
    @FXML
    private Text curHourText;
    @FXML
    private Text helloUserText;

    private LocalDate selectedDay;
    private ObservableList<MealDated> selectedDayMealList;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public ControllerAccueil(Stage stage, Controller previousController) {
        super(stage,previousController);
        this.actualView = new ViewAccueil();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //liste calendrier
        selectedDayMealList =  FXCollections.observableArrayList();
        curdayList.setItems(selectedDayMealList);

        changeDay(LocalDate.now());
        prevdayButton.setOnAction(event -> changeDay(selectedDay.minusDays(1)));
        nextdayButton.setOnAction(event -> changeDay(selectedDay.plusDays(1)));

        String hello = "Bonjour";
        if(User.actualUser.getFirstName() != null && User.actualUser.getFirstName().length() != 0)
            hello += " " + User.actualUser.getFirstName();

        helloUserText.setText(hello);

        agendaButton.setOnAction(event -> setView(new ControllerAgenda(getStage(), this)));
        createIngredientButton.setOnAction(event -> setView(new ControllerAddFood(getStage(), this)));
        dishManagerButton.setOnAction(event ->  setView(new ControllerDishManager(getStage(),this)));
        addMealButton.setOnAction(event -> setView(new ControllerGestionMenu(getStage(), this)));
        profileButton.setOnAction(event -> setView(new ControllerEditProfile(getStage(), this)));


        //statistiques

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        RingProgressIndicator ringCalories = new RingProgressIndicator();
        ringCalories.setRingWidth(50);
        ringCalories.setProgress(195);
        statsGridPane.add(ringCalories,0,0);

        RingProgressIndicator ringProteine = new RingProgressIndicator();
        ringProteine.setRingWidth(50);
        ringProteine.setProgress(90);
        statsGridPane.add(ringProteine,1,0);

        RingProgressIndicator ringGlucose = new RingProgressIndicator();
        ringGlucose.setRingWidth(50);
        ringGlucose.setProgress(75);
        statsGridPane.add(ringGlucose,0,2);

        RingProgressIndicator ringDontSucre = new RingProgressIndicator();
        ringDontSucre.setRingWidth(50);
        ringDontSucre.setProgress(160);
        statsGridPane.add(ringDontSucre,1,2);

        //roundyRound(ring);

        statsButton.setOnAction(event -> setView(new ControllerStat(getStage(),this)));
//        dontsucrePiechart.setStartAngle(90);
//        dontsucrePiechart.setData(FXCollections.observableArrayList(new PieChart.Data("", 50),new PieChart.Data("", 50)));
//        dontsucrePiechart.setLabelsVisible(false);
//
//        glucosesPiechart.setStartAngle(90);
//        glucosesPiechart.setData(FXCollections.observableArrayList(new PieChart.Data("", 30),new PieChart.Data("", 70)));
//        glucosesPiechart.setLabelsVisible(false);
//
//        caloriesPiechart.setStartAngle(90);
//        caloriesPiechart.setData(FXCollections.observableArrayList(new PieChart.Data("", 90),new PieChart.Data("", 10)));
//        caloriesPiechart.setLabelsVisible(false);
//
//        proteinPiechart.setStartAngle(90);
//        proteinPiechart.setData(FXCollections.observableArrayList(new PieChart.Data("", 95),new PieChart.Data("", 5)));
//        proteinPiechart.setLabelsVisible(false);

        //prochain repas
        setCurrentMeal();
        displayHour();

    }

    private void displayHour(){
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> curHourText.setText(LocalDateTime.now().format(formatter)), 0, 5, TimeUnit.SECONDS);
    }

    private void roundyRound(RingProgressIndicator ring){
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> ring.setProgress(ring.getProgress()+1), 0, 100, TimeUnit.MILLISECONDS);
    }
}
