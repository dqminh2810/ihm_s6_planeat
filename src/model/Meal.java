package model;

import java.util.ArrayList;

/**
 * Un meal est un repas, il contient un nom et une liste de plats
 * @see Dish
 */

public class Meal {
    private String name;
    private ArrayList<Dish> dishes;

    public Meal(String name, ArrayList<Dish> dishes){
        this.name = name;
        this.dishes = dishes;
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

    @Override
    public String toString() {
        return name;
    }
}
