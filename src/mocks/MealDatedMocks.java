package mocks;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class MealDatedMocks {
    public static ArrayList<MealDated> meals;

    public static void initMocks(){
        meals = new ArrayList<>();

        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(2019, 2, 25, 21, 30)));
        meals.add(new MealDated(MealMocks.meals.get(0), LocalDateTime.of(2019, 2, 25, 12, 30)));
        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(2019, 2, 25, 19, 30)));

        meals.add(new MealDated(MealMocks.meals.get(0), LocalDateTime.of(2019, 2, 26, 12, 30)));
        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(2019, 2, 26, 19, 30)));

        meals.add(new MealDated(MealMocks.meals.get(0), LocalDateTime.of(2019, 2, 27, 12, 30)));
        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(2019, 2, 27, 19, 30)));

        meals.add(new MealDated(MealMocks.meals.get(0), LocalDateTime.of(2019, 2, 28, 12, 30)));
        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(2019, 2, 28, 19, 30)));

        meals.add(new MealDated(MealMocks.meals.get(0), LocalDateTime.of(2019, 3, 28, 12, 30)));
        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(2019, 3, 28, 19, 30)));
    }
}
