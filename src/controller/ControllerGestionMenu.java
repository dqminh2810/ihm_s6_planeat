package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.*;
import view.ViewAgenda;
import view.ViewBase;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ListView;
import view.ViewPopupAjoutPlats;

import java.util.ArrayList;


public class ControllerGestionMenu extends Controller {

    ModelListOfMenus modelListOfMenus = null;

    @FXML
    private Button returnButton;
    @FXML
    private Button addButton;
    @FXML
    private Button agendaButton;
    @FXML
    private ListView<Meal> MealListView;
    @FXML
    private TableView<Meal> MealTableView;
    @FXML
    private TableColumn<Meal, ArrayList<Dish>> starterTableColumn;
    @FXML
    private TableColumn<Meal,  ArrayList<Dish>> maincourseTableColumn;
    @FXML
    private TableColumn<Meal,  ArrayList<Dish>> dessertTableColumn;

    //Constructor
    public ControllerGestionMenu(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage, previousController, actualView);
        modelListOfMenus = new ModelListOfMenus();
    }

    @Override
    public void init() {
        initListView();
        initTableView();
        linkListViewToTableView();

        //Handle Button event
        addButton.setOnAction(event -> addButtonEvent());
        returnButton.setOnAction(event -> returnButtonEvent());
        agendaButton.setOnAction(event -> agendaButtonEvent());
    }

    //ACTION EVENT
    public void addButtonEvent(){
        try{
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            ViewBase view = new ViewPopupAjoutPlats();
            Controller controller = new ControllerPopupAjoutPlats(stage,this,view);
            controller.setView(controller);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void agendaButtonEvent(){
        try{
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            ViewBase view = new ViewAgenda();
            Controller controller = new ControllerAgenda(stage,null,view);
            this.setView(controller);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void returnButtonEvent(){
        this.getPreviousController().setView(this.getPreviousController());
    }

    //GETTER
    public ListView getMealListView(){
        return MealListView;
    }
    public TableView getMealTableView(){
        return MealTableView;
    }
    public ObservableList<Meal> getListOfMenus(){ return modelListOfMenus.getListOfMenus(); }
    public ObservableList<Meal> getListOfDishes(){ return modelListOfMenus.getListOfDish(); }

    //init listView
    public void initListView(){
        MealListView.setItems(getListOfMenus());
    }
    //init tableView
    public void initTableView(){
        MealTableView.setItems(getListOfDishes());
        MealTableView.getItems().clear();

        starterTableColumn.setCellValueFactory(new PropertyValueFactory<Meal, ArrayList<Dish>>("starter"));
        maincourseTableColumn.setCellValueFactory(new PropertyValueFactory<Meal, ArrayList<Dish>>("maincourse"));
        dessertTableColumn.setCellValueFactory(new PropertyValueFactory<Meal, ArrayList<Dish>>("dessert"));
    }
    //Link Listview item to tableview
    public void linkListViewToTableView(){
        MealListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue,
                         newValue) -> {
                            if (observable != null && observable.getValue() != null) {
                                getListOfDishes().clear();
                                getListOfDishes().addAll(observable.getValue());
                            }
                        });
    }

    public ControllerGestionMenu getPreviousController() {
        return this;
    }
}
