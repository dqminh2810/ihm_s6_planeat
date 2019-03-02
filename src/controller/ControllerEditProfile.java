package controller;

import javafx.stage.Stage;
import model.CookingFrequency;
import model.StatusChoice;
import model.User;
import model.UserSex;
import view.ViewBase;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEditProfile extends ControllerUser {

    public ControllerEditProfile(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage, previousController, actualView);
    }

    @Override
    protected void sendForm() {
        if(!checkMailLength())
            return;

        if(!checkMailAlreadyUsed())
            return;

        if(!checkDate())
            return;

        if(passwordField.getText().length() != 0 || passwordConfirmationField.getText().length() != 0)
            if(!checkPasswords())
                return;

        User user = User.getActualUser();
        user.setName(nameTexfield.getText());
        user.setFirstName(firstnameTexfield.getText());
        user.setMail(mailTexfield.getText());
        user.setSex((UserSex) group.getSelectedToggle().getUserData());
        user.setStatus(statusChoicebox.getSelectionModel().getSelectedItem());
        user.setCookingFrequency(cookingFrequencyChoiceBox.getSelectionModel().getSelectedItem());
        user.setSize(getSize());
        user.setWeight(getWeight());

        if(passwordField.getText().length() != 0)
            user.setPassword(passwordField.getText());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        nameTexfield.setText(User.actualUser.getName());
        firstnameTexfield.setText(User.actualUser.getFirstName());
        birthDatePicker.setValue(User.actualUser.getBirthDate());
        mailTexfield.setText(User.actualUser.getMail());
        System.out.println(User.actualUser.getSex());
        if(User.actualUser.getSex().equals(UserSex.FEMALE)){
            sexF.setSelected(true);
        }else{
            sexM.setSelected(true);
        }

        if(User.actualUser.getSize() != -1)
            sizeTextfield.setText(String.valueOf(User.actualUser.getSize()));

        if(User.actualUser.getWeight() != -1)
            weightTexfield.setText(String.valueOf(User.actualUser.getWeight()));

        statusChoicebox.getSelectionModel().select(User.actualUser.getStatus());
        cookingFrequencyChoiceBox.getSelectionModel().select(User.actualUser.getCookingFrequency());

    }
}
