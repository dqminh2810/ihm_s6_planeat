package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import mocks.RecommendedDailyAmount;
import model.Periode;
import model.User;
import view.ViewBase;
import view.ViewStat;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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

    Periode periode;

    public ControllerStat(Stage stage, Controller previousController) {
        super(stage,previousController);
        this.actualView = new ViewStat();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clickOnReturnButton(returnButton);

        periode = new Periode(LocalDate.now(), LocalDate.now());

        updateData();

        statDateStart.setValue(periode.getStartDate());
        statDateEnd.setValue(periode.getEndDate());

        statDateStart.setOnAction(event -> {
            periode.setStartDate(statDateStart.getValue());
            statDateStart.setValue(periode.getStartDate());
            statDateEnd.setValue(periode.getEndDate());
            updateData();
        });

        statDateEnd.setOnAction(event -> {
            periode.setEndDate(statDateEnd.getValue());
            statDateStart.setValue(periode.getStartDate());
            statDateEnd.setValue(periode.getEndDate());
            updateData();
        });
    }

    private void updateData() {

        ArrayList<PieChart.Data> pieChartData = new ArrayList<>();

        for (int intakeIndex = 0; intakeIndex < 9; intakeIndex++) {
            pieChartData.add(new PieChart.Data("", User.getActualUser().getIntakes(intakeIndex, periode)));
        }
        energyPieChart.setStartAngle(90);
        energyPieChart.setData(FXCollections.observableArrayList(pieChartData.get(0), new PieChart.Data("", RecommendedDailyAmount.male[0]-pieChartData.get(0).getPieValue()))); //TODO : The real data
        energyPieChart.setLabelsVisible(false);

        fatsPieChart.setStartAngle(90);
        fatsPieChart.setData(FXCollections.observableArrayList(pieChartData.get(1), new PieChart.Data("", RecommendedDailyAmount.male[1]-pieChartData.get(1).getPieValue())));
        fatsPieChart.setLabelsVisible(false);

        saturatedPieChart.setStartAngle(90);
        saturatedPieChart.setData(FXCollections.observableArrayList(pieChartData.get(2), new PieChart.Data("", RecommendedDailyAmount.male[2]-pieChartData.get(2).getPieValue())));
        saturatedPieChart.setLabelsVisible(false);

        carbohydratesPieChart.setStartAngle(90);
        carbohydratesPieChart.setData(FXCollections.observableArrayList(pieChartData.get(3), new PieChart.Data("", RecommendedDailyAmount.male[3]-pieChartData.get(3).getPieValue())));
        carbohydratesPieChart.setLabelsVisible(false);

        sugarPieChart.setStartAngle(90);
        sugarPieChart.setData(FXCollections.observableArrayList(pieChartData.get(4), new PieChart.Data("", RecommendedDailyAmount.male[4]-pieChartData.get(4).getPieValue())));
        sugarPieChart.setLabelsVisible(false);

        proteinsPieChart.setStartAngle(90);
        proteinsPieChart.setData(FXCollections.observableArrayList(pieChartData.get(5), new PieChart.Data("", RecommendedDailyAmount.male[5]-pieChartData.get(5).getPieValue())));
        proteinsPieChart.setLabelsVisible(false);

        saltPieChart.setStartAngle(90);
        saltPieChart.setData(FXCollections.observableArrayList(pieChartData.get(6), new PieChart.Data("", RecommendedDailyAmount.male[6]-pieChartData.get(6).getPieValue())));
        saltPieChart.setLabelsVisible(false);

        fibresPieChart.setStartAngle(90);
        fibresPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Fibres", 67)));
        fibresPieChart.setLabelsVisible(false);

        calciumPieChart.setStartAngle(90);
        calciumPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("Calcium", 67)));
        calciumPieChart.setLabelsVisible(false);

    }
}
