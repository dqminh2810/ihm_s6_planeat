package mocks;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class UserMocks {
    public static HashMap<String, User> users;

    public static void initMocks(){
        users = new HashMap<>();
        User user;
        ArrayList<Dish> dish = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        user = new User("Todesco", "Gabin", LocalDate.now(),
                "todesco@gmail.com", UserSex.MALE, 76, 185, StatusChoice.STUDENT, CookingFrequency.OFTEN, "test");
        user.addRepas(MealDatedMocks.meals);
        users.put("todesco@gmail.com", user);


        user = new User("Bauce", "Camille", LocalDate.now(),
                "bauce@gmail.com", UserSex.FEMALE, 49, 160, StatusChoice.STUDENT, CookingFrequency.NEVER, "azerty");
        users.put("bauce@gmail.com", user);

        user = new User("Sasha", "Carniere", LocalDate.now(),
                "sashouille@gmail.com", UserSex.MALE, 74, 185, StatusChoice.STUDENT, CookingFrequency.NEVER, "password");
        users.put("sashouille@gmail.com", user);

    }
}