package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import mocks.RecommendedDailyAmount;
import model.Ingredient;
import model.Periode;
import model.User;
import view.ViewBase;
import view.ViewStat;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private DatePicker stat2DateStart;
    @FXML
    private DatePicker stat2DateEnd;
    @FXML
    private ListView fruitList;
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
    List<Ingredient> ingredientList;
    PieChart[] charts = {energyPieChart, fatsPieChart, saturatedPieChart, carbohydratesPieChart, sugarPieChart, proteinsPieChart, saltPieChart};

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
        stat2DateStart.setValue(periode.getStartDate());
        stat2DateEnd.setValue(periode.getEndDate());

        statDateStart.setOnAction(event -> {
            periode.setStartDate(statDateStart.getValue());
            statDateStart.setValue(periode.getStartDate());
            statDateEnd.setValue(periode.getEndDate());
            stat2DateStart.setValue(periode.getStartDate());
            stat2DateEnd.setValue(periode.getEndDate());
            updateData();
        });

        statDateEnd.setOnAction(event -> {
            periode.setEndDate(statDateEnd.getValue());
            statDateStart.setValue(periode.getStartDate());
            statDateEnd.setValue(periode.getEndDate());
            stat2DateStart.setValue(periode.getStartDate());
            stat2DateEnd.setValue(periode.getEndDate());
            updateData();
        });

        stat2DateStart.setOnAction(event -> {
            periode.setStartDate(stat2DateStart.getValue());
            statDateStart.setValue(periode.getStartDate());
            statDateEnd.setValue(periode.getEndDate());
            stat2DateStart.setValue(periode.getStartDate());
            stat2DateEnd.setValue(periode.getEndDate());
            updateData();
        });

        stat2DateEnd.setOnAction(event -> {
            periode.setEndDate(stat2DateEnd.getValue());
            statDateStart.setValue(periode.getStartDate());
            statDateEnd.setValue(periode.getEndDate());
            stat2DateStart.setValue(periode.getStartDate());
            stat2DateEnd.setValue(periode.getEndDate());
            updateData();
        });
    }

    private void updateData() {

        updateLists();

        updatePieCharts();

    }

    private void resetListViews(){
        fruitList.getItems().clear();
        veggieList.getItems().clear();
        meatList.getItems().clear();
        fishList.getItems().clear();
        animalOriginList.getItems().clear();
        otherList.getItems().clear();
    }

    private void updateLists() {

        resetListViews();

        ingredientList = getIngredientHistory(periode);

        for (Ingredient ingredient : ingredientList){
            switch (ingredient.getFood().getCategory()){
                case FRUIT:
                    fruitList.getItems().add(ingredient);
                    break;

                case VEGETABLE:
                    veggieList.getItems().add(ingredient);
                    break;

                case RED_MEAT: case WHITE_MEAT:
                    meatList.getItems().add(ingredient);
                    break;

                case FISH:
                    fishList.getItems().add(ingredient);
                    break;

                case ANIMAL_ORIGIN:
                    animalOriginList.getItems().add(ingredient);
                    break;

                case OTHER:
                    otherList.getItems().add(ingredient);
                    break;
            }
        }
    }

    private List<Ingredient> getIngredientHistory(Periode periode) {
        return User.getActualUser().getIngredientHistory(periode);
    }

    private void updatePieCharts() {
        ArrayList<PieChart.Data> pieChartData = new ArrayList<>();
        float intakesData[] = new float[7];
        for (Ingredient ingredient : ingredientList){
            intakesData[0] += ingredient.getFood().getEnergy() * ingredient.getQuantity();
            intakesData[1] += ingredient.getFood().getFat() * ingredient.getQuantity();
            intakesData[2] += ingredient.getFood().getAcid() * ingredient.getQuantity();
            intakesData[3] += ingredient.getFood().getCarbohydrate() * ingredient.getQuantity();
            intakesData[4] += ingredient.getFood().getSugar() * ingredient.getQuantity();
            intakesData[5] += ingredient.getFood().getProtein() * ingredient.getQuantity();
            intakesData[6] += ingredient.getFood().getSalt() * ingredient.getQuantity();
            //intakesData[7] += ingredient.getFood().getCarbohydrate() * ingredient.getQuantity();
        }

        for (float data : intakesData) {
            pieChartData.add(new PieChart.Data("", data));
        }

        float[] energyData;

        /*for (int i = 0; i < charts.length; i++) {
            energyData = calculatePieData(intakesData[i], RecommendedDailyAmount.male[i]*(periode.getInterval()+1));
            charts[i].getStylesheets().clear();
            charts[i].getStylesheets().add(obtainStylesheet(intakesData[i], RecommendedDailyAmount.male[i] * (periode.getInterval()+1)));
            charts[i].setStartAngle(90);
            charts[i].setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        }*/

        energyData = calculatePieData(intakesData[0], RecommendedDailyAmount.male[0]*(periode.getInterval()+1));
        energyPieChart.getStylesheets().clear();
        energyPieChart.getStylesheets().add(obtainStylesheet(intakesData[0], RecommendedDailyAmount.male[0]*(periode.getInterval()+1)));
        energyPieChart.setStartAngle(90);
        energyPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
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

    private String obtainStylesheet(float intakesData, int recommendedAmount) {
        float ratio = intakesData/recommendedAmount;
        if(ratio <= 0.5) { return "resources/css/stylePieChartBelow50.css"; }
        if(ratio <= 0.75) { return "resources/css/stylePieChartBelow75.css"; }
        if(ratio <= 1) { return "resources/css/stylePieChartOkay.css"; }
        if(ratio <= 1.25) { return "resources/css/stylePieChartAbove100.css"; }
        return "resources/css/stylePieChartAbove125.css";
    }

    private float[] calculatePieData(float intakesData, int recommendedAmount) {
        float[] result = new float[2];
        if (recommendedAmount - intakesData >= 0){
            result[0] = intakesData;
            result[1] = recommendedAmount-intakesData;
        }else if (2*recommendedAmount - intakesData >= 0){
            result[0] = intakesData - recommendedAmount;
            result[1] = recommendedAmount - result[0];
        }else{
            result[0] = intakesData;
            result[1] = 0;
        }
        return result;
    }
}
