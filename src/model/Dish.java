package model;

import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * Un Dish est un plat. Un plat peut être une entrée, un principal ou un dessert..
 * @see CourseType
 */

public class Dish {
    private String name;
    private String description;
    private CourseType courseType;
    private Button deleteButton;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> images;

    public Dish(String name, String description, CourseType courseType, ArrayList<Ingredient> ingredients, ArrayList<String> images) {
        this.name = name;
        this.description = description;
        this.courseType = courseType;
        this.ingredients = ingredients;
        this.images = images;
    }

    public Dish(String name, CourseType courseType){
        this.name = name;
        this.courseType = courseType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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
        return name;
    }
}
