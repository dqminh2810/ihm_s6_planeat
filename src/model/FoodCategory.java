package model;

public enum FoodCategory
{
    WHITE_MEAT ("Viande blanche"),
    RED_MEAT ("Viande rouge"),
    VEGETABLE ("Légumes"),
    FRUIT ("Fruit"),
    FISH ("Poisson"),
    ANIMAL_ORIGIN ("Origine animale"),
    OTHER ("Autre");

    private String name;

    private FoodCategory(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
