package mocks;

import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class UserMocks {
    public static HashMap<String, User> users;

    public static void initMocks(){
        users = new HashMap<>();
        User user;
        Menu menu;
        ArrayList<Dish> dish = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        user = new User("Todesco", "Gabin", LocalDate.now(),
                "todesco@gmail.com", UserSex.MALE, 76, 185, StatusChoice.STUDENT, CookingFrequency.OFTEN, "test");
        users.put("todesco@gmail.com", user);
        ingredients.add(new Ingredient(FoodMocks.foods.get(0), 6));
        dish.add(new Dish("Omelette des familles", "A ne pas retourner", CourseType.MAIN_COURSE, ingredients, null));
        menu = new Menu("Omelette", dish, LocalDateTime.now());
        user.addRepas(menu);

        menu = new Menu("Omelette", dish, LocalDateTime.of(2019, 2, 25, 12, 30));
        user.addRepas(menu);

        user = new User("Bauce", "Camille", LocalDate.now(),
                "bauce@gmail.com", UserSex.FEMALE, 49, 160, StatusChoice.STUDENT, CookingFrequency.NEVER, "azerty");
        users.put("bauce@gmail.com", user);

        user = new User("Sasha", "Carniere", LocalDate.now(),
                "sashouille@gmail.com", UserSex.MALE, 74, 185, StatusChoice.STUDENT, CookingFrequency.NEVER, "password");
        users.put("sashouille@gmail.com", user);

    }
}
