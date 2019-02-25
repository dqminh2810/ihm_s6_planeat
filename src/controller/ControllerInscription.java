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
import model.User;
import model.UserSex;
import view.ViewAccueil;
import view.ViewBase;
import view.ViewLancement;

import java.time.LocalDate;

public class ControllerInscription extends Controller {
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

    public ControllerInscription(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage,previousController, actualView);
    }

    public void init(){
        loadStatusChoice();
        loadCookingFrequency();
        initClickOnSex();
        inscriptionButton.setOnAction(event -> inscription());
        clickOnReturnButton(returnButton);
    }

    private void inscription(){
        errorText.setText("");
        String name = nameTexfield.getText();
        String firstName = firstnameTexfield.getText();
        String mail = mailTexfield.getText();
        if(mail.length() == 0){
            errorText.setText("Tu n'as pas remplis le mail");
            return;
        }

        if(UserMocks.users.containsKey(mail)){
            errorText.setText("Cet email existe déjà");
            return;
        }

        LocalDate birthDate = birthDatePicker.getValue();
        if(birthDate == null){
            errorText.setText("Tu n'as pas remplis ta date de naissance");
            return;
        }

        String password = passwordField.getText();
        if(password.length() == 0){
            errorText.setText("Tu n'as pas remplis le mot de passe");
            return;
        }

        String passwordConfirmation = passwordConfirmationField.getText();
        if(passwordConfirmation.length() == 0){
            errorText.setText("Tu n'as pas remplis le mot de passe de confirmation");
            return;
        }

        if(!password.equals(passwordConfirmation)){
            errorText.setText("Les mots de passe en correspondent pas");
            return;
        }

        UserSex sex = (UserSex) group.getSelectedToggle().getUserData();
        float weight = -1;
        if(weightTexfield.getText().length() != 0)
             weight = Float.parseFloat(weightTexfield.getText());
        int size = -1;
        if(sizeTexfield.getText().length() != 0)
            size = Integer.parseInt(sizeTexfield.getText());
        StatusChoice statusChoice = statusChoicebox.getSelectionModel().getSelectedItem();
        CookingFrequency cookingFrequency = cookingFrequencyChoiceBox.getSelectionModel().getSelectedItem();

        User user = new User(name, firstName, birthDate, mail, sex, weight, size, statusChoice, cookingFrequency, password);
        User.actualUser = user;
        setView(new ControllerAccueil(getStage(), this, new ViewAccueil()));
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
