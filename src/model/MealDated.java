package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealDated implements Comparable<MealDated> {
    private Meal meal;
    private LocalDateTime time;

    public MealDated(Meal meal, LocalDateTime time) {
        this.meal = meal;
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Meal getMeal() {
        return meal;
    }

    @Override
    public String toString() {
        return meal.getName() + "\n" + time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public int compareTo(MealDated o) {
        return this.time.compareTo(o.time);
    }
}
