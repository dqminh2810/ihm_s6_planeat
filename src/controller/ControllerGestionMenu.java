package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
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
    private ModelListOfMenus modelListOfMenus;

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
    private TableColumn<Meal, String> starterTableColumn;
    @FXML
    private TableColumn<Meal, String> maincourseTableColumn;
    @FXML
    private TableColumn<Meal,  String> dessertTableColumn;
    @FXML
    private TableColumn<Meal, Meal> actionTableColumn;

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

        addButton.setOnAction(event -> addButtonEvent());
        clickOnReturnButton(returnButton);
        agendaButton.setOnAction(event -> agendaButtonEvent());
    }

    public void initListView(){
        MealListView.setItems(getListOfMenusForListView());
    }

    public void initTableView(){
        MealTableView.setItems(getListOfMenusForTableView());
        MealTableView.getItems().clear();

        starterTableColumn.setCellValueFactory(new PropertyValueFactory<>("starter"));
        maincourseTableColumn.setCellValueFactory(new PropertyValueFactory<>("maincourse"));
        dessertTableColumn.setCellValueFactory(new PropertyValueFactory<>("dessert"));
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

        //Rename tablecolumncell to dish name
        starterTableColumn.setCellFactory(new Callback<TableColumn<Meal,  String>, TableCell<Meal, String>>() {
            @Override
            public TableCell<Meal, String> call(TableColumn<Meal, String> param) {
                TableCell<Meal, String> cell = new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(null);
                        if(item != null){
                            String res="";
                            for(Dish d: (ArrayList<Dish>) item){
                                res+=d.getName();
                                res+="\n";
                            }
                            setText(res);
                            setTooltip(new Tooltip(res));
                        }
                    }
                };
                return cell;
            }
        });
        maincourseTableColumn.setCellFactory(new Callback<TableColumn<Meal,  String>, TableCell<Meal, String>>() {
            @Override
            public TableCell<Meal, String> call(TableColumn<Meal, String> param) {
                TableCell<Meal, String> cell = new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(null);
                        if(item != null){
                            String res="";
                            for(Dish d: (ArrayList<Dish>) item){
                                res+=d.getName();
                                res+="\n";
                            }
                            setText(res);
                            setTooltip(new Tooltip(res));

                        }
                    }
                };
                return cell;
            }
        });
        dessertTableColumn.setCellFactory(new Callback<TableColumn<Meal,  String>, TableCell<Meal, String>>() {
            @Override
            public TableCell<Meal, String> call(TableColumn<Meal, String> param) {
                TableCell<Meal, String> cell = new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(null);
                        if(item != null){
                            String res="";
                            for(Dish d: (ArrayList<Dish>) item){
                                res+=d.getName();
                                res+="\n";
                            }
                            setText(res);
                            setTooltip(new Tooltip(res));
                        }
                    }
                };
                return cell;
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
            ControllerAddMeal controller = new ControllerAddMeal(stage,this, null);
             controller.getListOfDishes().clear();

            controller.setFirstView(controller);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void agendaButtonEvent(){
        try{
            Controller controller = new ControllerAgenda(getStage(),this);
            this.setView(controller);
        }catch (Exception e){
            e.printStackTrace();
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
            ControllerAddMeal controller = new ControllerAddMeal(stage,this, mealSelected);
            controller.getListOfDishes().clear();
            controller.getListOfDishes().addAll(getListOfDishFromMenu(selectedId));

            controller.setFirstView(controller);
        }catch (Exception e){
            e.printStackTrace();
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
