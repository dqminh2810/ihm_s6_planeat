package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.CookingFrequency;
import model.StatusChoice;
import model.UserSex;

import java.time.LocalDate;

public class ControllerInscription {
    @FXML
    private Button returnButton;
    @FXML
    private TextField nameTexfield;
    @FXML
    private TextField firstnameTexfield;
    @FXML
    private TextField mailTexfield;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private RadioButton sexM;
    @FXML
    private RadioButton sexF;
    @FXML
    private TextField weightTexfield;
    @FXML
    private TextField sizeTexfield;
    @FXML
    private ChoiceBox<StatusChoice> statusChoicebox;
    @FXML
    private ChoiceBox<CookingFrequency> cookingFrequencyChoiceBox;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordConfirmationField;
    @FXML
    private Button inscriptionButton;
    @FXML
    private Text errorText;
    private ToggleGroup group;

    public void init(){
        loadStatusChoice();
        loadCookingFrequency();
        initClickOnSex();
        inscriptionButton.setOnAction(event -> inscription());
    }

    private void inscription(){
        String password = passwordField.getText();
        if(password.length() == 0){
            errorText.setText("Tas pas remplis le mot de passe");
        }
        String passwordConfirmation = passwordConfirmationField.getText();

        String name = nameTexfield.getText();
        String firstName = firstnameTexfield.getText();
        String mail = mailTexfield.getText();
        LocalDate bithDate = birthDatePicker.getValue();
        UserSex sex = (UserSex) group.getSelectedToggle().getUserData();
        float weight = -1;
        if(weightTexfield.getText().length() != 0)
             weight = Float.parseFloat(weightTexfield.getText());
        int size = -1;
        if(sizeTexfield.getText().length() != 0)
            size = Integer.parseInt(sizeTexfield.getText());
        StatusChoice statusChoice = statusChoicebox.getSelectionModel().getSelectedItem();
        CookingFrequency cookingFrequency = cookingFrequencyChoiceBox.getSelectionModel().getSelectedItem();

    }

    private void initClickOnSex(){
        group = new ToggleGroup();
        sexF.setToggleGroup(group);
        sexF.setUserData(UserSex.FEMALE);
        sexM.setToggleGroup(group);
        sexM.setUserData(UserSex.MALE);
        sexM.setSelected(true);
    }

    private void loadCookingFrequency(){
        ObservableList<CookingFrequency> list = FXCollections.observableArrayList();
        list.add(CookingFrequency.NEVER);
        list.add(CookingFrequency.SOMETIMES);
        list.add(CookingFrequency.OFTEN);
        cookingFrequencyChoiceBox.setItems(list);
        cookingFrequencyChoiceBox.getSelectionModel().select(0);
    }

    private void loadStatusChoice(){
        ObservableList<StatusChoice> list = FXCollections.observableArrayList();
        list.add(StatusChoice.STUDENT);
        list.add(StatusChoice.UNEMPLOYED);
        list.add(StatusChoice.WORKMAN);
        list.add(StatusChoice.RETIRED);
        statusChoicebox.setItems(list);
        statusChoicebox.getSelectionModel().select(0);
    }
}
