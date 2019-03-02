package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mocks.UserMocks;
import model.User;
import view.ViewAccueil;
import view.ViewBase;
import view.ViewLancement;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerConnexion extends Controller {
    @FXML
    private TextField emailTextfield;
    @FXML
    private TextField passwordTextfield;
    @FXML
    private Button returnButton;
    @FXML
    private Button connexionButton;
    @FXML
    private Text errorText;

    public ControllerConnexion(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage,previousController, actualView);
    }

    public void init(){
        clickOnReturnButton(returnButton);
        connexionButton.setOnAction(event -> connexion());
    }

    private void connexion(){
        String email = emailTextfield.getText();
        String password = passwordTextfield.getText();

        if(UserMocks.users.containsKey(email)){
            User user = UserMocks.users.get(email);
            if (password.equals(user.getPassword())){
                User.actualUser = user;
                setView(new ControllerAccueil(getStage(), this, new ViewAccueil()));
            }
            else{
                errorText.setText("Mot de passe incorrect");
            }
        }
        else{
            errorText.setText("Email inconnu");
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
