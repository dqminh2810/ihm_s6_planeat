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
import model.UserSex;
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

        double intakesData[] = new double[9];
        for (Ingredient ingredient : ingredientList){
            intakesData[0] += ingredient.getFood().getEnergy() * ingredient.getQuantity();
            intakesData[1] += ingredient.getFood().getFat() * ingredient.getQuantity();
            intakesData[2] += ingredient.getFood().getAcid() * ingredient.getQuantity();
            intakesData[3] += ingredient.getFood().getCarbohydrate() * ingredient.getQuantity();
            intakesData[4] += ingredient.getFood().getSugar() * ingredient.getQuantity();
            intakesData[5] += ingredient.getFood().getProtein() * ingredient.getQuantity();
            intakesData[6] += ingredient.getFood().getSalt() * ingredient.getQuantity();
            intakesData[7] += ingredient.getFood().getFibres() * ingredient.getQuantity();
            intakesData[8] += ingredient.getFood().getCalcium() * ingredient.getQuantity();
        }

        double[] recommendedIntakes = getReferenceIntakes();

        double[] energyData;

        /*for (int i = 0; i < charts.length; i++) {
            energyData = calculatePieData(intakesData[i], RecommendedDailyAmount.male[i]*(periode.getInterval()+1));
            charts[i].getStylesheets().clear();
            charts[i].getStylesheets().add(obtainStylesheet(intakesData[i], RecommendedDailyAmount.male[i] * (periode.getInterval()+1)));
            charts[i].setStartAngle(90);
            charts[i].setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        }*/

        energyData = calculatePieData(intakesData[0], recommendedIntakes[0]*(periode.getInterval()+1));
        energyPieChart.getStylesheets().clear();
        energyPieChart.getStylesheets().add(obtainStylesheet(intakesData[0], recommendedIntakes[0]*(periode.getInterval()+1)));
        energyPieChart.setStartAngle(90);
        energyPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        energyPieChart.setLabelsVisible(false);

        energyData = calculatePieData(intakesData[1], recommendedIntakes[1]*(periode.getInterval()+1));
        fatsPieChart.getStylesheets().clear();
        fatsPieChart.getStylesheets().add(obtainStylesheet(intakesData[1], recommendedIntakes[1]*(periode.getInterval()+1)));
        fatsPieChart.setStartAngle(90);
        fatsPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        fatsPieChart.setLabelsVisible(false);

        energyData = calculatePieData(intakesData[2], recommendedIntakes[2]*(periode.getInterval()+1));
        saturatedPieChart.getStylesheets().clear();
        saturatedPieChart.getStylesheets().add(obtainStylesheet(intakesData[2], recommendedIntakes[2]*(periode.getInterval()+1)));
        saturatedPieChart.setStartAngle(90);
        saturatedPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        saturatedPieChart.setLabelsVisible(false);

        energyData = calculatePieData(intakesData[3], recommendedIntakes[3]*(periode.getInterval()+1));
        carbohydratesPieChart.getStylesheets().clear();
        carbohydratesPieChart.getStylesheets().add(obtainStylesheet(intakesData[3], recommendedIntakes[3]*(periode.getInterval()+1)));
        carbohydratesPieChart.setStartAngle(90);
        carbohydratesPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        carbohydratesPieChart.setLabelsVisible(false);

        energyData = calculatePieData(intakesData[4], recommendedIntakes[4]*(periode.getInterval()+1));
        sugarPieChart.getStylesheets().clear();
        sugarPieChart.getStylesheets().add(obtainStylesheet(intakesData[4], recommendedIntakes[4]*(periode.getInterval()+1)));
        sugarPieChart.setStartAngle(90);
        sugarPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        sugarPieChart.setLabelsVisible(false);

        energyData = calculatePieData(intakesData[5], recommendedIntakes[5]*(periode.getInterval()+1));
        proteinsPieChart.getStylesheets().clear();
        proteinsPieChart.getStylesheets().add(obtainStylesheet(intakesData[5], recommendedIntakes[5]*(periode.getInterval()+1)));
        proteinsPieChart.setStartAngle(90);
        proteinsPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        proteinsPieChart.setLabelsVisible(false);

        energyData = calculatePieData(intakesData[6], recommendedIntakes[6]*(periode.getInterval()+1));
        saltPieChart.getStylesheets().clear();
        saltPieChart.getStylesheets().add(obtainStylesheet(intakesData[6], recommendedIntakes[6]*(periode.getInterval()+1)));
        saltPieChart.setStartAngle(90);
        saltPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        saltPieChart.setLabelsVisible(false);

        energyData = calculatePieData(intakesData[7], recommendedIntakes[7]*(periode.getInterval()+1));
        fibresPieChart.getStylesheets().clear();
        fibresPieChart.getStylesheets().add(obtainStylesheet(intakesData[7], recommendedIntakes[7]*(periode.getInterval()+1)));
        fibresPieChart.setStartAngle(90);
        fibresPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        fibresPieChart.setLabelsVisible(false);

        energyData = calculatePieData(intakesData[8], recommendedIntakes[8]*(periode.getInterval()+1));
        calciumPieChart.getStylesheets().clear();
        calciumPieChart.getStylesheets().add(obtainStylesheet(intakesData[8], recommendedIntakes[8]*(periode.getInterval()+1)));
        calciumPieChart.setStartAngle(90);
        calciumPieChart.setData(FXCollections.observableArrayList(new PieChart.Data("", energyData[0]), new PieChart.Data("", energyData[1])));
        calciumPieChart.setLabelsVisible(false);
    }

    private double[] getReferenceIntakes() {
        if (User.getActualUser().getSex().equals(UserSex.FEMALE))
            return RecommendedDailyAmount.female;
        else
            return RecommendedDailyAmount.male;
    }

    private String obtainStylesheet(double intakesData, double recommendedAmount) {
        double ratio = intakesData/recommendedAmount;
        if(ratio <= 0.5) { return "resources/css/stylePieChartBelow50.css"; }
        if(ratio <= 0.75) { return "resources/css/stylePieChartBelow75.css"; }
        if(ratio <= 1) { return "resources/css/stylePieChartOkay.css"; }
        if(ratio <= 1.25) { return "resources/css/stylePieChartAbove100.css"; }
        return "resources/css/stylePieChartAbove125.css";
    }

    private double[] calculatePieData(double intakesData, double recommendedAmount) {
        double[] result = new double[2];
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
