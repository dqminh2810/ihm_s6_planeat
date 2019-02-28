package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import mocks.FoodMocks;
import mocks.FoodMocks;
import mocks.MealMocks;
import model.*;
import view.ViewBase;
import view.ViewGestionMenu;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ListView;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;


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
        configListView();
        configTableView();
        linkListViewToTableView();
    }

    //ACTION EVENT
    public void addButtonEvent(){
        //TODO
    }
    public void agendaButtonEvent(){
        //TODO
    }
    public void returnButtonEvent(){
        //TODO
    }
    public void displayMealSelected(MouseEvent event){
        //TODO
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

    //Configure listView
    public void configListView(){
        MealListView.setItems(getListOfMenus());

        //rename listcell to Meal name
        MealListView.setCellFactory(new Callback<ListView<Meal>, ListCell<Meal>>() {
            @Override
            public ListCell<Meal> call(ListView<Meal> param) {
                ListCell<Meal> cell = new ListCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(null);
                        if(item != null){
                            setText(((Meal)item).getName());
                            setTooltip(new Tooltip(((Meal)item).getName()));
                        }
                    }
                };
                return cell;
            }
        });
    }
    //Configure tableView
    public void configTableView(){
        MealTableView.setItems(getListOfDishes());

        starterTableColumn.setCellValueFactory(new PropertyValueFactory<Meal, ArrayList<Dish>>("starter"));
        maincourseTableColumn.setCellValueFactory(new PropertyValueFactory<Meal, ArrayList<Dish>>("maincourse"));
        dessertTableColumn.setCellValueFactory(new PropertyValueFactory<Meal, ArrayList<Dish>>("dessert"));

        //Rename tablecolumncell to dish name
        starterTableColumn.setCellFactory(new Callback<TableColumn<Meal,  ArrayList<Dish>>, TableCell<Meal, ArrayList<Dish>>>() {
            @Override
            public TableCell<Meal, ArrayList<Dish>> call(TableColumn<Meal,  ArrayList<Dish>> param) {
                TableCell<Meal,  ArrayList<Dish>> cell = new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(null);
                        if(item != null){
                            for(Dish d: (ArrayList<Dish>) item){
                                setText(d.getName());
                                setTooltip(new Tooltip(d.getName()));
                            }
                        }
                    }
                };
                return cell;
            }
        });
        maincourseTableColumn.setCellFactory(new Callback<TableColumn<Meal,  ArrayList<Dish>>, TableCell<Meal, ArrayList<Dish>>>() {
            @Override
            public TableCell<Meal, ArrayList<Dish>> call(TableColumn<Meal,  ArrayList<Dish>> param) {
                TableCell<Meal,  ArrayList<Dish>> cell = new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(null);
                        if(item != null){
                            ArrayList<Dish> starter = (ArrayList<Dish>) item;
                            for(Dish d: (ArrayList<Dish>) item){
                                setText(d.getName());
                                setTooltip(new Tooltip(d.getName()));
                            }
                        }
                    }
                };
                return cell;
            }
        });
        dessertTableColumn.setCellFactory(new Callback<TableColumn<Meal,  ArrayList<Dish>>, TableCell<Meal, ArrayList<Dish>>>() {
            @Override
            public TableCell<Meal, ArrayList<Dish>> call(TableColumn<Meal,  ArrayList<Dish>> param) {
                TableCell<Meal,  ArrayList<Dish>> cell = new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(null);
                        if(item != null){
                            ArrayList<Dish> starter = (ArrayList<Dish>) item;
                            for(Dish d: (ArrayList<Dish>) item){
                                setText(d.getName());
                                setTooltip(new Tooltip(d.getName()));
                            }
                        }
                    }
                };
                return cell;
            }
        });
    }

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
}
