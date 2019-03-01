package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Dish;
import model.Meal;
import model.ModelListOfDishes;
import view.ViewBase;

import java.util.ArrayList;

public class ControllerPopupAjoutPlats extends Controller{
    private ModelListOfDishes modelListOfDishes = null;
    private ControllerGestionMenu controllerGestionMenu;

    @FXML
    private TextField menuNameTextField;
    @FXML
    private Button pickButton;
    @FXML
    private Button saveandexitButton;
    @FXML
    private Button deleteAllButton;
    @FXML
    private ChoiceBox<Dish> startersChoiceBox;
    @FXML
    private ChoiceBox<Dish> maincoursesChoiceBox;
    @FXML
    private ChoiceBox<Dish> dessertsChoiceBox;
    @FXML
    private TableView<Dish> dishTableView;
    @FXML
    private TableColumn<Dish, Dish> typeTableColumn;
    @FXML
    private TableColumn<Dish, Dish> nameTableColumn;
    @FXML
    private TableColumn<Dish, Button> actionTableColumn;

    //private ControllerGestionMenu gestionMenuController;

    public ControllerPopupAjoutPlats(Stage stage, Controller previousController, ViewBase actualView) {
        super(stage, previousController, actualView);
        //this.gestionMenuController = gestionMenuController;
        modelListOfDishes = new ModelListOfDishes();
        controllerGestionMenu = (ControllerGestionMenu)previousController;
    }

    public void init(){
        initChoiceBox();
        initTableView();
        //linkChoiceBoxToTableView();

        //Handle Button event
        pickButton.setOnAction(event -> pickButtonEvent());
        deleteAllButton.setOnAction(event -> deleteAllButtonEvent());
        saveandexitButton.setOnAction(event -> saveAndExitButtonEvent());
    }

    //GETTER
    public ModelListOfDishes getModelListOfDishes() {
        return modelListOfDishes;
    }
    public ObservableList<Dish> getListOfStarters(){ return modelListOfDishes.getStarters(); }
    public ObservableList<Dish> getListOfMaincourses(){ return modelListOfDishes.getMaincourses(); }
    public ObservableList<Dish> getListOfDesserts(){ return modelListOfDishes.getDesserts(); }
    public ObservableList<Dish>  getListOfDishes(){ return modelListOfDishes.getListOfDishes(); }
    //ACTION EVENT
    public void pickButtonEvent(){
        linkChoiceBoxToTableView();
    }
    public void deleteAllButtonEvent() {
        System.out.println(getListOfDishes());
        //Update TableView
        dishTableView.getItems().clear();
        System.out.println(getListOfDishes());

    }
    public void saveAndExitButtonEvent() {
        //Update Menu Name
        String menuName = menuNameTextField.getText();
        ArrayList<Dish> newMeal = new ArrayList<>(getListOfDishes());
        controllerGestionMenu.getListOfMenus().add(new Meal(menuName, newMeal));
        getStage().close();
    }
    //init TextField menuName
    public void initTextField() {
        //TODO
    }
    //init Choice Box
    public void initChoiceBox(){
        startersChoiceBox.setItems(modelListOfDishes.getStarters());
        maincoursesChoiceBox.setItems(modelListOfDishes.getMaincourses());
        dessertsChoiceBox.setItems(modelListOfDishes.getDesserts());

        startersChoiceBox.getSelectionModel().select(0);
        maincoursesChoiceBox.getSelectionModel().select(0);
        dessertsChoiceBox.getSelectionModel().select(0);
    }
    //init Table View
    public void initTableView(){
        dishTableView.setItems(getListOfDishes());
        dishTableView.getColumns().clear();

        typeTableColumn.setCellValueFactory(new PropertyValueFactory<Dish, Dish>("courseType"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Dish, Dish>("name"));
        //actionTableColumn.setCellValueFactory(new PropertyValueFactory<Dish, Button>("deleteButton"));

        dishTableView.getColumns().addAll(typeTableColumn,nameTableColumn);
    }
    //link choiceBox to tableView
    public void linkChoiceBoxToTableView(){
        Dish starter = startersChoiceBox.getValue();
        Dish maincourse = maincoursesChoiceBox.getValue();
        Dish dessert = dessertsChoiceBox.getValue();
        //Update TableView
        dishTableView.getItems().add(starter);
        dishTableView.getItems().add(maincourse);
        dishTableView.getItems().add(dessert);
    }
}
