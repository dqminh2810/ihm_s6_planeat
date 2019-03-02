package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Periode;
import view.ViewBase;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerStat extends Controller {
    @FXML
    private Button returnButton;
    @FXML
    private DatePicker statDateStart;
    @FXML
    private DatePicker statDateEnd;
    @FXML
    private PieChart energyPieChart;
    @FXML
    private PieChart fatsPieChart;
    @FXML
    private PieChart saturatedPieChart;
    @FXML
    private PieChart carbohydratesPieChart;
    @FXML
    private PieChart sugarPieChart;
    @FXML
    private PieChart proteinsPieChart;
    @FXML
    private PieChart saltPieChart;
    @FXML
    private PieChart fibresPieChart;
    @FXML
    private PieChart calciumPieChart;

    @FXML
    private ListView fuitList;
    @FXML
    private ListView veggieList;
    @FXML
    private ListView meatList;
    @FXML
    private ListView fishList;
    @FXML
    private ListView animalOriginList;
    @FXML
    private ListView otherList;

    public ControllerStat(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage,previousController, actualView);
    }

    public void init(){
        returnButton.setOnAction(event -> System.out.println("back"));

        Periode periode = new Periode(LocalDate.now().minusDays(1), LocalDate.now());

        statDateStart.setValue(periode.getStartDate());
        statDateEnd.setValue(periode.getEndDate());

        energyPieChart.setStartAngle(90);
        energyPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Energie", 55), new PieChart.Data("Energie", 45))); //TODO : The real data
        energyPieChart.setLabelsVisible(false);

        fatsPieChart.setStartAngle(90);
        fatsPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Lipides", 120)));
        fatsPieChart.setLabelsVisible(false);

        saturatedPieChart.setStartAngle(90);
        saturatedPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Acides Gras Saturés", 67)));
        saturatedPieChart.setLabelsVisible(false);

        carbohydratesPieChart.setStartAngle(90);
        carbohydratesPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Glucides", 67)));
        carbohydratesPieChart.setLabelsVisible(false);

        sugarPieChart.setStartAngle(90);
        sugarPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Sucre", 100)));
        sugarPieChart.setLabelsVisible(false);

        proteinsPieChart.setStartAngle(90);
        proteinsPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Protéines", 67)));
        proteinsPieChart.setLabelsVisible(false);

        saltPieChart.setStartAngle(90);
        saltPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Sel", 67)));
        saltPieChart.setLabelsVisible(false);

        fibresPieChart.setStartAngle(90);
        fibresPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Fibres", 67)));
        fibresPieChart.setLabelsVisible(false);

        calciumPieChart.setStartAngle(90);
        calciumPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Calcium", 67)));
        calciumPieChart.setLabelsVisible(false);


        statDateStart.setOnAction(event -> {
            periode.setStartDate(statDateStart.getValue());
            statDateStart.setValue(periode.getStartDate());
            statDateEnd.setValue(periode.getEndDate());
        });

        statDateEnd.setOnAction(event -> {
            periode.setEndDate(statDateEnd.getValue());
            statDateStart.setValue(periode.getStartDate());
            statDateEnd.setValue(periode.getEndDate());
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
