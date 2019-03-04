package mocks;

import model.CourseType;
import model.Dish;
import model.Ingredient;

import java.util.ArrayList;

public abstract class DishMocks {
    public static ArrayList<Dish> dishes;
    public static ArrayList<Dish> starters;
    public static ArrayList<Dish> maincourses;
    public static ArrayList<Dish> desserts;

    public static void initMocks(){
        dishes = new ArrayList<>();
        starters = new ArrayList<>();
        maincourses = new ArrayList<>();
        desserts = new ArrayList<>();

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ArrayList<Ingredient> ingredients3 = new ArrayList<>();

        //Ingredient
        ingredients.add(new Ingredient(FoodMocks.foods.get(0), 6));
        ingredients2.add(new Ingredient(FoodMocks.foods.get(1), 400));
        ingredients2.add(new Ingredient(FoodMocks.foods.get(3), 250));
        ingredients3.add(new Ingredient(FoodMocks.foods.get(5), 200));

        //Starters
        starters.add(new Dish("poivrons","scrounch", CourseType.STARTER, ingredients3));

        //Maincourses
        maincourses.add(new Dish("Omelette des familles", "A ne pas retourner", CourseType.MAIN_COURSE, ingredients));
        maincourses.add(new Dish("Pates au poulet", "miam :)", CourseType.MAIN_COURSE, ingredients2));

        //Desserts
        desserts.add(new Dish("Yaourt aux fruits", "Perles de lait et mon visage s'est transformé", CourseType.DESSERT, null));

        //All Dishes
        dishes.addAll(starters);
        dishes.addAll(maincourses);
        dishes.addAll(desserts);
    }
}
