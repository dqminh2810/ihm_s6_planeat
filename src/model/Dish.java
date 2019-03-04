package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * Un Dish est un plat. Un plat peut être une entrée, un principal ou un dessert..
 * @see CourseType
 */

public class Dish {
    private StringProperty name;
    private StringProperty description;
    private CourseType courseType;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> images;

    public Dish(String name, String description, CourseType courseType, ArrayList<Ingredient> ingredients, ArrayList<String> images) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.courseType = courseType;
        this.ingredients = ingredients;
        this.images = images;
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public String getDescription()
    {
        return description.get();
    }

    public void setDescription(String description)
    {
        this.description.set(description);
    }

    public CourseType getCourseType()
    {
        return courseType;
    }

    public void setCourseType(CourseType courseType)
    {
        this.courseType = courseType;
    }

    public ArrayList<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getImages()
    {
        return images;
    }

    public void setImages(ArrayList<String> images)
    {
        this.images = images;
    }

    @Override
    public String toString() {
        return name.get();
    }

    public double getIntakes(int intakeIndex) {
        double sum = 0;
        switch (intakeIndex){
            case 0:
                for (Ingredient ingredient: ingredients)
                    sum += ingredient.getFood().getEnergy() * ingredient.getQuantity();
                break;
            case 1:
                for (Ingredient ingredient: ingredients)
                    sum += ingredient.getFood().getFat() * ingredient.getQuantity();
                break;
            case 2:
                for (Ingredient ingredient: ingredients)
                    sum += ingredient.getFood().getAcid() * ingredient.getQuantity();
                break;
            case 3:
                for (Ingredient ingredient: ingredients)
                    sum += ingredient.getFood().getCarbohydrate() * ingredient.getQuantity();
                break;
            case 4:
                for (Ingredient ingredient: ingredients)
                    sum += ingredient.getFood().getSugar() * ingredient.getQuantity();
                break;
            case 5:
                for (Ingredient ingredient: ingredients)
                    sum += ingredient.getFood().getProtein() * ingredient.getQuantity();
                break;
            case 6:
                for (Ingredient ingredient: ingredients)
                    sum += ingredient.getFood().getSalt() * ingredient.getQuantity();
                break;
            case 7:
                for (Ingredient ingredient: ingredients)
                    sum += ingredient.getFood().getFat() * ingredient.getQuantity();
                break;
            case 8:
                for (Ingredient ingredient: ingredients)
                    sum += ingredient.getFood().getFat() * ingredient.getQuantity();
                break;
        }
        return sum;
    }
}
