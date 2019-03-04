package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import view.ViewAgenda;
import view.ViewBase;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ListView;
import view.ViewAddMeal;
import view.ViewGestionMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerGestionMenu extends Controller {

    private ModelListOfMenus modelListOfMenus = null;

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
    @FXML
    private TableColumn<Meal, Meal> actionTableColumn;

    //Constructor
    public ControllerGestionMenu(Stage stage, Controller previousController) {
        super(stage, previousController);
        modelListOfMenus = new ModelListOfMenus();
        this.actualView = new ViewGestionMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListView();
        initTableView();
        linkListViewToTableView();

        //Handle Button event
        addButton.setOnAction(event -> addButtonEvent());
        clickOnReturnButton(returnButton);
        agendaButton.setOnAction(event -> agendaButtonEvent());
    }
    //init listView
    public void initListView(){
        MealListView.setItems(getListOfMenusForListView());
    }
    //init tableView
    public void initTableView(){
        MealTableView.setItems(getListOfMenusForTableView());
        MealTableView.getItems().clear();

        starterTableColumn.setCellValueFactory(new PropertyValueFactory<Meal, ArrayList<Dish>>("starter"));
        maincourseTableColumn.setCellValueFactory(new PropertyValueFactory<Meal, ArrayList<Dish>>("maincourse"));
        dessertTableColumn.setCellValueFactory(new PropertyValueFactory<Meal, ArrayList<Dish>>("dessert"));
        actionTableColumn.setCellFactory(param -> new TableCell<Meal, Meal>(){
            private final Button editButton = new Button("edit");
            private final Button deleteButton = new Button("delete");
            private final HBox pane = new HBox(deleteButton, editButton);

            @Override
            protected void updateItem(Meal item, boolean empty) {
                super.updateItem(item, empty);
                final int selectedId = getMealListView().getSelectionModel().getSelectedIndex();
                deleteButton.setOnAction(event -> {
                    deleteButtonEvent(selectedId);
                });

                editButton.setOnAction(event -> {
                    editButtonEvent(selectedId);
                });
                setGraphic(empty ? null : pane);
            }
        });

    }

    //GETTER
    public ListView getMealListView(){
        return MealListView;
    }
    public TableView getMealTableView(){
        return MealTableView;
    }
    public ObservableList<Meal> getListOfMenusForListView(){ return modelListOfMenus.getListOfMenusForListView(); }
    public ObservableList<Meal> getListOfMenusForTableView(){ return modelListOfMenus.getListOfMenusForTableView(); }
    public ControllerGestionMenu getPreviousController() { return this; }

    //ACTION EVENT
    public void addButtonEvent(){
        try{
            Stage stage = new Stage();
            //stage.initStyle(StageStyle.UNDECORATED);
            ViewBase view = new ViewAddMeal();
            Controller controller = new ControllerAddMeal(stage,this, null);
            controller.setView(controller);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void agendaButtonEvent(){
        try{
            ViewBase view = new ViewAgenda();
            Controller controller = new ControllerAgenda(getStage(),this);
            this.setView(controller);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void deleteButtonEvent(int selectedId) {
        if(selectedId!=-1){
            Meal itemRemoved = getListOfMenusForListView().get(selectedId);
            getListOfMenusForTableView().remove(itemRemoved);
            getListOfMenusForListView().remove(itemRemoved);
        }

    }
    public void editButtonEvent(int selectedId){
        try{
            Meal mealSelected = getListOfMenusForListView().get(selectedId);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            ViewBase view = new ViewAddMeal();
            Controller controller = new ControllerAddMeal(stage,this, mealSelected);
            ((ControllerAddMeal) controller).getListOfDishes().addAll(getListOfDishFromMenu(selectedId));

            controller.setView(controller);
        }catch (Exception e){
            System.out.println(e);
        }
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
                                getListOfMenusForTableView().clear();
                                getListOfMenusForTableView().addAll(observable.getValue());
                            }
                        });
    }
    public ArrayList<Dish> getListOfDishFromMenu(int selectedId){
        ArrayList<Dish> listofdish = new ArrayList<>();
        if(selectedId!=-1){
            Meal mealSelected = getListOfMenusForListView().get(selectedId);
            listofdish = mealSelected.getDishes();
        }
        return listofdish;
    }
}
