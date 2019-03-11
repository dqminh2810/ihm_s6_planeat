package utils;

import mocks.RecommendedDailyAmount;
import model.Ingredient;
import model.Periode;
import model.User;
import model.UserSex;

import java.util.List;

public class Util {

    public static String obtainStylesheet(int dataPercentage) {
        return "resources/css/pdfsam/ringprogress.css";
    }

    public static int getIntakePercentage(double intakes, double recommended) {
        return (int) ((intakes/recommended)*100);
    }

    public static double[] getReferenceIntakes() {
        if (User.getActualUser().getSex().equals(UserSex.FEMALE))
            return RecommendedDailyAmount.female;
        else
            return RecommendedDailyAmount.male;
    }

    public static List<Ingredient> getIngredientHistory(Periode periode) {
        return User.getActualUser().getIngredientHistory(periode);
    }
}
