package model;

public enum CourseType
{
    STARTER ("Entrée"),
    MAIN_COURSE ("Plat principale"),
    DESSERT ("Dessert");

    private String name;

    private CourseType(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
