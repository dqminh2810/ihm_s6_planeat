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
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ArrayList<Ingredient> ingredients3 = new ArrayList<>();

        //Ingredients
        ingredients.add(new Ingredient(FoodMocks.foods.get(0), 6));
        ingredients2.add(new Ingredient(FoodMocks.foods.get(1), 4));
        ingredients2.add(new Ingredient(FoodMocks.foods.get(3), 2));
        ingredients3.add(new Ingredient(FoodMocks.foods.get(5), 2));

        //Dishes
        dishes1.add(new Dish("Omelette des familles", "A ne pas retourner", CourseType.MAIN_COURSE, ingredients, null));
        dishes1.add(new Dish("Omelette des familles 2", "A ne pas retourner", CourseType.MAIN_COURSE, ingredients, null));
        dishes1.add(new Dish("Yaourt aux fruits", "Perles de lait et mon visage s'est transformé", CourseType.DESSERT, new ArrayList<>(), null));
        dishes2.add(new Dish("Pates au poulet", "miam :)", CourseType.MAIN_COURSE, ingredients2, null));
        dishes2.add(new Dish("poivrons","scrounch", CourseType.STARTER, ingredients3, null));
        dishes2.add(new Dish("Yaourt aux fruits", "Perles de lait et mon visage s'est transformé", CourseType.DESSERT, new ArrayList<>(), null));

        //Meals
        meals.add(new Meal("Omelette", dishes1));
        meals.add(new Meal("Pates au poulet et poivrons", dishes2));
    }
}
