package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Meal {
    private String name;
    private ArrayList<Dish> dishes;
    private LocalDateTime time;

    public Meal(String name, ArrayList<Dish> dishes, LocalDateTime time){
        this.name = name;
        this.dishes = dishes;
        this.time = time;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append("\n");
        builder.append(time.getHour()).append(" : ").append(time.getMinute());

        return builder.toString();
    }
}
