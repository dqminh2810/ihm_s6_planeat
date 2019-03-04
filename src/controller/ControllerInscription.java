package controller;

import javafx.stage.Stage;
import model.CookingFrequency;
import model.StatusChoice;
import model.User;
import model.UserSex;
import view.ViewAccueil;
import view.ViewBase;
import view.ViewInscription;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerInscription extends ControllerUser {

    ControllerInscription(Stage stage, Controller previousController) {
        super(stage,previousController);
        this.actualView = new ViewInscription();
    }

    protected void sendForm(){
        errorText.setText("");

        if(!checkMailLength())
            return;

        if(!checkMailAlreadyUsed())
            return;

        if(!checkDate())
            return;

        if(!checkPassword())
            return;

        if(!checkConfirmationPassword())
            return;

        if(!checkPasswords())
            return;

        String name = nameTexfield.getText();
        String firstName = firstnameTexfield.getText();
        String mail = mailTexfield.getText();
        UserSex sex = (UserSex) group.getSelectedToggle().getUserData();
        StatusChoice statusChoice = statusChoicebox.getSelectionModel().getSelectedItem();
        CookingFrequency cookingFrequency = cookingFrequencyChoiceBox.getSelectionModel().getSelectedItem();

        User.actualUser = new User(name, firstName, birthDatePicker.getValue(), mail, sex, getWeight(), getSize(), statusChoice, cookingFrequency, passwordField.getText());
        setView(new ControllerAccueil(getStage(), this));
    }
}
