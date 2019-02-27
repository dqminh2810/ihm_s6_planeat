package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {

    public static User actualUser = null;

    private String name;
    private String firstName;
    private LocalDate birthDate;
    private String mail;
    private UserSex sex;
    private float weight;
    private int size;
    private StatusChoice status;
    private CookingFrequency cookingFrequency;
    private String password;
    private ArrayList<MealDated> mealsDated;


    public User(String name, String firstName, LocalDate birthDate, String mail, UserSex sex, float weight, int size, StatusChoice status, CookingFrequency cookingFrequency, String password) {
        this.name = name;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.mail = mail;
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.status = status;
        this.cookingFrequency = cookingFrequency;
        this.password = password;
        mealsDated = new ArrayList<>();
    }

    public void addRepas(MealDated meal){
        this.mealsDated.add(meal);
    }

    public void addRepas(ArrayList<MealDated> meals){
        this.mealsDated.addAll(meals);
    }

    public String getPassword(){ return this.password; }

    public ArrayList<MealDated> getMeals() {
        return mealsDated;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(" ").append(firstName).append("\nSes repas : ").append(mealsDated.size()).append("\n");
        for (MealDated meal : mealsDated) {
            builder.append("\t ").append(meal.getMeal().getName()).append(" at ").append(meal.getTime()).append("\n");
        }
        return builder.toString();
    }
}
