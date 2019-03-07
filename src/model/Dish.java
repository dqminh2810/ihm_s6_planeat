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

    public Dish(String name, String description, CourseType courseType, ArrayList<Ingredient> ingredients) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.courseType = courseType;
        this.ingredients = ingredients;
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

    @Override
    public String toString() {
        return name.get();
    }

}
