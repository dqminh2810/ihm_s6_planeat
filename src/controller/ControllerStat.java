package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ControllerStat {
    @FXML
    private Button returnButton;
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


    public void init(){
        returnButton.setOnAction(event -> System.out.println("back"));

        energyPieChart = new PieChart(FXCollections.observableArrayList(new PieChart.Data("Energie", 55))); //TODO : The real data
        energyPieChart.setLabelsVisible(false);

        fatsPieChart = new PieChart(FXCollections.observableArrayList(new PieChart.Data("Lipides", 120)));
        fatsPieChart.setLabelsVisible(false);

        saturatedPieChart = new PieChart(FXCollections.observableArrayList(new PieChart.Data("Acides Gras Saturés", 67)));
        saturatedPieChart.setLabelsVisible(false);

        carbohydratesPieChart = new PieChart(FXCollections.observableArrayList(new PieChart.Data("Glucides", 67)));
        carbohydratesPieChart.setLabelsVisible(false);

        sugarPieChart = new PieChart(FXCollections.observableArrayList(new PieChart.Data("Sucre", 100)));
        sugarPieChart.setLabelsVisible(false);

        proteinsPieChart = new PieChart(FXCollections.observableArrayList(new PieChart.Data("Protéines", 67)));
        proteinsPieChart.setLabelsVisible(false);

        saltPieChart = new PieChart(FXCollections.observableArrayList(new PieChart.Data("Sel", 67)));
        saltPieChart.setLabelsVisible(false);

        fibresPieChart = new PieChart(FXCollections.observableArrayList(new PieChart.Data("Fibres", 67)));
        fibresPieChart.setLabelsVisible(false);

        calciumPieChart = new PieChart(FXCollections.observableArrayList(new PieChart.Data("Calcium", 67)));
        calciumPieChart.setLabelsVisible(false);
    }
}
