package controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CourseType;
import model.Dish;
import model.Meal;
import model.ModelListOfDishes;
import view.ViewBase;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerAddMeal extends Controller{
    private ModelListOfDishes modelListOfDishes = null;
    private ControllerGestionMenu controllerGestionMenu;
    private Meal mealSelected = null;


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
    private TableColumn<Dish, Dish> actionTableColumn;

    //private ControllerGestionMenu gestionMenuController;
    public ControllerAddMeal(Stage stage, Controller previousController, ViewBase actualView, Meal mealSelected) {
        super(stage, previousController, actualView);
        //this.gestionMenuController = gestionMenuController;
        this.mealSelected = mealSelected;
        modelListOfDishes = new ModelListOfDishes();
        menuNameTextField = new TextField();
        controllerGestionMenu = (ControllerGestionMenu)previousController;
    }


    //INIT
    public void initialize(URL location, ResourceBundle resources) {
        if(mealSelected!=null)
            menuNameTextField.setText(mealSelected.getName());

        initTextField();
        initChoiceBox();
        initTableView();

        //Handle Button event
        pickButton.setOnAction(event -> pickButtonEvent());
        deleteAllButton.setOnAction(event -> deleteAllButtonEvent());
        saveandexitButton.setOnAction(event -> saveAndExitButtonEvent());
    }
    //init TextField menuName
    public void initTextField() {
        menuNameTextField.setEditable(true);
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
        actionTableColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        actionTableColumn.setCellFactory(param -> new TableCell<Dish, Dish>(){
            private final Button deleteButton = new Button("delete");
            private final HBox pane = new HBox(deleteButton);

            @Override
            protected void updateItem(Dish item, boolean empty) {
                super.updateItem(item, empty);
                deleteButton.setOnAction(event -> {
                    deleteButtonEvent(item);
                });
                setGraphic(empty ? null : pane);
            }
        });

        dishTableView.getColumns().addAll(typeTableColumn,nameTableColumn,actionTableColumn);
        sortListOfDishes();
    }


    //GETTER
    public ModelListOfDishes getModelListOfDishes() {
        return modelListOfDishes;
    }
    public ObservableList<Dish> getListOfStarters(){ return modelListOfDishes.getStarters(); }
    public ObservableList<Dish> getListOfMaincourses(){ return modelListOfDishes.getMaincourses(); }
    public ObservableList<Dish> getListOfDesserts(){ return modelListOfDishes.getDesserts(); }
    public ObservableList<Dish>  getListOfDishes(){ return modelListOfDishes.getListOfDishes(); }
    public TextField getMenuNameTextField() { return menuNameTextField; }


    //ACTION EVENT
    public void pickButtonEvent(){
        linkChoiceBoxToTableView();
    }
    public void deleteAllButtonEvent() {
        //Update TableView
        dishTableView.getItems().clear();
    }
    public void saveAndExitButtonEvent() {
        //Update Menu Name
        if(checkMenuName()){
            if(mealSelected == null){      //Create new menu
                String menuName = menuNameTextField.getText();
                ArrayList<Dish> newMeal = new ArrayList<>(getListOfDishes());
                controllerGestionMenu.getListOfMenusForListView().add(new Meal(menuName, newMeal));
            }else{      //Update old menu
                String menuName = menuNameTextField.getText();
                ArrayList<Meal> tmp = new ArrayList<>(controllerGestionMenu.getListOfMenusForListView());
                ArrayList<Dish> updateMeal = new ArrayList<>(getListOfDishes());
                System.out.println("Before: "+controllerGestionMenu.getListOfMenusForTableView().get(0).getDishes());
                for(Meal m: controllerGestionMenu.getListOfMenusForListView()){
                    if(m==mealSelected){
                        m.setName(menuName);
                        m.getDishes().clear();
                        m.setDishes(updateMeal);
                        tmp.remove(m);
                    }
                }
                System.out.println("After: "+controllerGestionMenu.getListOfMenusForTableView().get(0).getDishes());

                /*
                 * Trick -> this is not really updated, I deleted the meal list
                 * then create new temporary meal list without the meal selected,
                 * it's added after the new one created
                 */
                controllerGestionMenu.getListOfMenusForListView().clear();
                controllerGestionMenu.getListOfMenusForListView().addAll(tmp);
                controllerGestionMenu.getListOfMenusForListView().add(new Meal(menuName, updateMeal));
                controllerGestionMenu.getMealTableView().refresh();

            }
            getStage().close();
        }
    }
    public  void deleteButtonEvent(Dish dish) {
        dishTableView.getItems().remove(dish);
    }

    //Link choiceBox to tableView
    public void linkChoiceBoxToTableView(){
        Dish starter = startersChoiceBox.getValue();
        Dish maincourse = maincoursesChoiceBox.getValue();
        Dish dessert = dessertsChoiceBox.getValue();
        //Update TableView
        dishTableView.getItems().add(starter);
        dishTableView.getItems().add(maincourse);
        dishTableView.getItems().add(dessert);
    }

    public void sortListOfDishes(){
        ArrayList<Dish> tmpList = new ArrayList<>();
        for(Dish d: getListOfDishes()){
            if(d.getCourseType()== CourseType.STARTER)
                tmpList.add(d);
        }
        for(Dish d: getListOfDishes()){
            if(d.getCourseType()== CourseType.MAIN_COURSE)
                tmpList.add(d);
        }
        for(Dish d: getListOfDishes()){
            if(d.getCourseType()== CourseType.DESSERT)
                tmpList.add(d);
        }
        getListOfDishes().clear();
        getListOfDishes().addAll(tmpList);
    }
    //Check if menu name is empty
    public boolean checkMenuName(){
        if(menuNameTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You forget something!!");
            alert.setHeaderText("Message");
            alert.setContentText("You have to fill the menu name!!!");

            alert.showAndWait();
            return false;
        }
        return true;
    }
}
