package model;

public enum UserSex {
    MALE("Homme"),
    FEMALE("Femme");

    private String text;

    UserSex(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
