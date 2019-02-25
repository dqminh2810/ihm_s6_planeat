package mocks;

import model.CourseType;
import model.Dish;
import model.Ingredient;
import model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MenuMocks {
    public static ArrayList<Meal> meals;

    public static void initMocks(){
        meals = new ArrayList<>();

        ArrayList<Dish> dish = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(FoodMocks.foods.get(0), 6));
        dish.add(new Dish("Omelette des familles", "A ne pas retourner", CourseType.MAIN_COURSE, ingredients, null));

        ArrayList<Dish> dish2 = new ArrayList<>();
        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(new Ingredient(FoodMocks.foods.get(1), 400));
        ingredients2.add(new Ingredient(FoodMocks.foods.get(3), 250));
        dish2.add(new Dish("Pates au poulet", "miam :)", CourseType.MAIN_COURSE, ingredients2, null));
        ArrayList<Ingredient> ingredients3 = new ArrayList<>();
        ingredients3.add(new Ingredient(FoodMocks.foods.get(5), 200));
        dish2.add(new Dish("poivrons","scrounch", CourseType.STARTER, ingredients3, null));

        meals.add(new Meal("Omelette", dish, LocalDateTime.of(2019, 2, 25, 12, 30)));
        meals.add(new Meal("Pates au poulet et poivrons", dish2, LocalDateTime.of(2019, 2, 25, 19, 30)));

        meals.add(new Meal("Omelette", dish, LocalDateTime.of(2019, 2, 26, 12, 30)));
        meals.add(new Meal("Pates au poulet et poivrons", dish2, LocalDateTime.of(2019, 2, 26, 19, 30)));

        meals.add(new Meal("Omelette", dish, LocalDateTime.of(2019, 2, 27, 12, 30)));
        meals.add(new Meal("Pates au poulet et poivrons", dish2, LocalDateTime.of(2019, 2, 27, 19, 30)));

        meals.add(new Meal("Omelette", dish, LocalDateTime.of(2019, 2, 28, 12, 30)));
        meals.add(new Meal("Pates au poulet et poivrons", dish2, LocalDateTime.of(2019, 2, 28, 19, 30)));

        meals.add(new Meal("Omelette", dish, LocalDateTime.of(2019, 3, 28, 12, 30)));
        meals.add(new Meal("Pates au poulet et poivrons", dish2, LocalDateTime.of(2019, 3, 28, 19, 30)));
    }
}
