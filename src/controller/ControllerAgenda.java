package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;
import view.ViewBase;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ControllerAgenda extends Controller {
    @FXML
    private Button returnButton;
    @FXML
    private Button previousWeekButton;
    @FXML
    private Button nextWeekButton;
    @FXML
    private Text currentDateText;

    private LocalDate firstDayOfActualWeek;

    public ControllerAgenda(Stage stage, Controller previousController, ViewBase previousView) {
        super(stage,previousController, previousView);
    }

    public void init(){
        firstDayOfActualWeek = LocalDate.now().with(DayOfWeek.MONDAY); //to get monday
        setDateText();
        System.out.println(firstDayOfActualWeek);
        clickOnReturnButton(returnButton);
        clickOnPreviousWeek();
        clickOnNextWeek();
        System.out.println(User.actualUser);
    }

    private void setDateText(){
        currentDateText.setText(firstDayOfActualWeek.toString() + " - " + firstDayOfActualWeek.plusDays(6).toString());
    }

    private void clickOnPreviousWeek(){
        previousWeekButton.setOnAction(event -> {
            firstDayOfActualWeek = firstDayOfActualWeek.minusWeeks(1);
            setDateText();
        });
    }

    private void clickOnNextWeek(){
        nextWeekButton.setOnAction(event -> {
            firstDayOfActualWeek = firstDayOfActualWeek.plusWeeks(1);
            setDateText();
        });
    }
}
