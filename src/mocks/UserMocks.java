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

        user = new User("Tremblay", "Jason", LocalDate.now(),
                "jason@gmail.com", UserSex.FEMALE, 90, 185, StatusChoice.STUDENT, CookingFrequency.OFTEN, "test");
        user.addRepas(MealDatedMocks.meals);
        users.put("jason@gmail.com", user);


        user = new User("Martin", "Janette", LocalDate.now(),
                "martin@gmail.com", UserSex.FEMALE, 68, 170, StatusChoice.STUDENT, CookingFrequency.NEVER, "azerty");
        users.put("martin@gmail.com", user);

        user = new User("Sasha", "Carniere", LocalDate.now(),
                "sashouille@gmail.com", UserSex.MALE, 74, 185, StatusChoice.STUDENT, CookingFrequency.NEVER, "password");
        users.put("sashouille@gmail.com", user);

    }
}
