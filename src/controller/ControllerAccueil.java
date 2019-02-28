package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.ViewAddFood;
import view.ViewAgenda;
import view.ViewBase;

import java.time.DayOfWeek;
import java.time.LocalDate;

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




    public ControllerAccueil(Stage stage, Controller previousController, ViewBase previousView) {
        super(stage,previousController, previousView);
    }

    public void init(){
        //date du jour
        LocalDate day = LocalDate.now();
        curdateText.setText(day.toString());

        //association button
        agendaButton.setOnAction(event -> setView(new ControllerAgenda(getStage(), this, new ViewAgenda())));
        createIngredientButton.setOnAction(event -> setView(new ControllerAddFood(getStage(), this, new ViewAddFood())));
        //createMealButton.setOnAction(event ->  new Con);
        //addMealButton.setOnAction(event -> setView(new ControllerAddDish(getStage(), this, new ViewAddDish())));
        //profileButton.setOnAction(event -> setView(new ControllerProfile(getStage(), this, new ViewProfile())));
    }

}
