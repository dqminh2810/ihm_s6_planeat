package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Repas {
    private ArrayList<Dish> dishes;
    private LocalDateTime time;

    public Repas(ArrayList<Dish> dishes, LocalDateTime time){
        this.dishes = dishes;
        this.time = time;
    }


}
