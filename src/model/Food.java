package model;

import java.util.ArrayList;

public class Food {
    private String name;
    private FoodCategory category;

    private int energy;
    private int fat;
    private int acid;
    private int carbohydrate; //glucides
    private int sugar;
    private int protein;
    private int salt;

    private boolean peanut;
    private boolean gluten;

    public Food(String name, FoodCategory category, int energy, int fat, int acid, int carbohydrate, int sugar, int protein, int salt, boolean peanut, boolean gluten) {
        this.name = name;
        this.category = category;
        this.energy = energy;
        this.fat = fat;
        this.acid = acid;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.protein = protein;
        this.salt = salt;
        this.peanut = peanut;
        this.gluten = gluten;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public FoodCategory getCategory()
    {
        return category;
    }

    public void setCategory(FoodCategory category)
    {
        this.category = category;
    }

    public int getEnergy()
    {
        return energy;
    }

    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    public int getFat()
    {
        return fat;
    }

    public void setFat(int fat)
    {
        this.fat = fat;
    }

    public int getAcid()
    {
        return acid;
    }

    public void setAcid(int acid)
    {
        this.acid = acid;
    }

    public int getCarbohydrate()
    {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate)
    {
        this.carbohydrate = carbohydrate;
    }

    public int getSugar()
    {
        return sugar;
    }

    public void setSugar(int sugar)
    {
        this.sugar = sugar;
    }

    public int getProtein()
    {
        return protein;
    }

    public void setProtein(int protein)
    {
        this.protein = protein;
    }

    public int getSalt()
    {
        return salt;
    }

    public void setSalt(int salt)
    {
        this.salt = salt;
    }

    public boolean isPeanut()
    {
        return peanut;
    }

    public void setPeanut(boolean peanut)
    {
        this.peanut = peanut;
    }

    public boolean isGluten()
    {
        return gluten;
    }

    public void setGluten(boolean gluten)
    {
        this.gluten = gluten;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
