package model;

public enum Theme {
    DARK("Dark"),
    LIGHT("Light");

    private String text;

    Theme(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
