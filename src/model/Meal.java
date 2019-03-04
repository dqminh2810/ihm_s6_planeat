package model;

import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * Un meal est un repas, il contient un nom et une liste de plats
 * @see Dish
 */

public class Meal {
    private String name;
    private ArrayList<Dish> dishes;
    private ArrayList<Dish> starter;
    private ArrayList<Dish> maincourse;
    private ArrayList<Dish> dessert;
    private Button deleteButton;
    private Button modifyButton;

    public Meal(String name, ArrayList<Dish> dishes){
        this.name = name;
        this.dishes = dishes;
        starter = new ArrayList<>();
        maincourse = new ArrayList<>();
        dessert = new ArrayList<>();
        this.getStarter();
        this.getMaincourse();
        this.getDessert();
        deleteButton = new Button("Delete");
        modifyButton = new Button("Modify");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    public ArrayList<Dish> getStarter() {
        for(Dish d: this.dishes){
            if(d.getCourseType()==CourseType.STARTER)
                if(!this.starter.contains(d))
                    this.starter.add(d);
        }
        return this.starter;
    }

    public ArrayList<Dish> getMaincourse() {
        for(Dish d: this.dishes){
            if(d.getCourseType()==CourseType.MAIN_COURSE)
                if(!this.maincourse.contains(d))
                    this.maincourse.add(d);
        }
        return this.maincourse;
    }

    public ArrayList<Dish> getDessert() {
        for(Dish d: this.dishes){
            if(d.getCourseType()==CourseType.DESSERT)
                if(!this.dessert.contains(d))
                    this.dessert.add(d);
        }
        return this.dessert;
    }

    public boolean setStarter(Dish starter) {
        return this.starter.add(starter);
    }

    public boolean setMaincourse(Dish maincourse) {
        return this.maincourse.add(maincourse);
    }

    public boolean setDessert(Dish dessert) {
        return this.dessert.add(dessert);
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public Button getModifyButton() {
        return modifyButton;
    }

    public void setModifyButton(Button modifyButton) {
        this.modifyButton = modifyButton;
    }

    @Override
    public String toString() {
        return name;
    }

    public double getIntakes(int intakeIndex) {
        double sum = 0;
        for (Dish dish : dishes) {
            sum += dish.getIntakes(intakeIndex);
        }
        return sum;
    }
}
