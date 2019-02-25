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
    private ArrayList<Menu> menus;


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
        menus = new ArrayList<>();
    }

    public void addRepas(Menu menu){
        this.menus.add(menu);
    }

    public void addRepas(ArrayList<Menu> menus){
        this.menus.addAll(menus);
    }

    public String getPassword(){ return this.password; }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(" ").append(firstName).append("\nSes repas : ").append(menus.size()).append("\n");
        for (Menu menu : menus) {
            builder.append("\t ").append(menu.getName()).append(" at ").append(menu.getTime()).append("\n");
        }
        return builder.toString();
    }
}
