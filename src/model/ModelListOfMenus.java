package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mocks.MealMocks;

public class ModelListOfMenus {
    private ObservableList<Meal> listOfMenusForListView;
    private ObservableList<Meal> listOfMenusForTableView;

    public ModelListOfMenus() {
        listOfMenusForListView = FXCollections.observableArrayList();
        listOfMenusForTableView = FXCollections.observableArrayList();
        listOfMenusForListView.addAll(MealMocks.meals);
    }

    public void add(Meal meal) {
        listOfMenusForListView.add(meal);
    }

    public ObservableList<Meal> getListOfMenusForListView() {
        return listOfMenusForListView;
    }

    public ObservableList<Meal> getListOfMenusForTableView() {
        return listOfMenusForTableView;
    }
}
