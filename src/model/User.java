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
    private ArrayList<Meal> meals;


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
        meals = new ArrayList<>();
    }

    public void addRepas(Meal meal){
        this.meals.add(meal);
    }

    public void addRepas(ArrayList<Meal> meals){
        this.meals.addAll(meals);
    }

    public String getPassword(){ return this.password; }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(" ").append(firstName).append("\nSes repas : ").append(meals.size()).append("\n");
        for (Meal meal : meals) {
            builder.append("\t ").append(meal.getName()).append(" at ").append(meal.getTime()).append("\n");
        }
        return builder.toString();
    }
}
