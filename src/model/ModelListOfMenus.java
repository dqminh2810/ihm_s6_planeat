package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mocks.MealMocks;

public class ModelListOfMenus {
    private ObservableList<Meal> listOfMenus;
    private ObservableList<Meal> listOfDish;

    public ModelListOfMenus() {
        listOfMenus = FXCollections.observableArrayList();
        listOfDish = FXCollections.observableArrayList();
        listOfMenus.addAll(MealMocks.meals);
    }

    public void add(Meal meal) {
        listOfMenus.add(meal);
    }

    public ObservableList<Meal> getListOfMenus() {
        return listOfMenus;
    }

    public ObservableList<Meal> getListOfDish() {
        return listOfDish;
    }
}
