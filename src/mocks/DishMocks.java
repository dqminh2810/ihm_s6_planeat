package mocks;

import model.CourseType;
import model.Dish;
import model.Ingredient;

import java.util.ArrayList;

public abstract class DishMocks {
    public static ArrayList<Dish> dishes;

    public static void initMocks(){
        dishes = new ArrayList<>();

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ArrayList<Ingredient> ingredients3 = new ArrayList<>();

        //Ingredient
        ingredients.add(new Ingredient(FoodMocks.foods.get(0), 360));
        ingredients2.add(new Ingredient(FoodMocks.foods.get(1), 400));
        ingredients2.add(new Ingredient(FoodMocks.foods.get(3), 250));
        ingredients3.add(new Ingredient(FoodMocks.foods.get(5), 200));

        //Dishes
        dishes.add(new Dish("Poivrons","scrounch", CourseType.STARTER, ingredients3));
        dishes.add(new Dish("Omelette", "A ne pas retourner", CourseType.MAIN_COURSE, ingredients));
        dishes.add(new Dish("Pates au poulet", "miam :)", CourseType.MAIN_COURSE, ingredients2));
        dishes.add(new Dish("Yaourt aux fruits", "Perles de lait et mon visage s'est transformé", CourseType.DESSERT, new ArrayList<>()));
        dishes.add(new Dish("Brownie", "De temps en temps on peut se faire plaisir !", CourseType.DESSERT, new ArrayList<>()));
    }
}
