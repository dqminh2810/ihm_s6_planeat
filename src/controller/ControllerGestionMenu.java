package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Dish;
import view.ViewBase;
import view.ViewGestionMenu;

import javax.swing.text.TableView;
import javax.swing.text.html.ListView;
import java.awt.event.MouseEvent;


public class ControllerGestionMenu extends Controller{
    @FXML
    private Button returnButton;
    @FXML
    private Button addButton;
    @FXML
    private Button agendaButton;
    @FXML
    private ListView menuListView;
    @FXML
    private TableView menuTableView;

    public ControllerGestionMenu(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage, previousController, actualView);
    }
    public ListView getMenuListView(){
        return menuListView;
    }
    public TableView getMenuTableView(){
        return menuTableView;
    }
    public Dish getStarter(){
        //TODO
        return null;
    }
    public Dish getMainCourse(){
        //TODO
        return null;
    }
    public Dish getDessert(){
        //TODO
        return null;
    }
    @Override
    void init() {

    }
    public void init(ViewGestionMenu view){
        addButton.setOnAction(event -> addButtonEvent());
        agendaButton.setOnAction(event -> agendaButtonEvent());
        returnButton.setOnAction(event -> returnButtonEvent());
    }

    public void addButtonEvent(){
        //TODO
    }
    public void agendaButtonEvent(){
        //TODO
    }
    public void returnButtonEvent(){
        //TODO
    }
    public void displayMenuSelected(MouseEvent event){
        //TODO
    }
}
