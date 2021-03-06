package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private Theme themeCss;


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
        this.themeCss = Theme.DARK;
    }

    public void setThemeCss(Theme themeCss) {
        this.themeCss = themeCss;
    }

    public Theme getThemeCss() {
        return themeCss;
    }

    public static User getActualUser() {
        return actualUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserSex getSex() {
        return sex;
    }

    public void setSex(UserSex sex) {
        this.sex = sex;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public StatusChoice getStatus() {
        return status;
    }

    public void setStatus(StatusChoice status) {
        this.status = status;
    }

    public CookingFrequency getCookingFrequency() {
        return cookingFrequency;
    }

    public void setCookingFrequency(CookingFrequency cookingFrequency) {
        this.cookingFrequency = cookingFrequency;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Ingredient> getIngredientHistory(Periode periode) {
        List<Ingredient> list = new ArrayList<>();
        for (MealDated mealDated : mealsDated){
            if (periode.containsDay(mealDated.getTime().toLocalDate()))
                list.addAll(mealDated.getMeal().getAllIngredients());
        }
        return list;
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
