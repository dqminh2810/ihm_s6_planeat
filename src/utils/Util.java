package utils;

import mocks.RecommendedDailyAmount;
import model.Ingredient;
import model.Periode;
import model.User;
import model.UserSex;

import java.util.List;

public class Util {

    public static String obtainStylesheet(int dataPercentage) {
//        return "resources/css/pdfsam/ringprogress.css";
        if (dataPercentage < 50) { return "resources/css/pdfsam/ringprogressBelow50.css"; }
        if (dataPercentage < 75) { return "resources/css/pdfsam/ringprogressBelow75.css"; }
        if (dataPercentage < 100) { return "resources/css/pdfsam/ringprogressAbove75.css"; }
        if (dataPercentage < 125) { return "resources/css/pdfsam/ringprogressAbove100.css"; }
        return "resources/css/pdfsam/ringprogressAbove125.css";
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
