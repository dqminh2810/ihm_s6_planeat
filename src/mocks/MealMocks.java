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

        ArrayList<Dish> dish = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(FoodMocks.foods.get(0), 6));
        dish.add(new Dish("Omelette des familles", "A ne pas retourner", CourseType.MAIN_COURSE, ingredients, null));
        dish.add(new Dish("Yaourt aux fruits", "Perles de lait et mon visage s'est transformé", CourseType.DESSERT, new ArrayList<>(), null));

        ArrayList<Dish> dish2 = new ArrayList<>();
        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(new Ingredient(FoodMocks.foods.get(1), 400));
        ingredients2.add(new Ingredient(FoodMocks.foods.get(3), 250));
        dish2.add(new Dish("Pates au poulet", "miam :)", CourseType.MAIN_COURSE, ingredients2, null));
        ArrayList<Ingredient> ingredients3 = new ArrayList<>();
        ingredients3.add(new Ingredient(FoodMocks.foods.get(5), 200));
        dish2.add(new Dish("poivrons","scrounch", CourseType.STARTER, ingredients3, null));

        dish2.add(new Dish("Yaourt aux fruits", "Perles de lait et mon visage s'est transformé", CourseType.DESSERT, new ArrayList<>(), null));

        meals.add(new Meal("Omelette", dish));
        meals.add(new Meal("Pates au poulet et poivrons", dish2));
    }
}
