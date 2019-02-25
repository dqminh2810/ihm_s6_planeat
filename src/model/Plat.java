package model;

import java.util.ArrayList;

public class Plat {
    private ArrayList<Ingredient> ingredients;
    private String name;
    private String description;
    private String imageUrl;

    public Plat(ArrayList<Ingredient> ingredients, String name, String description, String imageUrl) {
        this.ingredients = ingredients;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
