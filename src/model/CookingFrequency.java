package model;

public enum CookingFrequency {
    NEVER("Jamais"),
    SOMETIMES("De temps en temps"),
    OFTEN("Souvent");

    private String text;

    CookingFrequency(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
