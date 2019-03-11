package controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Ingredient;
import model.MealDated;
import model.Periode;
import model.User;
import org.pdfsam.ui.RingProgressIndicator;
import view.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static java.time.temporal.ChronoUnit.DAYS;
import static utils.Util.*;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Bauce Camille
 */

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

    private RingProgressIndicator ringCalories;
    private RingProgressIndicator ringProteines;
    private RingProgressIndicator ringSucre;
    private RingProgressIndicator ringFibres;
    private double[] intakesData;


    public ControllerAccueil(Stage stage, Controller previousController) {
        super(stage,previousController);
        this.actualView = new ViewAccueil();
    }

    private void changeDay(LocalDate day){
            this.selectedDay = day;
            curdateText.setText(day.format(DateTimeFormatter.ofPattern("dd/MM")));
            clearList();
            loadList();
            update();
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

        //statistiques

        ringCalories = new RingProgressIndicator();
        ringCalories.setRingWidth(50);
        statsGridPane.add(ringCalories,0,0);

        ringProteines = new RingProgressIndicator();
        ringProteines.setRingWidth(50);
        statsGridPane.add(ringProteines,1,0);

        ringSucre = new RingProgressIndicator();
        ringSucre.setRingWidth(50);
        statsGridPane.add(ringSucre,0,2);

        ringFibres = new RingProgressIndicator();
        ringFibres.setRingWidth(50);
        statsGridPane.add(ringFibres,1,2);

        statsButton.setOnAction(event -> setView(new ControllerStat(getStage(),this)));

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
        createIngredientButton.setOnAction(event -> setView(new ControllerFoodManager(getStage(), this)));
        dishManagerButton.setOnAction(event ->  setView(new ControllerDishManager(getStage(),this)));
        addMealButton.setOnAction(event -> setView(new ControllerGestionMenu(getStage(), this)));
        profileButton.setOnAction(event -> setView(new ControllerEditProfile(getStage(), this)));

        //prochain repas
        setCurrentMeal();
        displayHour();

    }

    private void update() {
        updateIngredientList();
        updateIndicators();
    }

    private void updateIndicators() {

        int dataPercentage;

        double[] recommendedIntakes = getReferenceIntakes();

        dataPercentage = getIntakePercentage(intakesData[0], recommendedIntakes[0]);
        ringCalories.getStylesheets().clear();
        ringCalories.getStylesheets().add(obtainStylesheet(dataPercentage));
        ringCalories.setProgress(dataPercentage);

        dataPercentage = getIntakePercentage(intakesData[1], recommendedIntakes[1]);
        ringProteines.getStylesheets().clear();
        ringProteines.getStylesheets().add(obtainStylesheet(dataPercentage));
        ringProteines.setProgress(dataPercentage);

        //sucre
        dataPercentage = getIntakePercentage(intakesData[2], recommendedIntakes[2]);
        ringSucre.getStylesheets().clear();
        ringSucre.getStylesheets().add(obtainStylesheet(dataPercentage));
        ringSucre.setProgress(dataPercentage);

        //fibre
        dataPercentage = getIntakePercentage(intakesData[3], recommendedIntakes[3]);
        ringFibres.getStylesheets().clear();
        ringFibres.getStylesheets().add(obtainStylesheet(dataPercentage));
        ringFibres.setProgress(dataPercentage);
    }

    private void updateIngredientList() {
        Periode periode = new Periode(selectedDay,selectedDay);
        List<Ingredient> ingredientList = getIngredientHistory(periode);
        intakesData = new double[4];
        for (Ingredient ingredient : ingredientList){
            intakesData[0] += (ingredient.getFood().getEnergy()/100) * ingredient.getQuantity();
            intakesData[2] += (ingredient.getFood().getSugar()/100) * ingredient.getQuantity();
            intakesData[1] += (ingredient.getFood().getProtein()/100) * ingredient.getQuantity();
            intakesData[3] += (ingredient.getFood().getFibres()/100) * ingredient.getQuantity();
        }
    }

    private void displayHour(){
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> curHourText.setText(LocalDateTime.now().format(formatter)), 0, 5, TimeUnit.SECONDS);
    }
}
