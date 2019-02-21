package model;

import java.time.LocalDate;

public class User {
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
    }
}
