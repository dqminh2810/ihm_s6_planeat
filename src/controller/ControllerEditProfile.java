package controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mocks.UserMocks;
import model.Theme;
import model.User;
import model.UserSex;
import view.ViewBase;
import view.ViewEditProfile;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEditProfile extends ControllerUser {
    @FXML
    private Text editedText;
    @FXML
    private RadioButton darkRadio;
    @FXML
    private RadioButton lightRadio;
    private ToggleGroup themeGroup;


    public ControllerEditProfile(Stage stage, Controller previousController) {
        super(stage, previousController);
        this.actualView = new ViewEditProfile();
    }

    @Override
    protected void sendForm() {
        editedText.setText(null);
        errorText.setText(null);
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
        user.setThemeCss((Theme) themeGroup.getSelectedToggle().getUserData());

        if(passwordField.getText().length() != 0)
            user.setPassword(passwordField.getText());

        editedText.setText("Profil édité avec succès");
    }

    @Override
    boolean checkMailAlreadyUsed(){
        String mail = mailTexfield.getText();
        if(!mail.equals(User.getActualUser().getMail()) && UserMocks.users.containsKey(mail)){
            errorText.setText("Cet email existe déjà");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        initClickOnTheme();
        nameTexfield.setText(User.actualUser.getName());
        firstnameTexfield.setText(User.actualUser.getFirstName());
        birthDatePicker.setValue(User.actualUser.getBirthDate());
        mailTexfield.setText(User.actualUser.getMail());
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

    private void initClickOnTheme(){
        themeGroup = new ToggleGroup();
        darkRadio.setToggleGroup(themeGroup);
        darkRadio.setUserData(Theme.DARK);
        lightRadio.setToggleGroup(themeGroup);
        lightRadio.setUserData(Theme.LIGHT);
        if(User.actualUser.getThemeCss().equals(Theme.LIGHT))
            lightRadio.setSelected(true);
        else
            darkRadio.setSelected(true);
    }
}
