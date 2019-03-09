package model;

import java.util.ArrayList;

public class Food {
    private String name;
    private FoodCategory category;

    private double energy;
    private double fat;
    private double acid;
    private double carbohydrate; //glucides
    private double sugar;
    private double protein;
    private double salt;
    private double fibres;
    private double calcium;

    private boolean peanut;
    private boolean gluten;

    public Food(String name, FoodCategory category, double energy, double fat, double acid, double carbohydrate, double sugar, double protein, double salt, double fibres, double calcium, boolean peanut, boolean gluten) {
        this.name = name;
        this.category = category;
        this.energy = energy;
        this.fat = fat;
        this.acid = acid;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.protein = protein;
        this.salt = salt;
        this.fibres = fibres;
        this.calcium = calcium;
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

    public double getEnergy()
    {
        return energy;
    }

    public void setEnergy(double energy)
    {
        this.energy = energy;
    }

    public double getFat()
    {
        return fat;
    }

    public void setFat(double fat)
    {
        this.fat = fat;
    }

    public double getAcid()
    {
        return acid;
    }

    public void setAcid(double acid)
    {
        this.acid = acid;
    }

    public double getCarbohydrate()
    {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate)
    {
        this.carbohydrate = carbohydrate;
    }

    public double getSugar()
    {
        return sugar;
    }

    public void setSugar(double sugar)
    {
        this.sugar = sugar;
    }

    public double getProtein()
    {
        return protein;
    }

    public void setProtein(double protein)
    {
        this.protein = protein;
    }

    public double getSalt()
    {
        return salt;
    }

    public void setSalt(double salt)
    {
        this.salt = salt;
    }

    public double getFibres() { return fibres; }

    public void setFibres(double fibres) { this.fibres = fibres; }

    public double getCalcium() { return calcium; }

    public void setCalcium(double calcium) { this.calcium = calcium; }

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
