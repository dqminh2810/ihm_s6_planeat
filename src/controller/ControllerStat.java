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

    public ControllerStat(Stage stage, Controller previousController) {
        super(stage,previousController);
        this.actualView = new ViewStat();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clickOnReturnButton(returnButton);

        Periode periode = new Periode(LocalDate.now(), LocalDate.now());

        statDateStart.setValue(periode.getStartDate());
        statDateEnd.setValue(periode.getEndDate());

        ArrayList<PieChart.Data> datas = new ArrayList<>();

        for (int intakeIndex = 0; intakeIndex < 9; intakeIndex++) {
            datas.add(new PieChart.Data("", User.getActualUser().getIntakes(intakeIndex, periode)));
        }
        energyPieChart.setStartAngle(90);
        energyPieChart.setData(FXCollections.observableArrayList(datas.get(0), new PieChart.Data("", RecommendedDailyAmount.male[0]-datas.get(0).getPieValue()))); //TODO : The real data
        energyPieChart.setLabelsVisible(false);

        fatsPieChart.setStartAngle(90);
        fatsPieChart.setData(FXCollections.observableArrayList(datas.get(1), new PieChart.Data("", RecommendedDailyAmount.male[1]-datas.get(1).getPieValue())));
        fatsPieChart.setLabelsVisible(false);

        saturatedPieChart.setStartAngle(90);
        saturatedPieChart.setData(FXCollections.observableArrayList(datas.get(2), new PieChart.Data("", RecommendedDailyAmount.male[2]-datas.get(2).getPieValue())));
        saturatedPieChart.setLabelsVisible(false);

        carbohydratesPieChart.setStartAngle(90);
        carbohydratesPieChart.setData(FXCollections.observableArrayList(datas.get(3), new PieChart.Data("", RecommendedDailyAmount.male[3]-datas.get(3).getPieValue())));
        carbohydratesPieChart.setLabelsVisible(false);

        sugarPieChart.setStartAngle(90);
        sugarPieChart.setData(FXCollections.observableArrayList(datas.get(4), new PieChart.Data("", RecommendedDailyAmount.male[4]-datas.get(4).getPieValue())));
        sugarPieChart.setLabelsVisible(false);

        proteinsPieChart.setStartAngle(90);
        proteinsPieChart.setData(FXCollections.observableArrayList(datas.get(5), new PieChart.Data("", RecommendedDailyAmount.male[5]-datas.get(5).getPieValue())));
        proteinsPieChart.setLabelsVisible(false);

        saltPieChart.setStartAngle(90);
        saltPieChart.setData(FXCollections.observableArrayList(datas.get(6), new PieChart.Data("", RecommendedDailyAmount.male[6]-datas.get(6).getPieValue())));
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
}
