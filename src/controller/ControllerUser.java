package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mocks.UserMocks;
import model.CookingFrequency;
import model.StatusChoice;
import model.UserSex;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author Montoya Damien
 */

public abstract class ControllerUser extends Controller{
    @FXML
    protected Button returnButton;
    @FXML
    TextField nameTexfield;
    @FXML
    TextField firstnameTexfield;
    @FXML
    TextField mailTexfield;
    @FXML
    DatePicker birthDatePicker;
    @FXML
    RadioButton sexM;
    @FXML
    RadioButton sexF;
    @FXML
    TextField weightTexfield;
    @FXML
    TextField sizeTextfield;
    @FXML
    ChoiceBox<StatusChoice> statusChoicebox;
    @FXML
    ChoiceBox<CookingFrequency> cookingFrequencyChoiceBox;
    @FXML
    PasswordField passwordField;
    @FXML
    PasswordField passwordConfirmationField;
    @FXML
    private Button sendFormButton;
    @FXML
    protected Text errorText;
    ToggleGroup group;

    ControllerUser(Stage stage, Controller previousController) {
        super(stage, previousController);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStatusChoice();
        loadCookingFrequency();
        initClickOnSex();
        sendFormButton.setOnAction(event -> sendForm());
        clickOnReturnButton(returnButton);
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

    protected abstract void sendForm();

    boolean checkMailLength(){
        if(mailTexfield.getText().length() == 0){
            errorText.setText("Tu n'as pas remplis le mail");
            return false;
        }
        return true;
    }

    boolean checkMailAlreadyUsed(){
        if(UserMocks.users.containsKey(mailTexfield.getText())){
            errorText.setText("Cet email existe déjà");
            return false;
        }
        return true;
    }

    boolean checkDate(){
        LocalDate birthDate = birthDatePicker.getValue();
        if(birthDate == null){
            errorText.setText("Tu n'as pas remplis ta date de naissance");
            return false;
        }
        return true;
    }

    boolean checkPassword(){
        if(passwordField.getText().length() == 0){
            errorText.setText("Tu n'as pas remplis le mot de passe");
            return false;
        }
        return true;
    }

    boolean checkConfirmationPassword(){
        if(passwordConfirmationField.getText().length() == 0){
            errorText.setText("Tu n'as pas remplis le mot de passe de confirmation");
            return false;
        }
        return true;
    }

    boolean checkPasswords(){
        if(!passwordField.getText().equals(passwordConfirmationField.getText())){
            errorText.setText("Les mots de passe en correspondent pas");
            return false;
        }
        return true;
    }

    int getSize(){
        int size = -1;
        if(sizeTextfield.getText().length() != 0)
            size = Integer.parseInt(sizeTextfield.getText());
        return size;
    }

    float getWeight(){
        float weight = -1;
        if(weightTexfield.getText().length() != 0)
            weight = Float.parseFloat(weightTexfield.getText());
        return weight;
    }
}
