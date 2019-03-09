package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mocks.DishMocks;


public class ModelListOfDishes {
    private ObservableList<Dish> starters;
    private ObservableList<Dish> maincourses;
    private ObservableList<Dish> desserts;
    private ObservableList<Dish> listOfDishes;


    public ModelListOfDishes(){
        starters = FXCollections.observableArrayList();
        maincourses = FXCollections.observableArrayList();
        desserts = FXCollections.observableArrayList();
        listOfDishes = FXCollections.observableArrayList();

        listOfDishes.addAll(DishMocks.dishes);
        for(Dish d: listOfDishes){
            if(d.getCourseType()==CourseType.STARTER)
                if(!this.starters.contains(d))
                    this.starters.add(d);
        }
        for(Dish d: listOfDishes){
            if(d.getCourseType()==CourseType.MAIN_COURSE)
                if(!this.maincourses.contains(d))
                    this.maincourses.add(d);
        }
        for(Dish d: listOfDishes){
            if(d.getCourseType()==CourseType.DESSERT)
                if(!this.desserts.contains(d))
                    this.desserts.add(d);
        }
    }
    public ObservableList<Dish> getStarters() {
        return starters;
    }

    public void setStarters(ObservableList<Dish> starters) {
        this.starters = starters;
    }

    public ObservableList<Dish> getMaincourses() {
        return maincourses;
    }

    public void setMaincourse(ObservableList<Dish> maincourse) {
        this.maincourses = maincourse;
    }

    public ObservableList<Dish> getDesserts() {
        return desserts;
    }

    public void setDesserts(ObservableList<Dish> desserts) {
        this.desserts = desserts;
    }

    public ObservableList<Dish> getListOfDishes() {
        return listOfDishes;
    }
}
