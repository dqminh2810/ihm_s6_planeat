package mocks;

import model.Food;
import model.FoodCategory;

import java.util.ArrayList;

public abstract class FoodMocks {
    public static ArrayList<Food> foods;

    public static void initMocks(){
        foods = new ArrayList<>();

        foods.add(new Food("Oeuf", FoodCategory.ANIMAL_ORIGIN, 145, 10, 4, 0.8, 0.5, 13, 0, 0, 0.04, false, false));
        foods.add(new Food("Pâtes", FoodCategory.OTHER, 151, 0.8, 0.1, 29.7, 0.5, 4.9, 0.5, 2, 0.016,false, true));
        foods.add(new Food("Pâtes Sans Gluten", FoodCategory.OTHER, 151, 0.8, 0.1, 27.3, 0.5, 4.9, 0.5, 1.4, 0.016,false, false));
        foods.add(new Food("Poulet", FoodCategory.WHITE_MEAT, 121, 1.8, 0.6, 0, 0, 26.2, 0.5, 0, 0.013,false, true));
        foods.add(new Food("Steak", FoodCategory.RED_MEAT, 210, 11.7, 4.6, 0, 0, 26.1, 0.1, 0, 0.013,false, false));
        foods.add(new Food("Poivron", FoodCategory.VEGETABLE, 29, 0.5, 0, 4.4, 3.2, 200, 1, 1, 0.009,false, false));

    }
}
