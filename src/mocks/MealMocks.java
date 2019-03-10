package mocks;

import model.CourseType;
import model.Dish;
import model.Ingredient;
import model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class MealMocks {
    public static ArrayList<Meal> meals;

    public static void initMocks(){
        meals = new ArrayList<>();
        ArrayList<Dish> dishes1 = new ArrayList<>();
        ArrayList<Dish> dishes2 = new ArrayList<>();

        //Dishes
        dishes1.add(DishMocks.dishes.get(0));
        dishes1.add(DishMocks.dishes.get(3));
        dishes2.add(DishMocks.dishes.get(2));
        dishes2.add(DishMocks.dishes.get(1));
        dishes2.add(DishMocks.dishes.get(4));

        //Meals
        meals.add(new Meal("Poivrons Yaourt", dishes1));
        meals.add(new Meal("Omelette pattes", dishes2));
    }
}
