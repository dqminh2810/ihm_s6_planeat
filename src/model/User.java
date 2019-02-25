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
    private ArrayList<Repas> repas;


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
        repas = new ArrayList<>();
    }

    public void addRepas(Repas repas){
        this.repas.add(repas);
    }

    public void addRepas(ArrayList<Repas> repas){
        this.repas.addAll(repas);
    }
}
