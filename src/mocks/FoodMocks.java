package mocks;

import model.Food;
import model.FoodCategory;

import java.util.ArrayList;

public abstract class FoodMocks {
    public static ArrayList<Food> foods;

    public static void initMocks(){
        foods = new ArrayList<>();

        foods.add(new Food("Oeuf", FoodCategory.ANIMAL_ORIGIN, 630, 10, 20, 30, 20, 20, 10, false, false, null));
        foods.add(new Food("Pates", FoodCategory.OTHER, 80, 10, 20, 30, 10, 20, 10, false, true, null));
        foods.add(new Food("Pates No Gluten", FoodCategory.OTHER, 80, 10, 20, 30, 10, 20, 10, false, false, null));
        foods.add(new Food("Poulet", FoodCategory.WHITE_MEAT, 800, 10, 20, 30, 10, 200, 10, false, true, null));
        foods.add(new Food("Steak", FoodCategory.RED_MEAT, 800, 10, 20, 30, 10, 200, 10, false, false, null));
        foods.add(new Food("Pepper", FoodCategory.VEGETABLE, 200, 10, 20, 30, 10, 200, 10, false, false, null));

    }
}
