package mocks;

import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class MealDatedMocks {
    public static ArrayList<MealDated> meals;

    public static void initMocks(){
        LocalDate today = LocalDate.now();
        meals = new ArrayList<>();

        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 21, 30)));
       // meals.add(new MealDated(MealMocks.meals.get(0), LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 12, 30)));
        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 19, 30)));

        meals.add(new MealDated(MealMocks.meals.get(0), LocalDateTime.of(today.plusDays(1).getYear(), today.plusDays(1).getMonth(), today.plusDays(1).getDayOfMonth(), 12, 30)));
        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(today.plusDays(1).getYear(), today.plusDays(1).getMonth(), today.plusDays(1).getDayOfMonth(), 19, 30)));

        meals.add(new MealDated(MealMocks.meals.get(0), LocalDateTime.of(today.plusDays(2).getYear(), today.plusDays(2).getMonth(), today.plusDays(2).getDayOfMonth(), 12, 30)));
        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(today.plusDays(2).getYear(), today.plusDays(2).getMonth(), today.plusDays(2).getDayOfMonth(), 19, 30)));

        meals.add(new MealDated(MealMocks.meals.get(0), LocalDateTime.of(today.plusDays(3).getYear(), today.plusDays(3).getMonth(), today.plusDays(3).getDayOfMonth(), 12, 30)));
        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(today.plusDays(3).getYear(), today.plusDays(3).getMonth(), today.plusDays(3).getDayOfMonth(), 19, 30)));

        meals.add(new MealDated(MealMocks.meals.get(0), LocalDateTime.of(today.plusDays(3).getYear(), today.plusDays(4).getMonth(), today.plusDays(4).getDayOfMonth(), 12, 30)));
        meals.add(new MealDated(MealMocks.meals.get(1), LocalDateTime.of(today.plusDays(3).getYear(), today.plusDays(4).getMonth(), today.plusDays(4).getDayOfMonth(), 19, 30)));
    }
}
