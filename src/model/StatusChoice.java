package model;

public enum StatusChoice {
    STUDENT("Etudiant"),
    UNEMPLOYED("Sans emploi"),
    WORKMAN("Travailleur"),
    RETIRED("Retraité");

    private String text;

    StatusChoice(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
